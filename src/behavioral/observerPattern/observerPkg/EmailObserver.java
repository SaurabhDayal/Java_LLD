package behavioral.observerPattern.observerPkg;

import behavioral.observerPattern.observablePkg.Observable;

// Concrete Email Subscriber Class
public class EmailObserver implements Observer {

    private final String email;
    private final Observable observable;  // Reference to the observable type (Weather or News)

    public EmailObserver(String email, Observable observable) {
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update(String updateMessage) {
        System.out.println("Email --->    " + email + " :    " + observable.getName() + " Update ---> " + updateMessage);
    }
}
