# Classpath

The classpath is a parameter for both the JVM and the java compiler. 
It specifies the location of user-defined classes that have already been compiled. 
By "user-defined", I mean classes I wrote and compiled (.class) or libraries other people wrote and compiled (.jar) like spring.

```bash
# The : separates each path (unix)
# The classpath includes library code and any classes I compiled previously (bin)
# This compiles every java file in the current directory (and not inside nested dirs)
javac -cp ./libs/*:./bin *.java

# Without JAR
java -classpath ./libs/*:./bin MyApplication

# With JAR (classpath is specified in the JAR)
java -jar MyApplication.jar
```

The reason why the java command also has a classpath is because you don't have to run a JAR.
If you aren't running a JAR you need to tell it where the compiled classes are that you're using.

The default -cp is "." and it's what it uses when you don't give it a class path.

#### Classpath Resource

A classpath resource is a file or resource that's accessible to Java applications at runtime.
Your ./lib directory might have a .txt file that can be loaded by the class loader at runtime.
