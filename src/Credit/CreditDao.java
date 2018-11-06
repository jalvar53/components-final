package Credit;

import Database.BankDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class CreditDao{

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

    public void save(Credit credit) throws SQLException {
        String query = "INSERT INTO credit (amount, state, userId) values(?, ?, ?)";
        statement = this.connection.prepareStatement(query);
        statement.setLong(1, credit.getAmount());
        statement.setString(2, credit.getState());
        statement.setInt(3, credit.getUserId());
        statement.executeUpdate();
    }

    public void update(Credit credit) throws SQLException {
        String query = "UPDATE credit SET amount = ?, state = ?, userId = ? WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, credit.getId());
        statement.setLong(2, credit.getAmount());
        statement.setString(3, credit.getState());
        statement.setInt(4, credit.getUserId());
        statement.executeUpdate();
    }

    public void delete(Credit credit) throws SQLException {
        String query = "DELETE FROM credit WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, credit.getId());
        statement.executeUpdate();
    }

}

