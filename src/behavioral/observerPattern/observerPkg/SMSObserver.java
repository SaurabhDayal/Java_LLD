package behavioral.observerPattern.observerPkg;

import behavioral.observerPattern.observablePkg.Observable;

// Concrete SMS Subscriber Class
public class SMSObserver implements Observer {

    private final String phoneNumber;
    private final Observable observable;  // Reference to the Observable/Publisher type (Weather or News)

    public SMSObserver(String phoneNumber, Observable observable) {
        this.phoneNumber = phoneNumber;
        this.observable = observable;
    }

    @Override
    public void update(String updateMessage) {
        // This method is triggered when the observable/publisher sends an update.
        // The actual logic here simulates sending a sms notification.
        System.out.println("SMS   --->    " + phoneNumber + " :            " + observable.getName() + " Update ---> " + updateMessage);
    }
}
