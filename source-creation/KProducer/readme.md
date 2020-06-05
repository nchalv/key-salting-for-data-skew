## Custom Kafka Producer written in Java

This small project has been created for benchmarking purposes and provides a
custom, configurable Kafka Producer. The producer's properties can be set in
the `kproducer.properties` resource.

The project is built using *maven*. Executing `mvn package` creates a `.jar`
containing all dependecies.


The producer can be executed with: `java -jar KafkaProducer-1.0-SNAPSHOT-jar-with-dependencies.jar`.
