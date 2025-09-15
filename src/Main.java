public class Main {
    public static void main(String[] args) {

        System.out.println("C H E C K I N G S");
        System.out.println();

        CheckingAccount checkTest = new CheckingAccount("CheckTEST", "AT12345", "AT 32 234 369420", 250.45F, 500.00F);

        System.out.println(checkTest);
        checkTest.deposit(200F);
        checkTest.withdraw(7000F);
        checkTest.withdraw(50.30F);
        System.out.println(checkTest);

        System.out.println("S A V I N G S");
        System.out.println();

        SavingsAccount saveTest = new SavingsAccount("SaveTEST", "AT12345", "AT 32 234 369420", 2545.45F);

        System.out.println(saveTest);
        saveTest.deposit(200F);
        saveTest.withdraw(2000F);
        saveTest.withdraw(50.30F);
        System.out.println(saveTest);

        System.out.println("C R E D I T");
        System.out.println();

        CreditAccount creditTest = new CreditAccount("CheckTEST", "AT12345", "AT 32 234 369420", -2545.45F);

        System.out.println(creditTest);
        creditTest.deposit(200F);
        creditTest.withdraw(2000F);
        creditTest.withdraw(50.30F);
        creditTest.deposit(5000F);
        System.out.println(creditTest);

    }
}