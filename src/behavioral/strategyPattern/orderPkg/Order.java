package behavioral.strategyPattern.orderPkg;

import behavioral.strategyPattern.strategiesPkg.PayStrategy;

// Order class. Doesn't know the concrete payment method (strategy) user has picked.
// It uses the common strategy interface to delegate collecting payment data to the strategy object.
public class Order {

    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
        // Here we could collect and store payment data from the strategy.
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}