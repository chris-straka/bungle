# Design

### DDD

The repositories can be created once on application startup, making DI/testing easy.
However, I can't do this for my rich data models because they're created dynamically.
So somewhere in my application, I have to instantiate them, they can't be IoC'd.

[Using the same entity for different user cases](https://softwareengineering.stackexchange.com/questions/392047)

[Migration From Anemic Models to Rich Models](https://softwareengineering.stackexchange.com/questions/386432)
