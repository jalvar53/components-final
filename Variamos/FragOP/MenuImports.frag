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