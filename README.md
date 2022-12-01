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

TODO:
 - Integration tests, concept:
     - setup Kafka (external script started prior to test)
     - prepare sample data in /test/resources
     - call `numbersProcessingStream(...)`
     - add client that will read data from Kafka
     - check results 