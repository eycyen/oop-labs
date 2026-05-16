import java.util.Scanner;

public class SmartPaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
        System.out.println("Enter payment type:");
        String type = scanner.nextLine();

        System.out.println("Enter payment amount:");
        double amount = scanner.nextDouble();

        PaymentProcessor processor = PaymentFactory.createProcessor(type);
        processor.processPayment(amount);

        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (PaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            scanner.close();
        }
    }
}
