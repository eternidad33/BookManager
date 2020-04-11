package dao;

import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 项目名 BookManager
 * <br>包名 dao
 * <br>创建时间 2020/4/11 10:34
 * <br>描述 图书 Dao类
 *
 * @author Vigilr
 */
public class BookDao {
    public static int add(Connection connection, Book book) throws SQLException {
        String sql="insert into book values(null,?,?,?,?,?,?)";
        PreparedStatement psmt=connection.prepareStatement(sql);
        psmt.setString(1,book.getBookname());
        psmt.setString(2,book.getAuthor());
        psmt.setString(3,book.getSex());
        psmt.setInt(4,book.getBooktypeid());
        psmt.setString(5,book.getBookdesc());
        psmt.setFloat(6,book.getPrice());
        return psmt.executeUpdate();
    }
}
