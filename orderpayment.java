import java.util.ArrayList;
import java.util.List;

// Example Payment class
class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // getters and setters for amount...
}

// MoneyTrackerManager class
public class MoneyTrackerManager {
    private List<Payment> paymentList;

    public MoneyTrackerManager() {
        this.paymentList = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    // Other methods for managing money tracking...
}
