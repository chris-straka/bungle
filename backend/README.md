# Bungle

1. Install jdk version 21 https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html
2. Install docker desktop and run it https://www.docker.com/products/docker-desktop/
3. (Optional) `brew install spring-boot` if on Mac

See BUNGLE.md for an explanation of what this API does.
See /docs for my other notes.

#### Development

```bash
### Development (no docker compose commands necessary)
./gradlew bootRun

### Hot Reload
# Terminal A
./gradlew build --continuous --parallel 
# Terminal B
./gradlew bootRun

### Testing
./gradlew test --continuous

### Debugging
# Run then attach debugger to localhost:5005
./gradlew test --debug-jvm
```

Dependencies

```bash
# Refresh gradle after adding dependencies
./gradlew --refresh-dependencies 
# Vscode -> Developer: Reload Window (this is ridiculous, there must be a better way lol)
```

#### Build

```bash
# This creates ./build 
./gradlew bootRun 

# This adds ./build/libs/bungle-0.0.1-SNAPSHOT.jar
# It's a fat jar and includes classes, resoureces, dependencies, etc.
./gradlew bootJar
```
