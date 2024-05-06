# Concurrency

https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html

## Threads

```java
Thread a = new Thread();
class A extends Thread {}      // Java doesn't support multiple inheritance
class B implements Runnable {} // This lets you inherit from another class
```

## Executor Framework

Used for managing worker threads efficiently.
The Executor API reduces the execution of the task from the actual task to be executed through the Executors.
The java.util.concurrent.Executors class provides a set of methods for creating ThreadPools of worker threads.

```java
// Executor framework follows the Producer-Consumer pattern
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService a = Executors.newSingleThreadExecutor()
ExecutorService b = Executors.newFixedThreadPool(4);
ExecutorService c = Executors.newCachedThreadPool();
ScheduledExecutorService d = Executors.newScheduledThreadPool(1);
```

## Volatile and Static

Any changes to a volatile variable is immediately visible to all threads.
It doesn't lock the variable for all threads while it's being changed.
Without it, the same volatile variable could be seen as two different values for each thread.

Static is not a concurrency thing. It creates a variable once.
You can create 0 objects and still have access to that static variable.
The thread can cache the static variable, making it different between threads.
You can use static volatile to make sure that the thread doesn't cache it.

If volatile makes it so the thread can't cache it, where does it go? Main memory.

## Synchronized

It's called synchronization because we want all the threads to have an identical understanding of who is allowed to run and when.

Synchronized is specifically designed for concurrency.
It locks an object/method so only one thread can execute it at a time. 
You should prefer the stuff in java.util.concurrent instead.

```java
// Thread A
public void a()
{
    synchronized(someObject) {
        // do something (1)
    }
}

// Thread B
public void b()
{
    synchronized(someObject) {
        // do something else (2)
    }
}
```

The monitor will monitor someObject and make sure nothing else interferes with it.

## Data race vs Race condition

A data race occurs when 2 instructions from different threads access the same memory location, at least one of these accesses is a write and there is no synchronization that is mandating any particular order among these accesses.

A race condition is a semantic error. It's a flaw that occurs in the timing or the ordering of events that leads to erroneous program behavior. 
Many race conditions can be caused by data races, but this is not necessary.

A semantic error is code that's syntactically correct but doesn't make sense.

```java
int a;
a = 1
a = 2
```

If a is 1 and not 2, it's possible you just had a race condition and not a data race.

[source](https://stackoverflow.com/questions/11276259)
[source 2](https://www.youtube.com/watch?v=ycQk80YlxPc)

## Monitor

Every object has a lock (inherited by java.lang.Object).
A method does not have a monitor, when you synchronize a method you're grabbing the object's lock.
The monitor makes sure that a thread has the lock before it executes any code.

## sleep() and wait()

sleep() is a blocking operation that keeps a hold on the monitor / lock of the shared object for the specified number of milliseconds.

wait(), on the other hand, simply pauses the thread without holding the lock until either 
- (a) the specified number of milliseconds have elapsed or 
- (b) it receives a desired notification from another thread 

sleep() is most commonly used for polling, or to check for certain results, at a regular interval. 
wait() is generally used in multithreaded applications, in conjunction with notify() / notifyAll(), to achieve synchronization and avoid race conditions.
