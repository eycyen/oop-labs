public class PaymentFactory {
    private PaymentFactory() {
    }

    public static PaymentProcessor createProcessor(String type) {
        switch (type.toLowerCase()) {
            case "credit":
                return new CreditCardProcessor();
            case "paypal":
                return new PayPalProcessor();
            case "crypto":
                return new CryptoProcessor();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
 }
