public class SavingsAccount extends BankAccount {
    SavingsAccount(String accHolder, String BIC, String accNumber, float balance) {
        super(accHolder, BIC, accNumber, balance);
        setAccType(AccountType.SAVINGS);
    }

    @Override
    public void withdraw(float amount) {
        if (getBalance() - amount > 0F) {
            setBalance(getBalance() - amount);
            System.out.println("New Savings balance: " + getBalance());
        } else {
            System.out.println("Withdrawal denied, can't negate savings");
        }
    }
}
