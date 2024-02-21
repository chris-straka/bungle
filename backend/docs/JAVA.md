# Java

### jlink

The JRE is not included in the JAR file, the client must install it.
But with Java 9 modules you can "pick and choose" different components from the JRE and produce a runtime image.
That image can run on someone else's computer, even if they don't have Java installed.
It's like golang though and each image is OS specific.

### GraalVM

GVM produces native images by compiling java programs ahead of time into an OS specific executable.
It has good startup time, potentially lower memory footprint, and easier distribution.
Some dynamic features like reflection require explicit configuration to work correctly though.

### ClassLoader

A class loader is an object that loads classes
Each Java app uses multiple class loaders in a hierarchy

```java
class Foo {
  void bar() {
    // This gets the class loader that loaded Foo initially (system/app class loader)
    ClassLoader loader = getClass().getClassLoader();
    // This lets Jackson load the security modules it needs from the classpath
    List<Module> modules = SecurityJackson2Modules.getModules(loader);
  }
}
```

The classpath is a parameter in the JRE that specifies the locations of all the classes (both mine and others). 
The classpath has things like directories, and JAR files on it.

### Weak/Soft/Phantom Reference

Weak -> Let's you reference an obj that can be GC'd at any time
Soft -> Same as weak, except it will only GC if it needs more memory
Phantom -> Let's you reference an obj after it's finalized but before memory is released

Finalized means "prepared for GC". You'd use a phantom for cleanup operations.

```java
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

ReferenceQueue<Object> queue = new ReferenceQueue<>();
Object obj = new Object();

// Create a phantom reference for that obj
PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);

// obj can now be garbage collected, and phantomRef is used for post-collection cleanup
```

### Volatile and Static

Volatile is a keyword specifically designed for concurrency.
Any changes to a volatile var is immediately visible to all threads.
It doesn't lock the variable for all threads, it just makes the changes immediately visible for all threads.
Each object gets its own volatile variable (100 objects means 100 volatile variables)
The key part, is volatile prevents the variable from being cached locally to one thread.
Without it, the same Object.var can be two different values for each thread, because it could be cached in one thread.

Static is not a concurrency thing. It creates a variable something once.
You can create 0 objects and still have access to that static variable.
The thread can cache the static variable, making it different between threads.
You can use static volatile to make sure that the thread doesn't cache it.

If volatile makes it so the thread can't cache it, where does it go? Main memory.

### Synchronized

Synchronized is specifically designed for concurrency.
It locks a variable so multiple threads can't create a data race. 
You should lean towards using java.util.concurrent over this keyword.
