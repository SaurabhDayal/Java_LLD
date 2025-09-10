package aMachineCoding.pubSubModelKafka;

import aMachineCoding.pubSubModelKafka.Publisher.SimplePublisher;
import aMachineCoding.pubSubModelKafka.Subscriber.SimpleSubscriber;
import aMachineCoding.pubSubModelKafka.controller.KafkaController;
import aMachineCoding.pubSubModelKafka.model.Message;
import aMachineCoding.pubSubModelKafka.model.Topic;
import aMachineCoding.pubSubModelKafka.model.TopicPublisher;

public class Main {
    public static void main(String[] args) {

        KafkaController kafkaController = new KafkaController();

        // Create topics
        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        // Create subscribers
        SimpleSubscriber subscriber1 = new SimpleSubscriber("Subscriber1");
        SimpleSubscriber subscriber2 = new SimpleSubscriber("Subscriber2");
        SimpleSubscriber subscriber3 = new SimpleSubscriber("Subscriber3");

        // Subscribe
        kafkaController.registerSubscriber(subscriber1, topic1.getTopicId());
        kafkaController.registerSubscriber(subscriber1, topic2.getTopicId());
        kafkaController.registerSubscriber(subscriber2, topic1.getTopicId());
        kafkaController.registerSubscriber(subscriber3, topic2.getTopicId());

        // Create publishers
        SimplePublisher publisher1 = new SimplePublisher("Publisher1");
        SimplePublisher publisher2 = new SimplePublisher("Publisher2");

        // Register publishers with topics
        TopicPublisher t1p1 = kafkaController.registerPublisher(publisher1, topic1.getTopicId());
        TopicPublisher t2p1 = kafkaController.registerPublisher(publisher1, topic2.getTopicId());
        TopicPublisher t2p2 = kafkaController.registerPublisher(publisher2, topic2.getTopicId());
        
        kafkaController.publishMessage(t1p1, new Message("Message m1"));
        kafkaController.publishMessage(t2p1, new Message("Message m2"));
        kafkaController.publishMessage(t2p2, new Message("Message m3"));

        // Allow time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }

        kafkaController.publishMessage(t2p2, new Message("Message m4"));
        kafkaController.publishMessage(t1p1, new Message("Message m5"));

        // Reset offset
        kafkaController.resetOffset(topic1.getTopicId(), subscriber1, 0);

        // Allow time before shutdown
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }

        kafkaController.shutdown();
    }
}
