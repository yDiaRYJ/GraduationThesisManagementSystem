package database;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author 李
 * @version 1.0
 * 演示如何在SpringBoot中开发测试类
 */
@SpringBootTest
public class ApplicationTests {
    //自动装配数据源，装配后才能使用数据源获取数据库连接，操作数据库等工作
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        //查看默认数据源，现在springboot默认使用com.zaxxer.hikari.HikariDataSource数据源
        System.out.println(dataSource.getClass());

        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


        //最后用完记得关闭
        connection.close();
    }

}
