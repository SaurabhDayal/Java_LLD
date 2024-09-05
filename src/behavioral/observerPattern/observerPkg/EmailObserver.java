package behavioral.observerPattern.observerPkg;

// Concrete Email Observer Class
public class EmailObserver implements Observer {
    private String email;

    public EmailObserver(String email) {
        this.email = email;
    }

    @Override
    public void update(String weatherUpdate) {
        System.out.println("Email to " + email + ": Weather Update - " + weatherUpdate);
    }
}