package main.java.co.edu.eafit.componentes.bankagent.Credit;

import main.java.co.edu.eafit.componentes.bankagent.Account.AccountDao;
import main.java.co.edu.eafit.componentes.bankagent.Account.Account;

import java.sql.SQLException;
import java.util.Objects;

public class CreditManagement {

    public static Account askForCredit(Account account, int amount) throws IllegalArgumentException {
        CreditDao creditDao = new CreditDao();
        Credit credit = new Credit(0, account.getId(), amount, "APPROVED");

        AccountDao accountDao = new AccountDao();

        if (amount <= 0) { throw new IllegalArgumentException(); }

        try {
            account.setDebt(Objects.requireNonNull(account).getDebt() + amount);
            accountDao.update(account);
            creditDao.save(credit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
