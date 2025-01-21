package behavioral.observerPattern.observerPkg;

import behavioral.observerPattern.observablePkg.Observable;

// Concrete SMS Subscriber Class
public class SMSObserver implements Observer {

    private final String phoneNumber;
    private final Observable observable;  // Reference to the observable type (Weather or News)

    public SMSObserver(String phoneNumber, Observable observable) {
        this.phoneNumber = phoneNumber;
        this.observable = observable;
    }

    @Override
    public void update(String updateMessage) {
        System.out.println("SMS   --->    " + phoneNumber + " :            " + observable.getName() + " Update ---> " + updateMessage);
    }
}
