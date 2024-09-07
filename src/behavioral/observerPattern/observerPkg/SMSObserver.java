package behavioral.observerPattern.observerPkg;

// Concrete SMS Observer Class
public class SMSObserver implements Observer {
    private String phoneNumber;
    private String updateType;  // This will be injected via constructor to differentiate between Weather and News

    public SMSObserver(String phoneNumber, String updateType) {
        this.phoneNumber = phoneNumber;
        this.updateType = updateType;  // "Weather" or "News"
    }

    @Override
    public void update(String updateMessage) {
        System.out.println("SMS   ---> " + phoneNumber + ": " + updateType + " Update ---> " + updateMessage);
    }
}
