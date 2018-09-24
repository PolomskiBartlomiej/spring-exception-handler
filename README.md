# spring-exception-handler
Project shows how to provide implemenation of error-handling in spring boot application

# preface
  # Spring MVC
  After `Spring 3.2` Spring MVC provides new features to handling with exception: `@ControllerAdvice`and                       
  `@Rest@ControllerAdvice`, and upgrades it in `Spring 4` 

  > Classes annotated with `@ControllerAdvice` can contain `@ExceptionHandler`, `@InitBinder`, and `@ModelAttribute`            annotated     methods, and these methods will apply to @RequestMapping methods across all controller hierarchies as          opposed to the           controller hierarchy within which they are declared.

  > @RestControllerAdvice is an alternative where @ExceptionHandler methods assume @ResponseBody semantics by default.

  # Spring WS
   To Handling Exceptions we have to way:
   * annotate exception classes with the @SoapFault annotation, to indicate the SOAP Fault that should be returned whenever        that exception is thrown
   * using SoapFaultMappingExceptionResolver, which enables you customize SOAP Fault of any exception thath might be thrown
   
# project description

  Project makes up in hexagonal architectural style :  
   * `app`:
      contains rest controller and soap endpoint
   * `domain` :
      contains model, bussines logic and port to infrastructure
      
        **port :**
        ```
        public interface OrderRepository {
         Optional<Order> findById(Integer orderId);
        }
        ```
      
       **bussiness logic :**
        ```
        public class OrderService {

        private final OrderRepository repository;

        public Order findById(@NonNull Integer orderId) {
          return repository
                .findById(orderId)
                .orElseThrow(() -> new NoResultException("No result for order id = " + orderId));
       }
       }
       ```
  
  * `infrastructure` :
      contains configuration of rest, soap and adapter to repository
    
      **adapter simualates repository :**
      ``` 
      ...
      class OrderRepositoryAdapter implements OrderRepository {

      @Override
      public Optional<Order> findById(@NotNull Integer orderId) {
         switch (orderId) {
            case 0 : throw new IllegalArgumentException("orderId cannot be 0");
            case 1 : return Optional.empty();
            case 2 : return buildOrder(orderId);
            default: throw new IllegalStateException("Illegal state");
        }
      }
     ...
     ```
# assumptions and implemenation

  Project focuces in handling error using `RestControllerAdvice` and `SoapFaultMappingExceptionResolver`.
  
  error handling assumptions:
   * when IllegalArgumentException throws then response status shoul have BAD_REQUEST status and erorr massage
   * when bussines exception (NoResultException) throws then reponse should have NOT_FOUND status and erorr massage
   * when else exception throws then reponse should have INTERNAL_SERVER_ERROR status 
  
  # Rest
   _Reference_: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
   
   In `Spring 4.0` handling exception is enabled by `@RestControllerAdvice` mixing with `@ExceptionHandler`
   and customize error json :
   
   Error Json :
   ```
   ...
   class MessageExceptionJson {
     String url;
     String message;
   }
   ```
   Interception with `@RestControllerAdvice`
   ```
   @RestControllerAdvice
   class RestExceptionInterceptor {
   ...
   }
   ```
   and configuration using `@ExceptionHandler`  :
   
   handling `IllegalArgumentException`
   ```
   @ResponseStatus(code = HttpStatus.BAD_REQUEST)
   @ExceptionHandler(IllegalArgumentException.class) MessageExceptionJson
   handleException(HttpServletRequest request, IllegalArgumentException exception) {
      return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message(exception.getLocalizedMessage())
                .build();
    }
  ```
  handling `NoResultException`
  
  ```
   @ResponseStatus(code = HttpStatus.NOT_FOUND)
   @ExceptionHandler(NoResultException.class) MessageExceptionJson
   handleException(HttpServletRequest request, NoResultException exception) {
        return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message(exception.getLocalizedMessage())
                .build();
    }
 
  ```
  handling `Excpetion`
  ```
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler MessageExceptionJson
   handleException(HttpServletRequest request, Exception exception) {
        return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message("Something go wrong !")
                .build();
    }
  ```
 **notes**
 
  When in application is uses many `ControllerAdive` or `RestControllerAdive`, then spring registered all of them.  
  If  `@ControllerAdvice` with an `@ExceptionHandler` for Exception gets registered before another
 `@ControllerAdvice` class with an `@ExceptionHandler` for a more specific exception, 
  then first one will get called.
  To avoids this problem , use '@Order' to control order of registering `ControllerAdiveBean` in Spring.
  
  _Reference_: https://stackoverflow.com/questions/19498378/setting-precedence-of-multiple-controlleradvice-exceptionhandlers
 
 
  # SOAP
  In the Soap error handling is enabled by `SoapFaultMappingExceptionResolver` and override `customizeFault` method
  
  ```
  public class SoapExceptionInterceptor extends SoapFaultMappingExceptionResolver {

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
    ...
    }
  ```
  
  and registered bean `SoapFaultMappingExceptionResolver`
  
  ```
  @Bean SoapFaultMappingExceptionResolver
    exceptionResolver() {
        SoapFaultMappingExceptionResolver exceptionResolver = new SoapExceptionInterceptor();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(SoapFaultHandler.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }
   ``` 
 
  
  
  
    
  
    
    
 
  
