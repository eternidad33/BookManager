/*
 * Created by JFormDesigner on Sat Apr 11 10:51:16 CST 2020
 */

package view;

import dao.BookDao;
import dao.BookTypeDao;
import entity.Book;
import entity.TableBooktype;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vigilr
 */
public class BookAddFrm extends JFrame {
    public BookAddFrm() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        String bookName = this.booknameTXT.getText();
        String author = this.bookAuthorTXT.getText();
        String price = this.bookPriceTXT.getText();
        String bookDesc = this.bookDescTXT.getText();
        if (StringUtil.isEmpty(bookName)) {
            JOptionPane.showMessageDialog(null, "图书名称不能为空");
            return;
        }
        if (StringUtil.isEmpty(author)) {
            JOptionPane.showMessageDialog(null, "图书作者不能为空");
            return;
        }
        if (StringUtil.isEmpty(price)) {
            JOptionPane.showMessageDialog(null, "图书价格不能为空");
            return;
        }
        String sex = "";
        if (this.man.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }
        TableBooktype booktype = (TableBooktype) this.booktypecb.getSelectedItem();
        assert booktype != null;
        int booktypeId = booktype.getId();
        Book book = new Book(bookName, author, sex, Float.parseFloat(price), booktypeId, bookDesc);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            int addNum = BookDao.add(conn, book);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "添加成功");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "添加失败");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 重置表单
     *
     * @param
     * @return void
     */
    private void resetValue() {
        this.booknameTXT.setText("");
        this.bookAuthorTXT.setText("");
        this.bookDescTXT.setText("");
        this.bookPriceTXT.setText("");
        this.man.setSelected(true);
        if (this.booktypecb.getItemCount() > 0) {
            this.booktypecb.setSelectedIndex(0);
        }
    }

    private DbUtil dbUtil = new DbUtil();

    private void fillBookTypeCB() {
        TableBooktype booktype = null;
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            ResultSet rs = BookTypeDao.list(conn, new TableBooktype());
            while (rs.next()) {
                booktype = new TableBooktype();
                booktype.setId(rs.getInt("id"));
                booktype.setBooktypename(rs.getString("bookTypeName"));
                this.booktypecb.addItem(booktype);
            }
        } catch (Exception e) {

        } finally {

        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        this.resetValue();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        booknameTXT = new JTextField();
        label2 = new JLabel();
        bookAuthorTXT = new JTextField();
        label3 = new JLabel();
        man = new JRadioButton();
        woman = new JRadioButton();
        label7 = new JLabel();
        label8 = new JLabel();
        bookPriceTXT = new JTextField();
        scrollPane1 = new JScrollPane();
        bookDescTXT = new JTextArea();
        label9 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        booktypecb = new JComboBox();
        man.setSelected(true);

        //======== this ========
        setTitle("\u56fe\u4e66\u6dfb\u52a0");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 25), label1.getPreferredSize()));
        contentPane.add(booknameTXT);
        booknameTXT.setBounds(90, 20, 85, booknameTXT.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u4f5c\u8005");
        contentPane.add(label2);
        label2.setBounds(245, 25, 70, label2.getPreferredSize().height);
        contentPane.add(bookAuthorTXT);
        bookAuthorTXT.setBounds(310, 20, 130, bookAuthorTXT.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u4f5c\u8005\u6027\u522b");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(25, 75), label3.getPreferredSize()));

        //---- man ----
        man.setText("\u7537");
        contentPane.add(man);
        man.setBounds(new Rectangle(new Point(90, 65), man.getPreferredSize()));

        //---- woman ----
        woman.setText("\u5973");
        contentPane.add(woman);
        woman.setBounds(new Rectangle(new Point(140, 65), woman.getPreferredSize()));

        //---- label7 ----
        label7.setText("\u56fe\u4e66\u7c7b\u522b");
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(25, 125), label7.getPreferredSize()));

        //---- label8 ----
        label8.setText("\u56fe\u4e66\u4ef7\u683c");
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(245, 75), label8.getPreferredSize()));
        contentPane.add(bookPriceTXT);
        bookPriceTXT.setBounds(310, 75, 130, bookPriceTXT.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bookDescTXT);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(90, 175, 355, 145);

        //---- label9 ----
        label9.setText("\u56fe\u4e66\u63cf\u8ff0");
        contentPane.add(label9);
        label9.setBounds(new Rectangle(new Point(25, 175), label9.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.addActionListener(this::button1ActionPerformed);
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(80, 360), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(270, 360, 70, 30);
        contentPane.add(booktypecb);
        booktypecb.setBounds(105, 115, 340, booktypecb.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(490, 460);
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(man);
        buttonGroup1.add(woman);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillBookTypeCB();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField booknameTXT;
    private JLabel label2;
    private JTextField bookAuthorTXT;
    private JLabel label3;
    private JRadioButton man;
    private JRadioButton woman;
    private JLabel label7;
    private JLabel label8;
    private JTextField bookPriceTXT;
    private JScrollPane scrollPane1;
    private JTextArea bookDescTXT;
    private JLabel label9;
    private JButton button1;
    private JButton button2;
    private JComboBox booktypecb;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
