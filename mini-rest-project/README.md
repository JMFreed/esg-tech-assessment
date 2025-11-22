# Mini REST project

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
