# Dynamic Proxies (JDK Proxies)

A "dynamic proxy class" is a class that implements a set of "proxy interfaces" that is created at runtime.
A proxy object is an instance of that class, and it intercepts all calls to the interface methods.
It funnels interface method calls to its invocation handler object's invoke() method.

Interfaces are not removed in the java bytecode, they persist at runtime (for reflection, proxies).
Proxies are used for logging, access control, transaction management, and performance monitoring.
Framework writers use proxies because they don't know what concrete classes the user will create.

```java
import java.lang.reflect.InvocationHandler;

// This is not the proxy, it defines the behaviour
public class DynamicInvocationHandler implements InvocationHandler {
    // invoke() intercepts all methods invoked on the proxied object
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method called: " + method.getName());
    }
}

import java.lang.reflect.Proxy;

// This creates a dynamic proxy class at runtime 
// Then it instantiates that class to give use the proxyInstance
Map proxyInstance = (Map) Proxy.newProxyInstance(
    DynamicProxyTest.class.getClassLoader(),
    // Create a dynamic proxy for every object that implements the Map interface.
    new Class[] { Map.class },
    new DynamicInvocationHandler()
);

proxyInstance.put("hello", "world"); // invoked method: put
```
