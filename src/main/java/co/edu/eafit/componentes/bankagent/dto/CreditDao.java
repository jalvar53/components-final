package co.edu.eafit.componentes.bankagent.dto;

import co.edu.eafit.componentes.bankagent.connection.BankDatabase;
import co.edu.eafit.componentes.bankagent.model.Credit;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
public class CreditDao implements Dao<Credit> {

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public CreditDao() {
        try {
            this.connection = BankDatabase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Credit> get(int id) throws SQLException {
        Credit credit = null;
        String query = "SELECT id, amount, state, userId FROM credit WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            credit = new Credit(
                    resultSet.getInt("id"),
                    resultSet.getInt("userId"),
                    resultSet.getLong("amount"),
                    resultSet.getString("state")
            );
        }
        return Optional.ofNullable(credit);
    }

    @Override
    public Collection<Credit> getAll() throws SQLException {
        List<Credit> credits = new ArrayList<>();
        String query = "SELECT * FROM credit";
        statement = this.connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            credits.add(new Credit(
                    resultSet.getInt("id"),
                    resultSet.getInt("userId"),
                    resultSet.getLong("amount"),
                    resultSet.getString("state")
            ));
        }
        return credits;
    }

    @Override
    public void save(Credit credit) throws SQLException {
        String query = "INSERT INTO credit (id, amount, state, userId) values(?, ?, ?, ?)";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, credit.getId());
        statement.setLong(2, credit.getAmount());
        statement.setString(3, credit.getState());
        statement.setInt(4, credit.getUserId());
        statement.executeUpdate();
    }

    @Override
    public void update(Credit credit) throws SQLException {
        String query = "UPDATE credit SET amount = ?, state = ?, userId = ? WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, credit.getId());
        statement.setLong(2, credit.getAmount());
        statement.setString(3, credit.getState());
        statement.setInt(4, credit.getUserId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Credit credit) throws SQLException {
        String query = "DELETE FROM credit WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, credit.getId());
        statement.executeUpdate();
    }

    @PostConstruct
    public void initializeDatabase() {
        try {
            this.connection = BankDatabase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeDatabase() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

