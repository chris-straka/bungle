# Lombok

Be careful with @ToString, it generates a method that prints/calls every field by default.

```java
@ToString
static class Foo {
    @ToString.Exclude
    private final String password;

    @ToString.Exclude
    @ManyToMany()
    private final Bar getExpensiveEntity;

    private final String name;
}
```

If you feel like you have to use @Value, look into Java 16 public records instead.

@Getter, @Setter, @NoArgsConstructor are much safer.

https://www.reddit.com/r/java/comments/mpd0gw/is_using_project_lombok_actually_an_good_idea/
