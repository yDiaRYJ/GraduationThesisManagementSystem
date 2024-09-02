package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQueryTest {
    private static final String JDBC_URL = "jdbc:mysql://122.51.8.3:3306/db_graduationthesis?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "user2";
    private static final String PASSWORD = "user2";

    public static void main(String[] args) {
        // 连接数据库并查询
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            // SQL 查询
            String sql = "SELECT * FROM t_user";
            ResultSet resultSet = statement.executeQuery(sql);

            // 处理结果集
            while (resultSet.next()) {
                // 假设表中有两个列: id 和 name
                String id = resultSet.getString("user_id");
                String name = resultSet.getString("user_name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
