Prerequisites: `sbt`, `docker-compose`

Kafka setup:

```
docker-compose up
```

Create kafka topic:

```
docker exec fs2-kafka kafka-topics.sh --create --topic "numbers" --bootstrap-server kafka:9092
```

Create console consumer to monitor kafka topic:

```
kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic test --from-beginning
```

Run processing:

```
sbt run
```

Run tests:

```
sbt test
```