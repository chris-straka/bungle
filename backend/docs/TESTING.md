# Testing

```java
import org.junit.Test; // WRONG (junit4)
import org.junit.jupiter.api.Test; // RIGHT (junit5)
```

## Tips

If you find that you're writing too many integration tests,
it's because you're not taking out logic and putting it in its own method. 
