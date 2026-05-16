public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) throws PaymentException {
        if (amount > 5000) {
            throw new PaymentException("Amount cannot exceed 5000.0");
        }
        System.out.println("Processing credit card payment of " + amount);
    }
}