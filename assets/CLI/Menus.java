package CLI;

/*B-creditManagementImport*/
/*B-transactionManagementImport*/
/*B-paymentManagementImport*/
import Account.AccountManagement;
import Account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menus {

    private static Scanner reader = new Scanner(System.in);  // Reading from System.in
    private static Account account;

    public static void showWelcomeMenu() {
        Utils.clearScreen();
        Utils.insertLogo();
        System.out.println("Welcome");
        System.out.println("1) Login Existing User");
        System.out.println("2) Register New User");
        System.out.println("3) Exit Program");
        System.out.println();
        System.out.print("Enter an option: ");

        int option = Integer.parseInt(reader.nextLine());
        switch (option) {
            case 1:
                showLogin();
                break;
            case 2:
                showRegistrationMenu();
                break;
            case 3:
                break;
            default:
                showWelcomeMenu();
        }
    }

    private static void showLogin() {
        Utils.clearScreen();
        Utils.insertLogo();
        System.out.println("Login");
        System.out.print("Enter your username: ");
        String username = reader.nextLine();
        System.out.print("Enter your password: ");
        String password = reader.nextLine();
        account = AccountManagement.authenticateUser(username, password);
        while (account == null) {
            System.out.println("Failed login, please try again");
            System.out.print("Enter your username: ");
            username = reader.nextLine();
            System.out.print("Enter your password: ");
            password = reader.nextLine();
            account = AccountManagement.authenticateUser(username, password);
        }
        showMenu();
    }

    private static void showRegistrationMenu() {
        Utils.clearScreen();
        System.out.println("Registration Form");
        System.out.print("Enter your username: ");
        String username = reader.nextLine();
        System.out.print("Enter your password: ");
        String password = reader.nextLine();
        System.out.print("Enter your first name: ");
        String firstName = reader.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = reader.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = reader.nextLine();
        account = AccountManagement.createUser(username, password, firstName, lastName, phoneNumber);
        showMenu();
    }

    private static void showMenu() {
        Utils.clearScreen();
        Utils.insertLogo();
        System.out.println("Welcome, " + account.getFirstName() + " " + account.getLastName());
        System.out.println("Select option:");
        System.out.println("A) Account");
        /*B-transactionOption*/
        /*B-paymentOption*/
        /*B-creditOption*/
        System.out.println("L) Logout");
        System.out.println("E) Exit");
        System.out.println();
        System.out.print("Enter an option: ");
        String option = reader.nextLine();
        switch (option.toLowerCase()) {
            case "a":
            case "account":
                showAccountMenu();
                break;
            /*B-transactionOptionCase*/
            /*B-paymentOptionCase*/
            /*B-creditOptionCase*/
            case "l":
            case "logout":
                showWelcomeMenu();
                break;
            case "e":
            case "exit":
                break;
            default:
                showMenu();
        }
    }

    private static void showAccountMenu() {
        Utils.clearScreen();
        System.out.println("Account");
        System.out.println("Select option:");
        System.out.println("1) View Balance");
        System.out.println("2) View Information");
        System.out.println("3) Update Information");
        System.out.println("4) Delete Account");
        System.out.println("5) Back To Menu");
        System.out.println();
        System.out.print("Enter an option: ");

        int option = Integer.parseInt(reader.nextLine());

        switch (option) {
            case 1:
                long balance = account.getBalance();
                long debt = account.getDebt();
                Utils.clearScreen();
                System.out.println("Account report");
                System.out.println("Your current balance is: " + balance);
                System.out.println("Your debt is: " + debt);
                Utils.waitInput();
                showAccountMenu();
                break;
            case 2:
                String userName = account.getUsername();
                String fullName = String.format("%s %s",
                        account.getFirstName(),
                        account.getLastName());
                String phoneNumber = account.getPhoneNumber();
                Utils.clearScreen();
                System.out.println("Account information");
                System.out.println("Your name is: " + fullName);
                System.out.println("Your bank username is: " + userName);
                System.out.println("Your registered phone number is: " + phoneNumber);
                Utils.waitInput();
                showAccountMenu();
                break;
            case 3:
                Utils.clearScreen();
                System.out.println("You can only change your name or your phone number");
                System.out.println("Enter your new first name:");
                String firstName = reader.nextLine();
                System.out.println("Enter your new last name:");
                String lastName = reader.nextLine();
                System.out.println("Enter your new phone number:");
                String editedPhoneNumber = reader.nextLine();
                AccountManagement.updateUserAccount(account, firstName, lastName, editedPhoneNumber);
                showAccountMenu();
                break;
            case 4:
                AccountManagement.deleteUserAccount(account.getId());
                System.out.println("Account successfully deleted");
                Utils.waitInput();
                showLogin();
                break;
            case 5:
                showMenu();
                break;
            default:
                showAccountMenu();
        }
    }

    /*B-transactionMenu*/

    /*B-paymentMenu*/

    /*B-creditMenu*/
}
