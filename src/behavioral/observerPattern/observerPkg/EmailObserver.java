package behavioral.observerPattern.observerPkg;

import behavioral.observerPattern.observablePkg.Observable;

// Concrete Email Observer Class
public class EmailObserver implements Observer {
    private String email;
    private Observable observable;  // Reference to the observable type (Weather or News)

    public EmailObserver(String email, Observable observable) {
        this.email = email;
        this.observable = observable;
    }

    @Override
    public void update(String updateMessage) {
        System.out.println("Email --->    " + email + " :    " + observable.getName() + " Update ---> " + updateMessage);
    }
}
