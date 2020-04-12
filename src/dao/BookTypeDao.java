package dao;

import entity.Booktype;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 项目名 BookManager
 * <br>包名 dao
 * <br>创建时间 2020/4/10 18:54
 * <br>描述 图书类型Dao
 *
 * @author Vigilr
 */
public class BookTypeDao {
    /**
     * 添加图书类别
     *
     * @return int
     * @since 2020/4/12
     */
    public static int add(Connection connection, Booktype booktype) throws SQLException {
        String sql = "insert into table_booktype values(null,?,?)";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, booktype.getBooktypename());
        psmt.setString(2, booktype.getBooktypedesc());
        return psmt.executeUpdate();
    }

    /**
     * 查询图书类别集合
     *
     * @param connection 数据库连接
     * @param booktype   图书类别实体
     * @return java.sql.ResultSet
     */
    public static ResultSet list(Connection connection, Booktype booktype) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from table_booktype");
        if (!StringUtil.isEmpty(booktype.getBooktypename())) {
            sb.append(" and bookTypeName like '%" + booktype.getBooktypename() + "%'");
        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return psmt.executeQuery();
    }

    /**
     * 删除图书类别
     *
     * @param connection 数据库连接
     * @param id         图书类别Id
     * @return int
     */
    public static int delete(Connection connection, String id) throws SQLException {
        String sql = "delete from table_booktype where id=?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, id);
        return psmt.executeUpdate();
    }

    /**
     * 修改图书类别
     *
     * @param connection 数据库连接
     * @param booktype   图书类别实体
     * @return int
     */
    public static int update(Connection connection, Booktype booktype) throws SQLException {
        String sql = "update table_booktype set bookTypeName=? ,bookTypeDesc=? where id=?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, booktype.getBooktypename());
        psmt.setString(2, booktype.getBooktypedesc());
        psmt.setInt(3, booktype.getId());
        return psmt.executeUpdate();
    }

    /**
     * 判断指定类别下是否有图书
     *
     * @param conn 数据库连接
     * @param id   图书类别编号
     * @return boolean
     * @since 2020/4/12
     */
    public static boolean existBook(Connection conn, String id) throws SQLException {
        String sql = "select * from book where bookTypeid=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, id);
        ResultSet rs = psmt.executeQuery();
        return rs.next();
    }
}
