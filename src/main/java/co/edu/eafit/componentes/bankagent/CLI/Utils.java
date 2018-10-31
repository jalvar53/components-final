package co.edu.eafit.componentes.bankagent.CLI;

import co.edu.eafit.componentes.bankagent.business.AccountManagement;
import co.edu.eafit.componentes.bankagent.business.CreditManagement;
import co.edu.eafit.componentes.bankagent.business.PaymentManagement;
import co.edu.eafit.componentes.bankagent.business.TransactionManagement;
import co.edu.eafit.componentes.bankagent.model.Account;
import co.edu.eafit.componentes.bankagent.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    private static Scanner reader = new Scanner(System.in);  // Reading from System.in
    private static Account account;

    public static void showLogin() {
        clearScreen();
        insertLogo();
        System.out.println();
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

    private static void showMenu() {
        clearScreen();
        insertLogo();
        System.out.println("Welcome, " + account.getFirstName());
        System.out.println("Select option:");
        System.out.println("A) Account");
        System.out.println("T) Transactions");
        System.out.println("P) Payment");
        System.out.println("C) Credits");
        System.out.println();
        System.out.print("Enter an option: ");
        String option = reader.nextLine();
        switch (option.toLowerCase()) {
            case "a":
            case "account":
                showAccountMenu();
                break;
            case "t":
            case "transaction":
                showTransactionMenu();
                break;
            case "p":
            case "payment":
                showPaymentsMenu();
                break;
            case "c":
            case "credit":
                showCreditsMenu();
                break;
            default:
                showMenu();
        }
    }

    private static void showAccountMenu() {
        clearScreen();
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
                System.out.println();
                System.out.println("Your current balance is: " + balance);
                System.out.println("Your debt is: " + debt);
                waitInput();
                showAccountMenu();
                break;
            case 2:
                String userName = account.getUsername();
                String fullName = String.format("%s %s",
                        account.getFirstName(),
                        account.getLastName());
                String phoneNumber = account.getPhoneNumber();
                System.out.println();
                System.out.println("Your name is: " + fullName);
                System.out.println("Your bank username is: " + userName);
                System.out.println("Your registered phone number is: " + phoneNumber);
                waitInput();
                showAccountMenu();
                break;
            case 3:
                System.out.println("You can only change your name or your phone number");
                System.out.println("Enter your new first name:");
                String firstName = reader.nextLine();
                System.out.println("Enter your new last name:");
                String lastName = reader.nextLine();
                System.out.println("Enter your new phone number:");
                String editedPhoneNumber = reader.nextLine();
                account = AccountManagement.updateUserAccount(account, firstName, lastName, editedPhoneNumber);
                showAccountMenu();
                break;
            case 4:
                AccountManagement.deleteUserAccount(account.getId());
                System.out.println("Account successfully deleted");
                waitInput();
                showLogin();
                break;
            case 5:
                showMenu();
                break;
            default:
                showAccountMenu();
        }
    }

    private static void showTransactionMenu() {
        clearScreen();
        System.out.println("Transaction");
        System.out.println("Select option:");
        System.out.println("1) Make transaction");
        System.out.println("2) List my transactions");
        System.out.println("3) Back To Menu");
        System.out.println();
        System.out.print("Enter an option: ");

        int option = Integer.parseInt(reader.nextLine());
        switch (option) {
            case 1:
                System.out.println("Bank account of the transaction receiver:");
                int receiver = Integer.parseInt(reader.nextLine());
                System.out.println("Amount to transfer:");
                long amount = Long.parseLong(reader.nextLine());
                try {
                    TransactionManagement.doTransaction(account, receiver, amount);
                    System.out.println("Transfer done successfully.");
                } catch (Exception e) { }
                waitInput();
                showMenu();
                break;
            case 2:
                System.out.println("Your last transactions: ");
                try {
                    List<Transaction> transactionList = (ArrayList<Transaction>) TransactionManagement.getAllTransactions();
                    transactionList.forEach((transaction) -> System.out.println(transaction.toString()));
                } catch (Exception e) { }
                waitInput();
                showMenu();
                break;
            default:
                showPaymentsMenu();
        }

    }

    private static void showPaymentsMenu() {
        clearScreen();
        System.out.println("Payments");
        System.out.println("Select option:");
        System.out.println("1) Make a payment");
        System.out.println("2) Back To Menu");
        System.out.println();
        System.out.print("Enter an option: ");

        int option = Integer.parseInt(reader.nextLine());
        switch (option) {
            case 1:
                System.out.println("Amount to pay:");
                int payingAmount = Integer.parseInt(reader.nextLine());
                try {
                    account = PaymentManagement.payDebt(account, payingAmount);
                    System.out.println("Payment done");
                } catch (Exception e) {
                    System.out.println("Amount cannot be 0 or negative");
                }
                waitInput();
                showPaymentsMenu();
                break;
            case 2:
                showMenu();
                break;
            default:
                showPaymentsMenu();
        }
    }

    private static void showCreditsMenu() {
        clearScreen();
        System.out.println("Credits");
        System.out.println("Select option:");
        System.out.println("1) Ask for credit");
        System.out.println("2) Back To Menu");
        System.out.println();
        System.out.print("Enter an option: ");

        int option = Integer.parseInt(reader.nextLine());
        switch (option) {
            case 1:
                System.out.println("Credit amount:");
                int creditAmount = Integer.parseInt(reader.nextLine());
                try {
                    account = CreditManagement.askForCredit(account, creditAmount);
                    System.out.println("Credit approved");
                } catch (Exception e) {
                    System.out.println("Amount cannot be 0 or negative");
                }
                waitInput();
                showCreditsMenu();
                break;
            case 2:
                showMenu();
                break;
            default:
                showCreditsMenu();
        }
    }

    private static void clearScreen() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    private static void insertLogo() {
        System.out.println(" ____              _        _                    _");
        System.out.println("| __ )  __ _ _ __ | | __   / \\   __ _  ___ _ __ | |_ ___");
        System.out.println("|  _ \\ / _` | '_ \\| |/ /  / _ \\ / _` |/ _ \\ '_ \\| __/ __|");
        System.out.println("| |_) | (_| | | | |   <  / ___ \\ (_| |  __/ | | | |_\\__ \\");
        System.out.println("|____/ \\__,_|_| |_|_|\\_\\/_/   \\_\\__, |\\___|_| |_|\\__|___/");
        System.out.println("                                |___/");
    }

    private static void waitInput(){
        System.out.println();
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
