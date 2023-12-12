import java.util.ArrayList;
import java.util.List;

// Singleton pattern
class OrderProcessorSingleton {
    private static OrderProcessorSingleton instance;

    private OrderProcessorSingleton() {
    }

    public static OrderProcessorSingleton getInstance() {
        if (instance == null) {
            instance = new OrderProcessorSingleton();
        }
        return instance;
    }

    public void processOrder(Order order) {
        System.out.println("Processing order: " + order);
    }
}

// Factory pattern
interface PaymentProcessor {
    void processPayment();
}

class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment");
    }
}

class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment");
    }
}

class PaymentProcessorFactory {
    public PaymentProcessor createPaymentProcessor(String paymentType) {
        switch (paymentType) {
            case "credit card":
                return new CreditCardPaymentProcessor();
            case "paypal":
                return new PayPalPaymentProcessor();
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// Adapter pattern
class LegacyOrderSystem {
    public void legacyProcessOrder() {
        System.out.println("Processing order in legacy system");
    }
}

class LegacyOrderAdapter implements Order {
    private LegacyOrderSystem legacyOrderSystem;

    public LegacyOrderAdapter(LegacyOrderSystem legacyOrderSystem) {
        this.legacyOrderSystem = legacyOrderSystem;
    }

    @Override
    public void process() {
        legacyOrderSystem.legacyProcessOrder();
    }
}

// Facade pattern
class OrderFacade {
    private OrderProcessorSingleton orderProcessor;
    private PaymentProcessorFactory paymentProcessorFactory;

    public OrderFacade() {
        this.orderProcessor = OrderProcessorSingleton.getInstance();
        this.paymentProcessorFactory = new PaymentProcessorFactory();
    }

    public void processOrderAndPayment(Order order, String paymentType) {
        orderProcessor.processOrder(order);
        PaymentProcessor paymentProcessor = paymentProcessorFactory.createPaymentProcessor(paymentType);
        paymentProcessor.processPayment();
    }
}

// Command pattern
interface Order {
    void process();
}

class OnlineOrder implements Order {
    @Override
    public void process() {
        System.out.println("Processing online order");
    }
}

class InStoreOrder implements Order {
    @Override
    public void process() {
        System.out.println("Processing in-store order");
    }
}

class OrderCommand {
    private Order order;

    public OrderCommand(Order order) {
        this.order = order;
    }

    public void execute() {
        order.process();
    }
}

public class OrderProcessApp {
    public static void main(String[] args) {
        // Singleton pattern
        OrderProcessorSingleton orderProcessor = OrderProcessorSingleton.getInstance();

        // Factory pattern
        PaymentProcessorFactory paymentProcessorFactory = new PaymentProcessorFactory();
        PaymentProcessor creditCardPaymentProcessor = paymentProcessorFactory.createPaymentProcessor("credit card");
        PaymentProcessor paypalPaymentProcessor = paymentProcessorFactory.createPaymentProcessor("paypal");

        // Adapter pattern
        LegacyOrderSystem legacyOrderSystem = new LegacyOrderSystem();
        Order legacyOrderAdapter = new LegacyOrderAdapter(legacyOrderSystem);

        // Facade pattern
        OrderFacade orderFacade = new OrderFacade();
        Order onlineOrder = new OnlineOrder();
        Order inStoreOrder = new InStoreOrder();
        orderFacade.processOrderAndPayment(onlineOrder, "credit card");
        orderFacade.processOrderAndPayment(inStoreOrder, "paypal");

        // Command pattern
        OrderCommand onlineOrderCommand = new OrderCommand(onlineOrder);
        OrderCommand inStoreOrderCommand = new OrderCommand(inStoreOrder);
        onlineOrderCommand.execute();
        inStoreOrderCommand.execute();
    }
}
