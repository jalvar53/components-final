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
Fragment Create-Payment-Table {
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