package dao;

import entity.TableBooktype;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /**
     * 查询图书类别集合
     *
     * @param connection, tableBooktype
     * @return java.sql.ResultSet
     */
    public static ResultSet list(Connection connection, TableBooktype tableBooktype) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from table_booktype");
        if (!StringUtil.isEmpty(tableBooktype.getBooktypename())) {
            sb.append(" and bookTypeName like '%" + tableBooktype.getBooktypename() + "%'");
        }
        PreparedStatement psmt = connection.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return psmt.executeQuery();
    }

    /**
     * 删除图书类别
     *
     * @param connection, id
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
     * @param connection, booktype
     * @return int
     */
    public static int update(Connection connection, TableBooktype booktype) throws SQLException {
        String sql = "update table_booktype set bookTypeName=? ,bookTypeDesc=? where id=?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, booktype.getBooktypename());
        psmt.setString(2, booktype.getBooktypedesc());
        psmt.setInt(3, booktype.getId());
        return psmt.executeUpdate();
    }
}
