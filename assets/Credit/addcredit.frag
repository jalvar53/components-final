Fragment Create-Credit-Table {
   Action: add
   Priority: high
   FragmentationPoints: createCreditTable
   PointBracketsLan: sql
   Destinations: Database-Creation
   SourceCode:
   [ALTERCODE-FRAG]
CREATE TABLE IF NOT EXISTS credit (
	id int(16) NOT NULL AUTO_INCREMENT,
	amount long NOT NULL,
	state varchar(128) NOT NULL,
	userId int(16) NOT NULL,
	PRIMARY KEY (id)
);
   [/ALTERCODE-FRAG]
}

Fragment Credit-Management-Menu-Import {
   Action: add
   Priority: high
   FragmentationPoints: creditManagementImport
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
import Credit.CreditManagement;
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

Fragment BankAgents-Credit-Option {
   Action: add
   Priority: high
   FragmentationPoints: creditOption
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("C) Credits");
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Credit-Option-Case {
   Action: add
   Priority: high
   FragmentationPoints: creditOptionCase
   PointBracketsLan: java
   Destinations: Menus
   SourceCode:
   [ALTERCODE-FRAG]
            case "c":
            case "credit":
                showCreditsMenu();
                break;
   [/ALTERCODE-FRAG]
}