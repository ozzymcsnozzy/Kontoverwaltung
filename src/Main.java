import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        CheckingAccount test1 = new CheckingAccount("Holder", "BIC", "Number", 500, 200);
        accounts.add(test1);

        int menu;
        do {
            PrintMenu();
            menu = ReadInt("Choose an option: ");
            switch (menu) {
                case 1 -> ChooseCreateAccount();
                case 2 -> DepositToAccount();
                case 3 -> WithdrawFromAccount();
                case 4 -> listAccounts();
                case 5 -> TransferToAccount();
                case 6 -> DeleteAccount();
                case 0 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid input, try again.");
            }
        } while (menu != 0);
        input.close();
    }

    //ACCOUNT ACTIONS
    private static void DeleteAccount(){
        if (CheckIfListEmpty()) {
            return;
        }

        listAccounts();
        int index = ReadInt("Select account number to delete: ") - 1;

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid account selection.\n");
            return;
        }

        BankAccount removed = accounts.remove(index);
        System.out.println("Account deleted: " + removed);
    }

    private static void TransferToAccount() {
        System.out.println("Select source account:");
        BankAccount from = SelectAccount("withdraw from");
        if (from == null) return;

        System.out.println("Select destination account:");
        BankAccount to = SelectAccount("deposit into");
        if (to == null) return;

        float amount = ReadFloat("Enter transfer amount: ");
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }

        from.withdraw(amount);
        to.deposit(amount);
        System.out.println("Transfer successful.");
    }

    private static void WithdrawFromAccount() {
        BankAccount account = SelectAccount("Withdraw from");
        if (account == null) return;

        float amount = ReadFloat("Enter withdraw amount: ");
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return;
        }

        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
    }

    private static void DepositToAccount() {
        BankAccount account = SelectAccount("deposit into");
        if (account == null) return;

        float amount = ReadFloat("Enter deposit amount: ");
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    //Account Creators
    private static void CreateCheckingAccount() {
        BankAccount data = AskBasicData();
        float overdraft = ReadFloat("Overdraft limit: ");

        BankAccount acc = new CheckingAccount(data.getAccHolder(), data.getBIC(),
                data.getAccNumber(), data.getBalance(), overdraft);
        accounts.add(acc);
        System.out.println("Checking account created!");
    }

    private static void CreateSavingAccount() {
        BankAccount data = AskBasicData();

        BankAccount acc = new SavingsAccount(data.getAccHolder(), data.getBIC(), data.getAccNumber(), data.getBalance());
        accounts.add(acc);
        System.out.println("Savings account created!");
    }

    private static void CreateCreditAccount() {
        BankAccount data = AskBasicData();
        if (data.getBalance() < 0) {
            BankAccount acc = new SavingsAccount(data.getAccHolder(), data.getBIC(), data.getAccNumber(), data.getBalance());
            accounts.add(acc);
            System.out.println("Credit account created!");
        } else {
            System.out.println("Credit Balance can't be positive!");
        }

    }

    //HELPERS TO REDUCE DUPLICATE CODE:
    private static Boolean CheckIfListEmpty() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Create one first.");
            return true;
        } else {
            return false;
        }
    }

    private static void ChooseCreateAccount() {
        PrintAccountOptions();
        int choice = ReadInt("Choose account type: ");

        switch (choice) {
            case 1 -> CreateCheckingAccount();
            case 2 -> CreateSavingAccount();
            case 3 -> CreateCreditAccount();
            case 0 -> System.out.println("Cancelled account creation.");
            default -> System.out.println("Invalid input.");
        }
    }

    private static BankAccount SelectAccount(String action) {
        if (CheckIfListEmpty()) {
            return null;
        }

        listAccounts();
        int index = ReadInt("Select account number to " + action + ": ") - 1;

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid account selection.");
            return null;
        }

        return accounts.get(index);
    }

    private static BankAccount AskBasicData() {
        String holder = ReadString("Account holder: ");
        String bic = ReadString("BIC: ");
        String number = ReadString("Account number: ");
        float balance = ReadFloat("Initial balance: ");
        return new BankAccount(holder, bic, number, balance);
    }

    //Print all accounts
    private static void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("=== Accounts ===");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println("[" + (i + 1) + "]");
                System.out.println(accounts.get(i));
            }
        }
    }

    // INPUT VALIDATORS:
    private static int ReadInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    private static float ReadFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Float.parseFloat(input.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a decimal number.");
            }
        }
    }

    private static String ReadString(String prompt) {
        System.out.print(prompt);
        return input.next();
    }

    // MENU TEXT PRINTERS:
    public static void PrintMenu() {
        System.out.println("""
                What would you like to do?
                1. - Open new Account
                2. - Deposit Money
                3. - Withdraw Money
                4. - List all Accounts
                5. - Transfer Money
                6. - Delete Account
                0. - Close Program
                """);
    }

    private static void PrintAccountOptions() {
        System.out.println("""
                Which type of account would you like to open?
                1. - Open new Checking Account
                2. - Open new Savings Account
                3. - Open new Credit Account
                0. - Cancel
                """);
    }
}