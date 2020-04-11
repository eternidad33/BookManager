package dao;

import entity.Book;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql = "insert into book values(null,?,?,?,?,?,?)";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, book.getBookname());
        psmt.setString(2, book.getAuthor());
        psmt.setString(3, book.getSex());
        psmt.setInt(4, book.getBooktypeid());
        psmt.setString(5, book.getBookdesc());
        psmt.setFloat(6, book.getPrice());
        return psmt.executeUpdate();
    }

    public static ResultSet list(Connection conn, Book book) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from book b,table_booktype bt where b.bookTypeId=bt.id");
        if (!StringUtil.isEmpty(book.getBookname())) {
            sb.append(" and b.bookName like '%" + book.getBookname() + "%'");
        }
        if (!StringUtil.isEmpty(book.getAuthor())) {
            sb.append(" and b.author like '%" + book.getAuthor() + "%'");
        }
        if (book.getBooktypeid() != null && book.getBooktypeid() != -1) {
            sb.append(" and b.bookTypeId=" + book.getBooktypeid());
        }
        PreparedStatement psmt = conn.prepareStatement(sb.toString());
        return psmt.executeQuery();
    }
}
