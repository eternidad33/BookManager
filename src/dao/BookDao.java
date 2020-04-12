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
    /**
     * 添加图书
     *
     * @param connection 数据库连接
     * @param book       要添加的图书实体
     * @return int
     * @since 2020/4/12
     */
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

    /**
     * 列出图书
     *
     * @param conn 数据库连接
     * @param book 图书实体
     * @return java.sql.ResultSet
     * @since 2020/4/12
     */
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

    /**
     * 删除图书信息
     *
     * @param conn 数据库连接
     * @param id   图书编号
     * @return int
     * @since 2020/4/12
     */
    public static int delete(Connection conn, String id) throws SQLException {
        String sql = "delete from book where id=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, id);
        return psmt.executeUpdate();
    }
}
