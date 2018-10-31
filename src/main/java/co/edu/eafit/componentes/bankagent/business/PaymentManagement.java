package co.edu.eafit.componentes.bankagent.business;

import co.edu.eafit.componentes.bankagent.dto.AccountDao;
import co.edu.eafit.componentes.bankagent.dto.PaymentDao;
import co.edu.eafit.componentes.bankagent.model.Account;
import co.edu.eafit.componentes.bankagent.model.Payment;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class PaymentManagement {

    public static Account payDebt(Account account, int payingAmount) {
        PaymentDao paymentDao = new PaymentDao();
        Payment payment = new Payment(0, account.getId(), payingAmount, Date.valueOf(LocalDate.now()));

        AccountDao accountDao = new AccountDao();

        if (payingAmount <= 0) { throw new IllegalArgumentException(); }

        try {
            account.setDebt(Objects.requireNonNull(account).getDebt() - payingAmount);
            accountDao.update(account);
            paymentDao.save(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
