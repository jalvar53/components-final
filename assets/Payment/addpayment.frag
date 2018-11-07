Fragment Create-Payment-Table {
   Action: add
   Priority: high
   FragmentationPoints: createPaymentTable
   PointBracketsLan: sql
   Destinations: Database-Creation
   SourceCode:
   [ALTERCODE-FRAG]
CREATE TABLE IF NOT EXISTS payment (
  id int(16) NOT NULL AUTO_INCREMENT,
  payerId int(16) NOT NULL,
  amount varchar(128) NOT NULL,
  payment_date timestamp NOT NULL,
  PRIMARY KEY (id)
);
   [/ALTERCODE-FRAG]
}

Fragment Payment-Management-Menu-Import {
   Action: add
   Priority: high
   FragmentationPoints: paymentManagementImport
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
import Payment.PaymentManagement;
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
    private static void showPaymentsMenu() {
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
    }
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Payment-Option {
   Action: add
   Priority: high
   FragmentationPoints: paymentOption
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("P) Payment");
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Payment-Option-Case {
   Action: add
   Priority: high
   FragmentationPoints: paymentOptionCase
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
            case "p":
            case "payment":
                showPaymentsMenu();
                break;
   [/ALTERCODE-FRAG]
}


