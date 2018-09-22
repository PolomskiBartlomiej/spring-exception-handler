# spring-exception-handler
Project shows how to provides implemenation of error-handling in spring boot application

# preface
 # Spring MVC
  After `Spring 3.2` Spring MVC provides new feuture to handling with exception: `@ControllerAdvice`and                       
  `@Rest@ControllerAdvice`, upgrades it in `Spring 4` 

  > Classes annotated with `@ControllerAdvice` can contain `@ExceptionHandler`, `@InitBinder`, and `@ModelAttribute` annotated     methods, and these methods will apply to @RequestMapping methods across all controller hierarchies as opposed to the           controller hierarchy within which they are declared.

  > @RestControllerAdvice is an alternative where @ExceptionHandler methods assume @ResponseBody semantics by default.

 # Spring WS
   To Handling Exceptions we have to way:
   * annotate exception classes with the @SoapFault annotation, to indicate the SOAP Fault that should be returned whenever        that exception is thrown
   * using SoapFaultMappingExceptionResolver, which enables you customize SOAP Fault of any exception thath might be thrown
   
 
  
