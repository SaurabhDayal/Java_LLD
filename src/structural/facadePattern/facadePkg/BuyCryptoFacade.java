package structural.facadePattern.facadePkg;

import structural.facadePattern.subSystemPkg.*;

// Facade class
public class BuyCryptoFacade {

    public void buyCryptocurrency(double amount, String currency) {

        // Database service to get user details
        DatabaseService dbService = new DatabaseService();

        // Get the logged-in user
        User user = dbService.getUser(UIService.getLoggedInUserId());

        // Check if the user has enough balance
        if (user.balance() < amount) {
            System.out.println("Insufficient balance to perform transaction");
            return;
        }

        // Get the appropriate crypto service and buy the currency
        CryptoFactory.getCryptoService(currency).buyCurrency(user, amount);

        // Send a confirmation email
        MailService mailService = new MailService();
        mailService.sendConfirmationMail(user);

        // Transaction success message
        System.out.println(amount + " of " + currency + " bought successfully!");
    }
}