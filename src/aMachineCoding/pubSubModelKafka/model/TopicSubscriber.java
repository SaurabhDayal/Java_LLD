package aMachineCoding.pubSubModelKafka.model;

import aMachineCoding.pubSubModelKafka.Subscriber.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {

    private final Topic topic;
    private final Subscriber subscriber;
    private final AtomicInteger offset;

    public TopicSubscriber(Topic topic, Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    public Topic getTopic() {
        return topic;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
