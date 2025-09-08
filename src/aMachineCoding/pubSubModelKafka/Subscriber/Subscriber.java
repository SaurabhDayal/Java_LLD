package aMachineCoding.pubSubModelKafka.Subscriber;

import aMachineCoding.pubSubModelKafka.model.Message;

public interface Subscriber {
    String getId();

    void onMessage(Message message) throws InterruptedException;
}
