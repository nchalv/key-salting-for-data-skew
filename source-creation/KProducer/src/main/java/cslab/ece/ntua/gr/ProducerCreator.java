//package cslab.ece.ntua.gr;
////import util.properties packages
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
//
////import simple producer packages
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.LongSerializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//
//
//public class ProducerCreator {
//
//    public static Producer<Long, String> createProducer() throws IOException {
//        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String appConfigPath = rootPath + "app.properties";
//
//        Properties appProps = new Properties();
//        appProps.load(new FileInputStream(appConfigPath));
//
//
//
//
//        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
//
//        //props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
//
//        //props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
//
//        //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
//
//        return new KafkaProducer<>(appProps);
//
//    }
//
//}