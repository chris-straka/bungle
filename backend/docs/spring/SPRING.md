# Spring

```bash
./gradlew tasks # shows you what tasks are available

# Get a feel for what the starter packages included
./gradlew dependencies         # Displays all dependencies declared in root project 'bungle'.
./gradlew dependencyInsight    # Displays the insight into a specific dependency in root project 'bungle'.
./gradlew dependencyManagement # Displays the dependency management declared in root project 'bungle'.

# Grab the version so you can read the API docs
chris@y8 backend % ./gradlew dependencies | grep "jakarta.persistence" # 3.1.0 btw
```

## General Spring Notes

### Context

The application context (or spring container) is an interface that manages beans.
Beans are objects that you can DI everywhere. It makes for loose coupling and easy testing.
In a multi-module project, each module can be its own application context.

There's also a sessions context, a security context, etc.

https://stackoverflow.com/questions/29862681/java-spring-multiple-applicationcontext

### "Stereotypes"

Technical term for all the annotations that mark a class for automatic detection and registration as a bean.

`@Component` makes the class a bean and marks it for scanning.
`@Bean` is a method annotation that says "make this return object a bean available for Spring's DI (IoC)"
`@Configuration` is an offshoot of @Component, except it tells spring that this class might also declare beans.
`@Service` is an offshoot of component, except it tells spring it's part of the service layer.

Beans are singletons.

#### @Configuration(proxyBeanMethods = false)

By default, spring creates a CGLIB proxy object for every @Configuration class.
The proxy object makes sure bean methods follow the correct "bean scope" (singleton by default).
The bean scope determines how many instances of a bean are created, shared and reused.

You can get rid of the proxy object by setting proxyBeanMethods = false.
This would improve the application startup time and memory usage.
The application context will respect the singleton scope if so configured.
But direct calls within the configuration class will still create new instances.
If you're not careful, this could lead to more bean instances than intended.

E.g.

Assuming you want "singleton" bean scope and Foo.a() calls Foo.beanMethod().
Without a proxy object, multiple calls to Foo.a() will create multiple bean objects.
The same bean will not be reused and different parts of the app will have different beans.

### Bean Scope

https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html

### @Autowired

It's for injecting dependencies into a class.

Constructor injection looks like this and is prefered.

```java
public class ApplicationConfig {
    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

@Autowired looks like this and can inject in three different ways.

```java
public class ApplicationConfig {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

In all cases, the annotation sets the dependency using reflection after the object is created.
This means the field can't be final, limiting immutability (which is why manual CI is preferred)
But sometimes you need @Autowired for libraries where you don't control its object instantiation.
@Autowired lets you integrate these objects into your spring context.

You use @Bean methods in configuration classes to instantiate and configure third-party objects.
This makes them available as beans in Spring's application context.
Then, you can then @Autowire them wherever they're needed.

### Servlet vs WebFlux

Servlets are good for CPU intensive tasks like...

- image/audio/video processing
- data encryption/decryption
- scientific computation/algorithms
- machine learning tasks (training models, processing datasets)

WebFlux is better for IO tasks (everything else)

- image/audio/video streaming
- lots of network or DB operations
