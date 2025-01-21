package behavioral.observerPattern.observerPkg;

import behavioral.observerPattern.observablePkg.Observable;

// Concrete Email Subscriber Class
public class EmailObserver implements Observer {

    private final String email;
    private final Observable observable;  // Reference to the Observable/Publisher type (Weather or News)

    public EmailObserver(String email, Observable observable) {
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update(String updateMessage) {
        // This method is triggered when the observable/publisher sends an update.
        // The actual logic here simulates sending an email notification.
        System.out.println("Email --->    " + email + " :    " + observable.getName() + " Update ---> " + updateMessage);
    }
}
