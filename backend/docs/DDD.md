# Domain Driven Design

An Aggregate (A) is a cluster of domain objects (DOs) treated as a single unit. 
Every A has an Aggregate Root (AR), which is the only object outside objects can reference.
(Objects within an A can reference each other freely, though)

An AR controls access to A's members, guaranteeing consistency for all changes.
An example might be an order (AR) and its line items (DOs)

Domain Events (DEs) announce something in the domain that domain experts care about. 
DEs allow for side effects without coupling. Domain experts is not a technical term.

## Observations

Repositories can be created once at application startup, making DI/testing easy.
I can't do this for rich domain models though, because they often have to be created dynamically.

[Using the same entity for different user cases](https://softwareengineering.stackexchange.com/questions/392047)

[Migration From Anemic Models to Rich Models](https://softwareengineering.stackexchange.com/questions/386432)
