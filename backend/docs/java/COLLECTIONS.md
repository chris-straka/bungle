# Collections

Collections can only hold objects in Java (Integer, Number, Long) and not primitives.

### Fail-fast iterators

Fail-Fast iterators terminate quickly whenever there's an error.
Examples include the iterators you'd get from collections in `java.util`.

```java
// fail-fast iterators
Iterator<E> a = arrayList.iterator();

// You typically iterate over entries
Iterator<K> iterator = hashMap.keySet().iterator();
```

Every collection has an internal modCount that keeps track of the number of items.
If the modCount in iterator.next() is a mismatch from the previous, it throws `ConcurrentModificationException`.

### Fail-safe iterators

Fail-Safe iterators keep runnning even when they hit an error.
They clone the underlying collection to prevent throwing a `ConcurrentModificationException`.
Concurrent collections in `java.util.concurrent` will return a default iterator that is fail-safe.

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

map.put("First", 10);

Iterator<String> iterator = map.keySet().iterator();

while (iterator.hasNext()) {
    String key = iterator.next();
    map.put("Fifth", 50);
}
```

Keep in mind, the underlying collection can still be mutated while you iterate.
