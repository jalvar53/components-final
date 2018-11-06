package Credit;

import Account.AccountDao;
import Account.Account;

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
