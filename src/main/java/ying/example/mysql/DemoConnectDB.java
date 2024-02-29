package ying.example.mysql;

import java.sql.*;

public class DemoConnectDB {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school","root","123456");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
    }
}
