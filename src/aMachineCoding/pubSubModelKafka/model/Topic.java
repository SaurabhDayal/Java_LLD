package aMachineCoding.pubSubModelKafka.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

    private final String topicId;   // Unique identifier for the topic.
    private final String topicName; // Name of the topic, used for identification/display purposes.
    private final List<Message> messages; // List to store all messages published to this topic. This list is exposed to the outside using an immutable getter.

    public Topic(final String topicName, final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    public synchronized List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }
}