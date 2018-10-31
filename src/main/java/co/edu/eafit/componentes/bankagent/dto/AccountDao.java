package co.edu.eafit.componentes.bankagent.dto;

import co.edu.eafit.componentes.bankagent.connection.BankDatabase;
import co.edu.eafit.componentes.bankagent.model.Account;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountDao implements Dao<Account> {
    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public AccountDao() {
        try {
            this.connection = BankDatabase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Account> getWithLogin(String username, String password) throws SQLException {
        Account account = null;
        String query = "SELECT id, username, password, firstName, lastName, phoneNumber, balance, debt FROM account WHERE username = ? AND password = ?";
        statement = this.connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            account = new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getLong("balance"),
                    resultSet.getLong("debt")
            );
        }
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> get(int id) throws SQLException {
        Account account = null;
        String query = "SELECT id, username, password, firstName, lastName, phoneNumber, balance, debt FROM account WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            account = new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getLong("balance"),
                    resultSet.getLong("debt")
            );
        }
        return Optional.ofNullable(account);
    }

    @Override
    public Collection<Account> getAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";
        statement = this.connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            accounts.add(new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getLong("balance"),
                    resultSet.getLong("debt")
            ));
        }
        return accounts;
    }

    @Override
    public void save(Account account) throws SQLException {
        String query = "INSERT INTO account (username, password, firstName, lastName, phoneNumber, balance, debt) values(?, ?, ?, ?, ?, ?, ?)";
        statement = this.connection.prepareStatement(query);
        statement.setString(1, account.getUsername());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getFirstName());
        statement.setString(4, account.getLastName());
        statement.setString(5, account.getPhoneNumber());
        statement.setLong(6, account.getBalance());
        statement.setLong(7, account.getDebt());
        statement.executeUpdate();
    }

    @Override
    public void update(Account account) throws SQLException {
        String query = "UPDATE account SET username = ?, password = ?, firstName = ?, lastName = ?, phoneNumber = ?, balance = ?, debt = ? WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setString(1, account.getUsername());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getFirstName());
        statement.setString(4, account.getLastName());
        statement.setString(5, account.getPhoneNumber());
        statement.setLong(6, account.getBalance());
        statement.setLong(7, account.getDebt());
        statement.setLong(8, account.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Account account) throws SQLException {
        String query = "DELETE FROM account WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, account.getId());
        statement.executeUpdate();
    }
}