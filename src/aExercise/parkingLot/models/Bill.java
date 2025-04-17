package aExercise.parkingLot.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill extends BaseModel {
    private Date exitTime;
    private int amount;
    private Ticket ticket;
    private Operator operator;
    private BillStatus billStatus;
    private List<Payment> payments;

    public Bill() {
        // Initialize payments list to avoid NullPointerException
        this.payments = new ArrayList<>();
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    // Method to add a payment to the bill
    public void addPayment(Payment payment) {
        if (payment != null) {
            this.payments.add(payment);
            // Update bill status based on the payment
            updateBillStatus();
        }
    }

    // Method to update the bill status based on payments
    public void updateBillStatus() {
        if (payments.isEmpty()) {
            this.billStatus = BillStatus.PENDING; // Bill is pending if no payments
        } else {
            // Calculate the total paid amount
            int totalPaid = payments.stream().mapToInt(Payment::getAmount).sum();

            // If the total paid is equal to or greater than the bill amount, the bill is PAID
            if (totalPaid >= amount) {
                this.billStatus = BillStatus.PAID;
            } else {
                this.billStatus = BillStatus.PARTIALLY_PAID; // Bill is partially paid
            }
        }
    }
}
