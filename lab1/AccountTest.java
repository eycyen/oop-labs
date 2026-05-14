class Account {
    private String name;
    private double balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    public Account(String name, double balance) {
        this.name = name;
        if (balance > 0.0) {
            this.balance = balance;
        }
        else {
            this.balance = 0.0;
            System.out.println("Balance should be bigger than 0!");
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0.0) {
            System.err.println("The amount should be bigger than zero!");
        }
        else {
            balance += amount;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account account1 = new Account("Jane Green",50.00);
        Account account2 = new Account("John Blue",-7.53);
        account1.deposit(25.53);
        System.out.printf("%s's balance is: %.2f\n",account1.getName(),account1.getBalance());
        account2.deposit(10.00);
        System.out.printf("%s's balance is: %.2f\n",account2.getName(),account2.getBalance());

        String studentName = "Canbolat";
        Account myAccount = new Account("Test Account", 100);
        Account mySecondAccount = new Account("Second Test Account", 200);
        
        myAccount.setName("New Name");
        studentName.toUpperCase();
        
        System.out.println(mySecondAccount.getName());
    }
}