package  main.java.co.edu.eafit.componentes.bankagent.Account;

import java.sql.SQLException;
import java.util.Optional;

public class AccountManagement {

    public static Account authenticateUser(String username, String password) {
        AccountDao dao = new AccountDao();
        try {
            Optional<Account> account = dao.getWithLogin(username, password);
            if(account.isPresent()){
                return account.get();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Account createUser(String username, String password, String firstName, String lastName, String phoneNumber) {
        AccountDao dao = new AccountDao();
        Account account = new Account(0, username, password, firstName, lastName, phoneNumber, 0, 0);
        try {
            dao.save(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static void deleteUserAccount(int accountId){
        AccountDao dao = new AccountDao();
        try {
            Optional<Account> accountToDelete = dao.get(accountId);
            if (accountToDelete.isPresent()) {
                dao.delete(accountToDelete.get());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Account updateUserAccount(Account account, String firstName, String lastName, String editedPhoneNumber) {
        AccountDao dao = new AccountDao();

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(editedPhoneNumber);

        try {
            dao.update(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
