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

### Exceptions

Unchecked exceptions are not subject to the catch/specify requirement.

```java
// spring boot loves RuntimeExceptions because it makes code easier to read
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
```

Checked exceptions must be caught or thrown (specified).

### hashCode()

It's inherited from the Object class.
It's used to make sure that an object can fit in a HashMap/Set/table.
It provides an integer that is roughly unique for each object.
It doesn't represent or derive from the object's memory address.
Its value depends on the JVM you use and other factors (possibly object state).

If you override equals() you MUST also override hashCode().
Because objects that are equal must have the same hashCode().
You would override equals() to tell java what it means for objects of that class to be "structurally equal"

### equals()

equals() is meant to check for structural equality but by default it checks for referential equality (same as ==)
You have to override equals() in your class to tell Java what it means for that object to be structurally equal.

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // The object must have the same name and the same age
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;

        // The == works differently for primitives (doesn't check references)
        // You need equals() for strings since they're objects
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
```

### Inner class vs Static Nested class

The inner class object can only exist as part of an outer object.
Therefore, it has access to all the outer object's methods (including private)

```java
Outer().Inner()
```

Static nested classes are treated like separate classes. 
They do not have access to any of the outer class methods (except static methods).
