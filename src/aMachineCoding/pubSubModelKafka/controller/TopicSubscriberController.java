package aMachineCoding.pubSubModelKafka.controller;


import aMachineCoding.pubSubModelKafka.Subscriber.Subscriber;
import aMachineCoding.pubSubModelKafka.model.Message;
import aMachineCoding.pubSubModelKafka.model.Topic;
import aMachineCoding.pubSubModelKafka.model.TopicSubscriber;

public class TopicSubscriberController implements Runnable {
    private final TopicSubscriber topicSubscriber;

    public TopicSubscriberController(TopicSubscriber topicSubscriber) {
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        Topic topic = topicSubscriber.getTopic();
        Subscriber subscriber = topicSubscriber.getSubscriber();
        while (true) {
            Message messageToProcess = null;
            synchronized (topicSubscriber) {
                // Wait until there is a new message (offset is less than the number of messages)
                while (topicSubscriber.getOffset().get() >= topic.getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // Retrieve the next message and increment the offset
                int currentOffset = topicSubscriber.getOffset().getAndIncrement();
                messageToProcess = topic.getMessages().get(currentOffset);
            }
            // Process the message outside of the synchronized block
            try {
                subscriber.onMessage(messageToProcess);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
