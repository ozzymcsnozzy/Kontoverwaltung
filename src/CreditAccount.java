public class CreditAccount extends BankAccount {

    public CreditAccount(String accHolder, String BIC, String accNumber, float balance) {
        super(accHolder, BIC, accNumber, balance);
    }

    @Override
    public void deposit(float amount) {
        if (getBalance() + amount <= 0F) {
            super.deposit(amount);
        } else {
            System.out.println("Deposit denied: credit accounts cannot have a positive balance.");
        }
    }

    @Override
    public void withdraw(float amount) {
        setBalance(getBalance() - amount);
        System.out.println("New balance: " + getBalance());
    }
}
