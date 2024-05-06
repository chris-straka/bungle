# Class Loaders

A class loader (CL) is an object that loads classes.
Each Java app uses multiple CLs in a hierarchy.

```java
class Foo {
  void bar() {
    // This gets the CL that loaded Foo initially (app CL)
    ClassLoader loader = getClass().getClassLoader();
    // This lets Jackson load the security modules it needs from the classpath
    List<Module> modules = SecurityJackson2Modules.getModules(loader);
  }
}
```

## Built-in CLs

Bootstrap CL: Loads core Java API classes from JAVA_HOME/jre/lib.
Extension CL: Loads extension-specific classes from JAVA_HOME/jre/lib/ext (or java.ext.dirs)
System/App CL: Loads classes and app-level resources from -cp.
