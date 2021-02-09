package kafka.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private List<String> bootstrapServers;

    @Value("${group.id}")
    private String groupId;

    @Value("${enable.auto.commit}")
    private String enableAutoCommit;

    @Value("${session.timeout.ms}")
    private String sessionTimeoutMs;

    @Value("${max.poll.records}")
    private String maxPollRecords;

    @Value("${max.poll.interval.ms}")
    private String maxPollIntervalMs;

    @Value("${key.deserializer}")
    private String keyDeserializer;

    @Value("${value.deserializer}")
    private String valueDeserializer;

    @Value("${topics}")
    private String[] topics;

    @Autowired
    private MessageConsumer messageConsumer;

    @Bean
    public Map<String, Object> consumerProperties() {
        HashMap<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", enableAutoCommit);
        props.put("session.timeout.ms", sessionTimeoutMs);
        props.put("max.poll.records", maxPollRecords);
        props.put("max.poll.interval.ms", maxPollIntervalMs);
        props.put("key.deserializer", keyDeserializer);
        props.put("value.deserializer", valueDeserializer);

        return props;
    }

    @Bean
    public DefaultKafkaConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerProperties());
    }

    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties properties = new ContainerProperties(topics);
        properties.setMessageListener(messageConsumer);

        return properties;
    }

    @Bean
    public ConcurrentMessageListenerContainer messageListenerContainer() {
        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer(
                consumerFactory(), containerProperties());
        container.start();

        return container;
    }
}
