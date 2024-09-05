package behavioral.observerPattern.observerPkg;

// Concrete SMS Observer Class
public class SMSObserver implements Observer {
    private String phoneNumber;

    public SMSObserver(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String weatherUpdate) {
        System.out.println("SMS to " + phoneNumber + ": Weather Update - " + weatherUpdate);
    }
}