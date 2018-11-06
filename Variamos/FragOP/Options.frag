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

Fragment BankAgents-Credit-Option {
   Action: add
   Priority: high
   FragmentationPoints: creditOption
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("C) Credits");
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