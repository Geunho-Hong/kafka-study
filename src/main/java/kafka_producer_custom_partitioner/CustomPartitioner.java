package kafka_producer_custom_partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(keyBytes == null) {
            throw new InvalidRecordException("Need Message Key");
        }

        // Grayson이라는 Key값이 들어오면 파티션 0번으로 지정되도록 0을 리턴한다다
       if(((String)key).equals("Grayson")) {
            return 0;
        }
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numOfPartitons = partitions.size();
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numOfPartitons;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
