# Interfaces

## Marker Interface

It's an interface with no field or methods. Examples include Serializable, Clonable and Remote.
They're tell the compiler the class is special in some way.

## Comparator

Comparator lets you sort objects in any way you choose, offering flexibility beyond the default sorting order.

```java
List<Person> people = new ArrayList<>();
people.add(new Person("a", 1));
people.add(new Person("b", 2));

// sort(collection, Comparator)
Collections.sort(people, (p1, p2) -> p1.getAge() - p2.getAge());
Collections.sort(people, (p1, p2) -> p1.getName().compareTo(p2.getName()));
```

## Comparable

Comparable is used to enforce a default sorting order for objects of a class, even when multiple sorting orders are possible.

```java
// Use Comparable when you want a collection to be sorted the same way every time
public class Employee implements Comparable<Employee> {
    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Order employees by id, always
    @Override
    public int compareTo(Employee other) {
        return this.id - other.id;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "a"));
        employees.add(new Employee(1, "b"));

        Collections.sort(employees); // Sorts by natural ordering the id
    }
}
```
