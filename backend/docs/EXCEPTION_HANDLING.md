# Error Handling

## RuntimeException

Spring likes to use the RuntimeException for throwing exceptions

"""
Runtime exceptions are usually unanticipated events that you don't recover from. 
Think programming bugs, logic errors, NPE. 
They're not subject to the Catch/Specify Requirement (specify means throws X).
They're indicated by RuntimeException and its subclasses.
"""

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
