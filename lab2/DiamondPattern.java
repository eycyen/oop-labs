import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.printf("Enter a positive and odd number: ");
            n = scanner.nextInt();

        } while (n%2 != 1 || n < 0);

        int rows = 2*n - 1;

        for (int i = 1 ; i <= rows ; i++) {
            int current = (i <= n) ? i : (2*n - i);

            for (int s = 0; s < (n - current); s++) {
                System.out.print(" "); 
            }

            if (current % 2 != 0) {
                for (int j = 1 ; j <= current ; j++) {
                    System.out.print(j);
                }

                for (int j = current - 1 ; j >= 1 ; j--) {
                    System.out.print(j);
                }
            }
            else {
                int count = 2 * current - 1;

                for (int j = 0 ; j < count ; j++) {
                    System.out.print("*");
                }
            }

            System.out.println();

        }

        scanner.close();



    }
}
