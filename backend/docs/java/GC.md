# Garbage Collection

### Perm Gen

Permanent generation is the space on the Java Heap that holds the meta-data describing user classes (classes that are not part of the Java language). Examples of such meta-data are objects describing classes and methods and they are stored in the Permanent Generation. Applications with large code-base can quickly fill up this segment of the heap which will cause java.lang.OutOfMemoryError: PermGen no matter how high your -Xmx (maximum size of the memory allocation pool) and how much memory you have on the machine.

### Weak/Soft/Phantom Reference

Weak -> reference to an obj that can be GC'd any time
Soft -> same, except it only GC's if it needs more memory
Phantom -> Let's you reference an obj after it's "finalized" but before memory is released

Finalized means "prepared for GC". 

You'd use a phantom for cleanup operations.

```java
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

ReferenceQueue<Object> queue = new ReferenceQueue<>();
Object obj = new Object();

// Create a phantom reference for that obj
PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);

// obj can now be garbage collected, and phantomRef is used for post-collection cleanup
```
