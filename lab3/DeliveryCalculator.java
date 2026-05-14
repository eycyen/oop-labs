import java.util.Scanner;

public class DeliveryCalculator {

    public enum CustomerType {
        REGULAR,
        PREMIUM
    }

    public static CustomerType parseCustomerType(String input) {
        if (input == null) {
            return null;
        }

        input = input.toUpperCase();

        if (input.equals("REGULAR")) {
            return CustomerType.REGULAR;
        }
        else if (input.equals("PREMIUM")) {
            return CustomerType.PREMIUM;
        }

        return null;
    }

    public static double calculateCost(double weight, double distance) {
        return calculateCost(weight, distance, false, CustomerType.REGULAR);
    }

    public static double calculateCost(double weight, double distance, boolean isExpress) {
        return calculateCost(weight, distance, isExpress, CustomerType.REGULAR);
    }

    public static double calculateCost(double weight, double distance, boolean isExpress, CustomerType customerType) {
        double cost = 0.0;

        if (weight <= 5) {
            cost += 10.0;
        } else if (weight <= 20) {
            cost += 20.0;
        } else {
            cost += 40.0;
        }

        if (distance > 50) {
            cost += (distance - 50) * 0.5;
        }

        if (isExpress) {
            cost *= 1.5;
        }

        if (customerType == CustomerType.PREMIUM) {
            cost *= 0.8;
        }

        return cost;
    }

    public static String findCheapestOption(double weight, double distance) {
        double minCost = calculateCost(weight, distance, false, CustomerType.REGULAR);
        String bestOption = "Normal Delivery for Regular Customer";

        double cost2 = calculateCost(weight, distance, true, CustomerType.REGULAR);
        if (cost2 < minCost) {
            minCost = cost2;
            bestOption = "Express Delivery for Regular Customer";
        }

        double cost3 = calculateCost(weight, distance, false, CustomerType.PREMIUM);
        if (cost3 < minCost) {
            minCost = cost3;
            bestOption = "Normal Delivery for Premium Customer";
        }

        double cost4 = calculateCost(weight, distance, true, CustomerType.PREMIUM);
        if (cost4 < minCost) {
            minCost = cost4;
            bestOption = "Express Delivery for Premium Customer";
        }

        return bestOption;
    }
    public static void main(String args[]) {
        double weight;
        double distance;
        boolean isExpress;
        CustomerType customerType;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter weight (kg) : ");
            weight = scanner.nextDouble();
            if (weight < 0) {
                System.out.println("Weight cannot be negative. Try again.");
            }
        } while (weight < 0);

        do {
            System.out.print("Enter distance (km) : ");
            distance = scanner.nextDouble();
            if (distance < 0) {
                System.out.println("Distance cannot be negative. Try again.");
            }
        } while (distance < 0);

        System.out.print("Is express delivery ? (true/false) : ");
        isExpress = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter customer type (regular/premium) : ");
        String customerTypeStr = scanner.nextLine();
            
        customerType = parseCustomerType(customerTypeStr);

        double finalCost = calculateCost(weight, distance,isExpress,customerType);
        System.out.println("Total Cost: " + finalCost + "TL");
        String bestOption = findCheapestOption(weight, distance);
        System.out.println("Best Option: " + bestOption);


        scanner.close();

    }
}
