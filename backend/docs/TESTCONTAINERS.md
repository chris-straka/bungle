# Testcontainers

There's a ~/.testcontainers.properties in your home directory.

```bash
#Modified by Testcontainers
#Tue Jan 16 18:16:32 MST 2024
docker.client.strategy=org.testcontainers.dockerclient.UnixSocketClientProviderStrategy
testcontainers.reuse.enable=true
```

## Testcontainers in development

When they say "testcontainers at development" they mean ./gradlew bootTestRun not ./gradlew test

```java
// This loads the entire spring application context
@SpringBootTest()

// This is only meant for integration testing
// It starts new containers for each class
@Testcontainers
```

## Resources

[Reusing testcontainers](https://rieckpil.de/reuse-containers-with-testcontainers-for-fast-integration-tests/)

[Reusing testcontainers](https://logarithmicwhale.com/posts/faster-tests-by-resuing-testcontainers-in-spring-boot/)
