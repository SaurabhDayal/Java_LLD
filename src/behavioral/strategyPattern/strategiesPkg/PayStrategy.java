package behavioral.strategyPattern.strategiesPkg;

public interface PayStrategy {
    boolean pay(int paymentAmount);

    void collectPaymentDetails();
}