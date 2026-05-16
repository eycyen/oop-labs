public class CryptoProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) throws PaymentException {
        if (amount % 5 != 0) {
            throw new PaymentException("Amount must be divisible by 5");
        }
        System.out.println("Processing crypto payment of " + amount);
    }
}