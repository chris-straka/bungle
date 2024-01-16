### @Bean
@Bean doesn't mean java bean, it means "make it available for Spring's dependency injection (IoC)"
Adding it to a @TestConfiguration class puts it in the application context (not the test context)
Adding it to the app context ties it to the app's lifecycle instead of the test lifecycle


### @Container
This gives control of the container's lifecycle to spring
