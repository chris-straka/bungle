In my API, it's not feasible to have DI for my rich data models in some cases. 
If I have a route that creates a new Entity, I have to instantiate it somewhere and then save it via an entityRepository.
I don't know how many entities I'll need on startup. I do know I will need a single repo on startup though

[Using the same entity for different user cases](https://softwareengineering.stackexchange.com/questions/392047)

[Migration From Anemic Models to Rich Models](https://softwareengineering.stackexchange.com/questions/386432)
