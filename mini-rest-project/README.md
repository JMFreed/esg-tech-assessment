# Mini REST project

Console app that takes a single argument, which can be a relative path to a CSV file.

CsvParser class parses CSV file into Collection of DTO objects (see test.csv)

Spring Boot Data JPA connection to a postgreSQL database running in docker container.

CustomerController endpoints:
 - GET /api/v1/customers
 - GET /api/v1/customers?customerRef=<customerRef>
 - POST /api/v1/customers

CustomerService uses Spring webflux WebClient to call the REST API controller endpoints.

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
