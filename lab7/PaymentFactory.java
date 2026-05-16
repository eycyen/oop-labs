public class PaymentFactory {
    private PaymentFactory() {
    }

    public static PaymentProcessor getPaymentProcessor(String type) {
        switch (type.toLowerCase()) {
            case "creditcard":
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
