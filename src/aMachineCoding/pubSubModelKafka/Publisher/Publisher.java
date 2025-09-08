package aMachineCoding.pubSubModelKafka.Publisher;

import aMachineCoding.pubSubModelKafka.model.Message;

public interface Publisher {
    String getId();

    void publish(String topicId, Message message) throws IllegalArgumentException;
}
