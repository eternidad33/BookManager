package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 项目名 BookManager
 * <br>包名 util
 * <br>创建时间 2020/4/10 10:34
 * <br>描述
 *
 * @author Vigilr
 */
public class DbUtil {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/db_book";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

    /**
     * 获取数据库连接
     *
     * @param
     * @return java.sql.Connection
     */
    public Connection getConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     * @return void
     */
    public void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getConnection();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

}
