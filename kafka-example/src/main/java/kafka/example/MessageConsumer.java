package kafka.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer implements MessageListener<String, String> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("record = {}", record);
    }
}
