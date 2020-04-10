package dao;

import entity.TableBooktype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 项目名 BookManager
 * <br>包名 dao
 * <br>创建时间 2020/4/10 18:54
 * <br>描述
 *
 * @author Vigilr
 */
public class BookTypeDao {
    public static int add(Connection connection, TableBooktype tableBooktype) throws SQLException {
        String sql = "insert into table_booktype values(null,?,?)";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, tableBooktype.getBooktypename());
        psmt.setString(2, tableBooktype.getBooktypedesc());
        return psmt.executeUpdate();
    }
}
