# Testing

If you're writing too many integration tests, you might not be extracting logic into functions.

```java
import org.junit.Test; // WRONG (junit4)
import org.junit.jupiter.api.Test; // RIGHT (junit5)
```

# Testcontainers

There's a ~/.testcontainers.properties in your home directory.

```bash
#Modified by Testcontainers
#Tue Jan 16 18:16:32 MST 2024
docker.client.strategy=org.testcontainers.dockerclient.UnixSocketClientProviderStrategy
testcontainers.reuse.enable=true
```

### @Container
This gives control of the container's lifecycle to spring

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

[How to reuse Testcontainers between multiple SpringBootTests](https://stackoverflow.com/questions/62425598)
