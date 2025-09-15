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
                case 5 -> System.out.println("Transfer feature not yet implemented.");
                case 6 -> System.out.println("Delete account feature not yet implemented.");
                case 0 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid input, try again.");
            }
        } while (menu != 0);
        input.close();
    }

    private static void  WithdrawFromAccount(){
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Create one first.");
            return;
        }

        listAccounts();
        int index = (ReadInt("Select account number to withdraw from: ") - 1);

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid account selection.\n");
            return;
        }

        float amount = ReadFloat("Enter withdraw amount: ");
        if (amount <= 0 || amount > accounts.get(index).getBalance()){
            System.out.println("Withdrawal amount must be less than overdraft limit");
            return;
        }

        BankAccount account = accounts.get(index);
        account.withdraw(amount);
        System.out.println("Withdrawal successful.");

    }

    private static void DepositToAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Create one first.");
            return;
        }

        listAccounts();
        int index = (ReadInt("Select account number to deposit into: ") - 1);

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid account selection.\n");
            return;
        }

        float amount = ReadFloat("Enter deposit amount: ");
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }

        BankAccount account = accounts.get(index);
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void CreateCheckingAccount() {
        BankAccount data = AskBasicData();
        float overdraft = ReadFloat("Overdraft limit: ");

        BankAccount acc = new CheckingAccount(data.getAccHolder(), data.getBIC(),
                data.getAccNumber(), data.getBalance(), overdraft);
        accounts.add(acc);
        System.out.println("Checking account created!\n");
    }

    private static void CreateSavingAccount() {
        BankAccount data = AskBasicData();

        BankAccount acc = new SavingsAccount(data.getAccHolder(), data.getBIC(), data.getAccNumber(), data.getBalance());
        accounts.add(acc);
        System.out.println("Savings account created!\n");
    }

    private static void CreateCreditAccount() {
        BankAccount data = AskBasicData();
        if (data.getBalance() > 0) {
            BankAccount acc = new SavingsAccount(data.getAccHolder(), data.getBIC(), data.getAccNumber(), data.getBalance());
            accounts.add(acc);
            System.out.println("Credit account created!\n");
        }
        else {
            System.out.println("Credit Balance can't be positive!");
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
            System.out.println("No accounts found.\n");
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
        return input.nextLine();
    }

    // MENU TEXT PRINTERS:
    public static void PrintMenu() {
        System.out.println("""
                What would you like to do?
                1. - Open new Account
                2. - Deposit Money
                3. - Withdraw Money
                4. - List all Accounts
                5. - Delete Account
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
