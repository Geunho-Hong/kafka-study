public enum AccessInformation {

    TOPIC_NAME("kafka_producer_custom_partitioner"),
    BOOTSTRAP_SERVERS("34.207.194.91:9092");

    public final String info;

    AccessInformation(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
