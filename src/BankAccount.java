public class BankAccount {
    private String accHolder;
    private String BIC;
    private String accNumber;
    private float accManagementFees; //Not Used
    protected float balance;
    private AccountType accType;

    BankAccount(String accHolder, String BIC, String accNumber, float balance) {
        this.accHolder = accHolder;
        this.BIC = BIC;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public void setAccType(AccountType accType) {
        this.accType = accType;
    }

    public void deposit(float amount) {
        balance += amount;
        System.out.println("New balance: " + balance);
    }

    public void withdraw(float amount) {
        if (balance > amount) {
            balance -= amount;
            System.out.println("New balance:" + balance);
        } else {
            System.out.println("Withdrawal denied, your balance is not high enough");
        }
    }

    @Override
    public String toString() {
        return ("Account Holder: " + accHolder + "\n"
                + "Balance: " + balance + "\n"
                + "BIC: " + BIC + "\n"
                + "Account Number: " + accNumber + "\n"
                + "Account Type: " + accType + "\n");
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccHolder() {
        return accHolder;
    }

    public String getBIC() {
        return BIC;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
