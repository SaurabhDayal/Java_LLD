package aMachineCoding.pubSubModelKafka.Publisher;

public class SimplePublisher implements Publisher {

    private final String id;

    public SimplePublisher(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
