package challenge_6_Classes.BankAccount;

public class Main {
    public static void main(String[] args) {
        // instance of an Account class with sample data
        Account account = new Account(
                "EE1234567890",
                1000.00,
                "Alice Johnson",
                "alice@example.com",
                "+3725550000");

        System.out.printf("Starting balance: %.2f%n", account.getAccountBalance());

        account.deposit(250.00);
        account.withdraw(100.00);
        account.withdraw(2000.00);

        System.out.printf("Final balance: %.2f%n", account.getAccountBalance());
    }
}

// --- Expected Output ---
/*
Starting balance: 1000.00
Deposited 250.00. New balance: 1250.00
Withdrew 100.00. New balance: 1150.00
Insufficient funds for withdrawal.
Final balance: 1150.00
*/