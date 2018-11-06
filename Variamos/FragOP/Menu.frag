Fragment BankAgents-Transaction-Menu {
   Action: add
   Priority: high
   FragmentationPoints: creditMenu
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
    private static void showTransactionMenu() {
        Utils.clearScreen();
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Utils.waitInput();
                showMenu();
                break;
            case 2:
                System.out.println("Your last transactions: ");
                try {
                    List<Transaction> transactionList = (ArrayList<Transaction>) TransactionManagement.getAllTransactions();
                    transactionList.forEach((transaction) -> System.out.println(transaction.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Utils.waitInput();
                showMenu();
                break;
            default:
                showPaymentsMenu();
        }

    }
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Credit-Menu {
   Action: add
   Priority: high
   FragmentationPoints: creditMenu
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
    private static void showCreditsMenu() {
        Utils.clearScreen();
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
                    CreditManagement.askForCredit(account, creditAmount);
                    System.out.println("Credit approved");
                } catch (Exception e) {
                    System.out.println("Amount cannot be 0 or negative");
                }
                Utils.waitInput();
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

Fragment BankAgents-Payment-Menu {
   Action: add
   Priority: high
   FragmentationPoints: paymentMenu
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
        Utils.clearScreen();
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
                    PaymentManagement.payDebt(account, payingAmount);
                    System.out.println("Payment done");
                } catch (Exception e) {
                    System.out.println("Amount cannot be 0 or negative");
                }
                Utils.waitInput();
                showPaymentsMenu();
                break;
            case 2:
                showMenu();
                break;
            default:
                showPaymentsMenu();
        }
   [/ALTERCODE-FRAG]
}