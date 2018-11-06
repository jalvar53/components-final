Fragment BankAgents-Account {
   Action: add
   Priority: high
   FragmentationPoints: account
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
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
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Transaction {
   Action: add
   Priority: high
   FragmentationPoints: transaction
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
    private static void showTransactionMenu() {
            clearScreen();
            System.out.println("Transaction");
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
    }
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Credit {
   Action: add
   Priority: high
   FragmentationPoints: credit
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
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
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Payment {
   Action: add
   Priority: high
   FragmentationPoints: payment
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
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
                   showCreditsMenu();
                   break;
               case 2:
                   showMenu();
                   break;
               default:
                   showPaymentsMenu();
           }
       }
   [/ALTERCODE-FRAG]
}