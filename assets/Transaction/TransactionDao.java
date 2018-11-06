package Transaction;

import Database.BankDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TransactionDao {

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public TransactionDao() {
        try {
            this.connection = BankDatabase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Transaction> get(int id) throws SQLException {
        Transaction transaction = null;
        String query = "SELECT id, receiverId, senderId, amount, transaction_date FROM transaction WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            transaction = new Transaction(
                    resultSet.getInt("id"),
                    resultSet.getInt("receiverId"),
                    resultSet.getInt("senderId"),
                    resultSet.getLong("amount"),
                    resultSet.getDate("transaction_date")
            );
        }
        return Optional.ofNullable(transaction);
    }

    public Collection<Transaction> getAll() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction";
        statement = this.connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            transactions.add(new Transaction(
                    resultSet.getInt("id"),
                    resultSet.getInt("receiverId"),
                    resultSet.getInt("senderId"),
                    resultSet.getLong("amount"),
                    resultSet.getDate("transaction_date")
                )
            );
        }
        return transactions;
    }

    public void save(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transaction (receiverId, senderId, amount, transaction_date) values(?, ?, ?,?)";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, transaction.getReceiverId());
        statement.setInt(2, transaction.getSenderId());
        statement.setLong(3, transaction.getAmount());
        statement.setDate(4, transaction.getTimestamp());
        statement.executeUpdate();
    }

    public void update(Transaction transaction) throws SQLException {
        String query = "UPDATE transaction SET receiverId = ?, senderId = ?, amount = ?, transaction_date = ? WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, transaction.getReceiverId());
        statement.setInt(2, transaction.getSenderId());
        statement.setLong(3, transaction.getAmount());
        statement.setDate(4, transaction.getTimestamp());
        statement.setInt(5, transaction.getId());
        statement.executeUpdate();
    }

    public void delete(Transaction transaction) throws SQLException {
        String query = "DELETE FROM transaction WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, transaction.getId());
        statement.executeUpdate();
    }
}