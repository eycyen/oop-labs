public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) throws PaymentException {
        if (amount < 10) {
            throw new PaymentException("Amount must be at least 10.0");
        }
        System.out.println("Processing PayPal payment of " + amount);
    }
}