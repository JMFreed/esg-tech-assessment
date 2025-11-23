# Mini REST project

[ConsoleApp](src/main/java/com/jfreed/esg/ConsoleApp.java) takes a single argument, which can be an absolute or relative path to a CSV file.

[CsvParser](src/main/java/com/jfreed/esg/parser/CsvParser.java) parses CSV file into Collection of [Customer](src/main/java/com/jfreed/esg/dto/Customer.java) objects

Spring Boot Data JPA connection to a postgreSQL database running in docker container (see [docker-compose.yaml](docker/postgresql/docker-compose.yaml).

[CustomerController](src/main/java/com/jfreed/esg/customer/CustomerController.java) endpoints:
 - GET /api/v1/customers
 - GET /api/v1/customers?customerRef=<customerRef>
 - POST /api/v1/customers

[CustomerMapper](src/main/java/com/jfreed/esg/customer/CustomerMapper.java) maps the [CustomerEntity](src/main/java/com/jfreed/esg/customer/CustomerEntity.java) to [Customer](src/main/java/com/jfreed/esg/dto/Customer.java)

[CustomerService](src/main/java/com/jfreed/esg/customer/CustomerService.java) uses Spring webflux WebClient to call the REST API controller endpoints.

## How to run

### Spin up postgreSQL container

```shell
docker-compose -f docker/postgresql/docker-compose.yaml up -d
```

### Access postgreSQL container
```shell
psql -h localhost -U esg_admin -p 5435 -d esg
```

### Run console application

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="data/test.csv"
```

### Notes

- Hardcoded credentials in postgreSQL container and application.properties for simplicity.
- Using synchronous blocking in the webclient for simplicity
- Did not implement API endpoints for updating/deleting customer entities
- Did not implement any search criteria pattern to allow finding customers by town, country, postcode, etc