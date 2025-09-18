public class CheckingAccount extends BankAccount {
    private final float overdraftLimit;

    CheckingAccount(String accHolder, String BIC, String accNumber, float balance, float overdraftLimit) {
        super(accHolder, BIC, accNumber, balance);
        this.overdraftLimit = overdraftLimit;
        setAccType(AccountType.CHECKING);
    }

    @Override
    public void withdraw(float amount) {
        if (getBalance() - amount >= -overdraftLimit) {
            setBalance(getBalance() - amount);
            System.out.println("New balance: " + getBalance());
        } else {
            System.out.println("Withdrawal denied: overdraft limit reached.");
        }
    }
}
