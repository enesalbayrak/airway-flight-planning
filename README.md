# airway-flight-planning

Definitions of values:
sourceAirportCode and destinationAirportCode are bounded between 100 and 181.These numbers represent the license plates of the cities.
airlineCode represents airline company.There are no restrictions.
PNR is a unique value for flight information.

Business Logic:
There can be daily three flights between two destinations.
The place of source and the place of destination cannot be the same.


I used libraries:
Lombok:I used it to increase code readability and write speed
ModelMapper: I used it for conversion of entities to data transfer objects.
Openapi:I used it to check endpoints
