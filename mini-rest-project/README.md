# Mini REST project

## How to run

### Spin up postgreSQL container

```shell
docker-compose -f docker/postgresql/docker-compose.yaml up -d
```

### Run console application

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="data/test.csv"
```
