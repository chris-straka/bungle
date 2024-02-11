# Spring

### Contexts

The application context or spring container manages beans. 
In a multimodule project, each module can be its own application context.

There's also a sessions context, security context, etc.

### "Stereotypes"

It's a technical term for all the annotations that mark a class for automatic detection and registration as a Spring bean.

@Component marks a class for scanning, letting Spring detect whether the class is a bean.
@Configuration is an offshoot of component, except it tells spring that this class might also declare beans.
@Service is an offshoot of component, except it tells spring it's part of the service layer.
@Bean is put on @Component class methods and says "make the return object available for Spring's DI (IoC)"

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

### Resources

[JPA Performance pitfalls](https://medium.com/@majbahbuet08/performance-pitfalls-while-using-spring-data-jpa-and-solutions-to-avoid-them-5eb4ee3fe4ea)
