package aMachineCoding.pubSubModelKafka.Publisher;


import aMachineCoding.pubSubModelKafka.controller.KafkaController;
import aMachineCoding.pubSubModelKafka.model.Message;


public class SimplePublisher implements Publisher {

    private final String id;
    private final KafkaController kafkaController;

    public SimplePublisher(String id, KafkaController kafkaController) {
        this.id = id;
        this.kafkaController = kafkaController;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void publish(String topicId, Message message) throws IllegalArgumentException {
        kafkaController.publish(this, topicId, message);
        System.out.println("Publisher " + id + " published: " + message.getMessage() + " to topic " + topicId);
    }
}
