package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 项目名 BookManager
 * <br>包名 dao
 * <br>创建时间 2020/4/10 11:27
 * <br>描述 登录验证
 *
 * @author Vigilr
 */
public class UserDao {
    /**
     * 用户登录
     *
     * @param connection 数据库连接
     * @param user       用户实体
     * @return entity.User
     * @since 2020/4/12
     */
    public User login(Connection connection, User user) throws SQLException {
        User resultUser = null;
        String sql = "select * from table_user where userName=? and password=?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, user.getUserName());
        psmt.setString(2, user.getPassword());
        ResultSet resultSet = psmt.executeQuery();
        if (resultSet.next()) {
            resultUser = new User();
            resultUser.setId(resultSet.getInt("id"));
            resultUser.setUserName(resultSet.getString("userName"));
            resultUser.setPassword(resultSet.getString("password"));
        }
        return resultUser;
    }
}
