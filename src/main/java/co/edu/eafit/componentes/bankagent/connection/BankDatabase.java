package co.edu.eafit.componentes.bankagent.connection;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class BankDatabase {
    private static final String server = "localhost";
    private static final int port = 3306;
    private static final String dbName = "bankagents";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(server);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        ds.setUser(username);
        ds.setPassword(password);

        return ds.getConnection();
    }
}
