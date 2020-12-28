package h8.chikey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
        String BD_DRIVER = "com.mysql.jdbc.Driver";
        String BD_USERNAME = "root";
        String BD_PASSWORD = "12345";
        String BD_CONNECTION = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC";
        private Connection connection;

        public ConnectionUtil(){
            try {
                connection = DriverManager.getConnection(BD_CONNECTION, BD_USERNAME, BD_PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
