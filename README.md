# spring-exception-handler
Project shows how to provide implemenation of error-handling in spring boot application

# preface
 # Spring MVC
  After `Spring 3.2` Spring MVC provides new features to handling with exception: `@ControllerAdvice`and                       
  `@Rest@ControllerAdvice`, and upgrades it in `Spring 4` 

  > Classes annotated with `@ControllerAdvice` can contain `@ExceptionHandler`, `@InitBinder`, and `@ModelAttribute` annotated     methods, and these methods will apply to @RequestMapping methods across all controller hierarchies as opposed to the           controller hierarchy within which they are declared.

  > @RestControllerAdvice is an alternative where @ExceptionHandler methods assume @ResponseBody semantics by default.

 # Spring WS
   To Handling Exceptions we have to way:
   * annotate exception classes with the @SoapFault annotation, to indicate the SOAP Fault that should be returned whenever        that exception is thrown
   * using SoapFaultMappingExceptionResolver, which enables you customize SOAP Fault of any exception thath might be thrown
   
# project description
  Project focuces in handling error using RestControllerAdvice and SoapFaultMappingExceptionResolver.
 
  error handling assumptions:
   * when IllegalArgumentException throws then response status shoul have BAD_REQUEST status and erorr massage
   * when bussines exception (NoResultException) throws then reponse should have NOT_FOUND status and erorr massage
   * when else exception throws then reponse should have INTERNAL_SERVER_ERROR status 
 
 
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
   
   
 
   
    
    
    
 
  
