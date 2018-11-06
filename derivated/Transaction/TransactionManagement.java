package main.java.co.edu.eafit.componentes.bankagent.Transaction;

import main.java.co.edu.eafit.componentes.bankagent.Account.AccountDao;
import main.java.co.edu.eafit.componentes.bankagent.Account.Account;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class TransactionManagement {

    public static void doTransaction(Account sender, int receiverId, long amount) throws IllegalArgumentException {
        TransactionDao dao = new TransactionDao();
        Transaction transaction = new Transaction(0, sender.getId(), receiverId, amount, Date.valueOf(LocalDate.now()));

        AccountDao accountDao = new AccountDao();

        if (amount <= 0)  {
            System.out.println("Amount cannot be 0 or negative.");
            throw new IllegalArgumentException();
        } else if (sender.getId() == receiverId) {
            System.out.println("You cannot give money to yourself.");
            throw new IllegalArgumentException();
        }

        try {
            Account senderAccount = accountDao.get(sender.getId()).isPresent() ? accountDao.get(sender.getId()).get() : null;
            senderAccount.setBalance(Objects.requireNonNull(senderAccount).getBalance() - amount);
            Account receiverAccount = accountDao.get(receiverId).isPresent() ? accountDao.get(receiverId).get() : null;
            receiverAccount.setBalance(Objects.requireNonNull(receiverAccount).getBalance() + amount);
            accountDao.update(senderAccount);
            accountDao.update(receiverAccount);
            dao.save(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Collection<Transaction> getAllTransactions() {
        Collection<Transaction> transactions = new ArrayList();
        TransactionDao dao = new TransactionDao();
        try {
            transactions = dao.getAll();
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
