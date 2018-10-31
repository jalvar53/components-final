Fragment BankAgents-Account-Option {
   Action: add
   Priority: high
   FragmentationPoints: accountOption
   PointBracketsLan: java
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("A) Account");
   [/ALTERCODE-FRAG]
}

Fragment BankAgents-Transaction-Option {
   Action: add
   Priority: high
   FragmentationPoints: transactionOption
   PointBracketsLan: java
   Destinations: Bank-modules
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
   Destinations: Bank-modules
   SourceCode:
   [ALTERCODE-FRAG]
        System.out.println("P) Payment");
   [/ALTERCODE-FRAG]
}