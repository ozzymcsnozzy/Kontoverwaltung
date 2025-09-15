import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int menu = -1;
        do {
            System.out.println("""
                    What would you like to do?
                    1. - Open new Account
                    2. - Deposit Money
                    3. - Withdraw Money
                    4. - See Account information
                    5. - Delete Account
                    0. - Close Program
                    """);
            try {String userInput = input.next();
                menu = Integer.parseInt(userInput);
                if (menu > -1 && menu <= 5) {
                    switch (menu) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                } else {
                    System.out.println("Invalid input, try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
        } while (menu != 0);
        System.out.println("Exiting Program....");
    }
}