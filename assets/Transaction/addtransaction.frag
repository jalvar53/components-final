Fragment Create-Transaction-Table {
   Action: add
   Priority: high
   FragmentationPoints: createTransactionTable
   PointBracketsLan: sql
   Destinations: Database-Creation
   SourceCode:
   [ALTERCODE-FRAG]
CREATE TABLE IF NOT EXISTS transaction (
	id int(16) NOT NULL AUTO_INCREMENT,
	receiverId int(16) NOT NULL,
	senderId int(16) NOT NULL,
	amount long NOT NULL,
    transaction_date date,
	PRIMARY KEY (id)
);
   [/ALTERCODE-FRAG]
}

Fragment Transaction-Management-Menu-Import {
   Action: add
   Priority: high
   FragmentationPoints: transactionManagementImport
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
import Transaction.TransactionManagement;
import Transaction.Transaction;
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Transaction-Menu {
   Action: add
   Priority: high
   FragmentationPoints: transactionMenu
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

Fragment BankAgents-Transaction-Option {
   Action: add
   Priority: high
   FragmentationPoints: transactionOption
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("T) Transactions");
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Transaction-Option-Case {
   Action: add
   Priority: high
   FragmentationPoints: transactionOptionCase
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
                case "t":
                case "transaction":
                    showTransactionMenu();
                    break;
   [/ALTERCODE-FRAG]
}