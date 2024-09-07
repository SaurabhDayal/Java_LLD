package behavioral.observerPattern.observerPkg;

// Concrete Email Observer Class
public class EmailObserver implements Observer {
    private String email;
    private String updateType;  // This will specify whether it's a weather or news update

    public EmailObserver(String email, String updateType) {
        this.email = email;
        this.updateType = updateType;  // "Weather" or "News"
    }

    @Override
    public void update(String updateMessage) {
        System.out.println("Email ---> " + email + ": " + updateType + " Update ---> " + updateMessage);
    }
}
