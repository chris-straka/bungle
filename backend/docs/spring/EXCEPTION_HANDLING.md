# Exception Handling

## RuntimeException

Spring likes to use unchecked RuntimeExceptions.
They're usually meant for unanticipated events that you can't recover from. 
Programming bugs, logic errors, NPE, etc. 
They're not subject to the Catch/Specify Requirement (specify = throws X).

https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html

## Prior to Spring 3.2

@ExceptionHandler

```java
public class FooController{
    // This exception handler applies to all FooController methods like getFoo(), setFoo()
    // Issue: It's locked to this controller, you can't use this handler globally
    @ExceptionHandler({ CustomException1.class, CustomException2.class })
    public void handleException() {}
}
```

HandlerExceptionResolver

```java
// Has many implementations, including custom resolvers, all enabled in 3.0 or 3.1
// Issue: They convert exceptions to HTTP response codes but the response body is empty
interface HandlerExceptionResolver {}
interface ExceptionHandlerExceptionResolver {}
interface DefaultHandlerExceptionResolver {}
interface ResponseStatusExceptionResolver {}
```

## Spring 3.2

```java
@ControllerAdvice() 
```

## Spring 5

```java
ResponseStatusException
```
