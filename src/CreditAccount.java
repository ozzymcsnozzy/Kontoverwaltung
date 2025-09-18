public class CreditAccount extends BankAccount {
    public CreditAccount(String accHolder, String BIC, String accNumber, float balance) {
        super(accHolder, BIC, accNumber, balance);
        setAccType(AccountType.CREDIT);
    }

    @Override
    public void deposit(float amount) {
        if (getBalance() + amount <= 0F) {
            deposit(amount);
        } else {
            Float overZero = getBalance() + amount;
            System.out.println("Deposit denied: credit accounts cannot have a positive balance." +
                    "With your amount input you would overpay your Credit by " + overZero + "€");
        }
    }

    @Override
    public void withdraw(float amount) {
        setBalance(getBalance() - amount);
        System.out.println("New balance: " + getBalance());
    }
}
