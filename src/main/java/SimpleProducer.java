import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducer {

    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    public static void main(String[] args) {
        Properties configs = new Properties();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AccessInformation.BOOTSTRAP_SERVERS.getInfo());

        // 메세지 Key, Value를 직렬화 하기 위해 StringSerializer 사용
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<>(configs);

        String message = "Hello Grayson!";

        // kafka broker로 데이터를 보내기 위한 ProducerRecord 생성
        ProducerRecord<String,String> record = new ProducerRecord<>(AccessInformation.TOPIC_NAME.getInfo(),message);

        // 즉각 전송하는 것이 아닌 배치형태로 묶어 브로커로 전송
        producer.send(record);

        logger.info("{}", record);

        producer.flush();
        producer.close();
    }

}
