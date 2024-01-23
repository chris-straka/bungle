### /build 

```bash
# This creates the build folder with these subdirs
# classes, generated, resources and tmp.
./gradlew bootRun 

# This assembles the output for the project
# It adds the libs dir to the build folder
./gradlew bootJar

# That libs folder contains this deployable fat JAR (classes, resources, dependencies) 
# bungle-0.0.1-SNAPSHOT.jar
```

The plain JAR only includes the classes and resources folders.
The other JAR is a fat jar, it also includes the dependencies and it's what I deploy.

### @Bean
@Bean doesn't mean java bean, it means "make the return object available for Spring's dependency injection (IoC)"
Adding it to a @TestConfiguration class puts it in the application context (not the test context)
Adding it to the app context ties it to the app's lifecycle instead of the test lifecycle

### @Container
This gives control of the container's lifecycle to spring
