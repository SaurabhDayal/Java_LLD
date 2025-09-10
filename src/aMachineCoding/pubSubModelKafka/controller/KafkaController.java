package aMachineCoding.pubSubModelKafka.controller;

import aMachineCoding.pubSubModelKafka.Publisher.Publisher;
import aMachineCoding.pubSubModelKafka.Subscriber.Subscriber;
import aMachineCoding.pubSubModelKafka.model.Message;
import aMachineCoding.pubSubModelKafka.model.Topic;
import aMachineCoding.pubSubModelKafka.model.TopicPublisher;
import aMachineCoding.pubSubModelKafka.model.TopicSubscriber;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaController {

    private final Map<String, Topic> topics;                             // Map of topic IDs to Topic objects
    private final Map<String, List<TopicSubscriber>> topicSubscribers;   // Subscribers per topic
    private final Map<String, List<TopicPublisher>> topicPublishers;     // Publishers per topic
    private final ExecutorService subscriberExecutor;                    // For running subscriber workers
    private final AtomicInteger topicIdCounter;

    public KafkaController() {
        topics = new ConcurrentHashMap<>();
        topicSubscribers = new ConcurrentHashMap<>();
        topicPublishers = new ConcurrentHashMap<>();
        subscriberExecutor = Executors.newCachedThreadPool();  // Dynamic thread pool
        topicIdCounter = new AtomicInteger(0);
    }

    // Create a new topic
    public Topic createTopic(String topicName) {
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        topicPublishers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Created topic: " + topicName + " with id: " + topicId);
        return topic;
    }

    // Register a subscriber to a topic
    public void registerSubscriber(Subscriber subscriber, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }
        TopicSubscriber ts = new TopicSubscriber(topic, subscriber);
        topicSubscribers.get(topicId).add(ts);
        subscriberExecutor.submit(new SubscriberWorker(ts));
        System.out.println("Subscriber " + subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    // Register a publisher to a topic
    public TopicPublisher registerPublisher(Publisher publisher, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return null;
        }
        TopicPublisher tp = new TopicPublisher(topic, publisher);
        topicPublishers.get(topicId).add(tp);
        System.out.println("Publisher " + publisher.getId() + " registered to topic: " + topic.getTopicName());
        return tp;
    }

    // Called by TopicPublisher when publishing a message
    public void publishMessage(TopicPublisher tp, Message message) {
        Topic topic = tp.getTopic();
        topic.addMessage(message);

        System.out.println("Publisher " + tp.getPublisher().getId() +
                " published message \"" + message.getMessage() + "\"" +
                " to topic " + topic.getTopicName() +
                " (id=" + topic.getTopicId() + ")");

        // Wake up subscribers
        List<TopicSubscriber> subs = topicSubscribers.get(topic.getTopicId());
        for (TopicSubscriber topicSubscriber : subs) {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
    }

    // Reset subscriber offset
    public void resetOffset(String topicId, Subscriber subscriber, int newOffset) {
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if (subscribers == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }
        for (TopicSubscriber ts : subscribers) {
            if (ts.getSubscriber().getId().equals(subscriber.getId())) {
                
                System.out.println("Offset for subscriber " + subscriber.getId() +
                        " on topic " + ts.getTopic().getTopicName() +
                        " reset to " + newOffset);

                ts.getOffset().set(newOffset);
                synchronized (ts) {
                    ts.notify();
                }
                break;
            }
        }
    }

    // Shutdown controller
    public void shutdown() {
        subscriberExecutor.shutdown();
        try {
            if (!subscriberExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                subscriberExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            subscriberExecutor.shutdownNow();
        }
    }
}
