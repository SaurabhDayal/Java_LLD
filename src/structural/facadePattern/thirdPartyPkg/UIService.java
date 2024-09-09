package structural.facadePattern.thirdPartyPkg;

public class UIService {

    public static String getLoggedInUserId() {
        return "";
    }

    public void logIn(String username, String password) {
        System.out.println("Logging In...");
    }

    public void logout() {
        System.out.println("Logging Out...");
    }

}