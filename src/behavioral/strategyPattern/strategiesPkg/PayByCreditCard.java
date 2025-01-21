package behavioral.strategyPattern.strategiesPkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PayByCreditCard implements PayStrategy {

    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        try {

            System.out.print("Enter the card number: ");
            String number = READER.readLine();
            number = number.trim();

            System.out.print("Enter the card expiration date 'mm/yy': ");
            String date = READER.readLine();
            date = date.trim();

            System.out.print("Enter the CVV code: ");
            String cvv = READER.readLine();
            cvv = cvv.trim();

            card = new CreditCard(number, date, cvv);

            // You can add additional validation logic here (e.g., check if the card details are valid)

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}