package challenges_6_Classes.BankAccount;

// Create an encapsulated class Account, where all fields are private
public class Account {
    private String accountNumber;
    private double accountBalance;
    private String customerName;
    private String email;
    private String phoneNumber;

    public Account(String accountNumber, double accountBalance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // method for depositing funds into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        accountBalance += amount;
        System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, accountBalance);
    }

    // method for withdrawing funds from the account
    public void withdraw(double amount) {
        //balance should not be negative
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (amount > accountBalance) {
            System.out.println("Insufficient funds for withdrawal.");
            return;
        }

        accountBalance -= amount;
        System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, accountBalance);
    }

    // --- getters ---
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // --- setters ---
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
