package aMachineCoding.pubSubModelKafka.model;

import aMachineCoding.pubSubModelKafka.Publisher.Publisher;

public class TopicPublisher {
    private final Topic topic;
    private final Publisher publisher;

    public TopicPublisher(Topic topic, Publisher publisher) {
        this.topic = topic;
        this.publisher = publisher;
    }

    public Topic getTopic() {
        return topic;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
