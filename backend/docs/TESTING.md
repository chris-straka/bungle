# Testing

## Testcontainers in development

When they say "testcontainers at development time" they're referring to ./gradlew bootTestRun
This part requires extra configuration and is more hands on.

```java
// This loads the entire spring application context
@SpringBootTest()
```

## @TestContainer in testing

Use this annotation for integration testing. 
The configuration for this is mostly automatic, you just need new WhateverDB() and @Container + @ServiceConnection

## Testing Methodology

Logic that works in isolation should be unit tested
Logic that involves another service should be integration tested

You should take out the logic that can run in isolation out of the logic that integrates with another service. 
You should then put it in its own function and unit test it. An example is input validation. 
