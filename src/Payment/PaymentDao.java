package Payment;

import Database.BankDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PaymentDao{

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public PaymentDao() {
        try {
            this.connection = BankDatabase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Payment> get(int id) throws SQLException {
        Payment Payment = null;
        String query = "SELECT id, payerId, amount, payment_date FROM Payment WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Payment = new Payment(
                    resultSet.getInt("id"),
                    resultSet.getInt("payerId"),
                    resultSet.getLong("amount"),
                    resultSet.getDate("payment_date")
            );
        }
        return Optional.ofNullable(Payment);
    }

    public Collection<Payment> getAll() throws SQLException {
        List<Payment> Payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";
        statement = this.connection.prepareStatement(query);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Payments.add(new Payment(
                    resultSet.getInt("id"),
                    resultSet.getInt("payerId"),
                    resultSet.getLong("amount"),
                    resultSet.getDate("payment_date")
            ));
        }
        return Payments;
    }

    public void save(Payment Payment) throws SQLException {
        String query = "INSERT INTO Payment (payerId, amount, payment_date) values(?, ?, ?)";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, Payment.getPayerId());
        statement.setLong(2, Payment.getAmount());
        statement.setDate(3, Payment.getTimestamp());
        statement.executeUpdate();
    }

    public void update(Payment Payment) throws SQLException {
        String query = "UPDATE Payment SET payerId = ?, amount = ?, payment_date = ? WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, Payment.getPayerId());
        statement.setLong(2, Payment.getAmount());
        statement.setDate(3, Payment.getTimestamp());
        statement.setInt(4, Payment.getId());
        statement.executeUpdate();
    }

    public void delete(Payment Payment) throws SQLException {
        String query = "DELETE FROM Payment WHERE id = ?";
        statement = this.connection.prepareStatement(query);
        statement.setInt(1, Payment.getId());
        statement.executeUpdate();
    }
}
