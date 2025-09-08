package aMachineCoding.pubSubModelKafka.controller;


import aMachineCoding.pubSubModelKafka.Publisher.Publisher;
import aMachineCoding.pubSubModelKafka.model.Message;
import aMachineCoding.pubSubModelKafka.model.Topic;


public class TopicPublisherController {
    private final Topic topic;
    private final Publisher publisher;

    public TopicPublisherController(Topic topic, Publisher publisher) {
        this.topic = topic;
        this.publisher = publisher;
    }

    // Synchronized publish method ensures thread-safe publishing for this topic.
    public synchronized void publish(Message message, KafkaController controller) {
        controller.publish(publisher, topic.getTopicId(), message);
        System.out.println("Publisher " + publisher.getId() + " published to topic " + topic.getTopicName());
    }
}
