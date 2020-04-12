/*
 * Created by JFormDesigner on Fri Apr 10 16:43:38 CST 2020
 */

package view;

import dao.BookDao;
import dao.BookTypeDao;
import entity.Book;
import entity.Booktype;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author vigilr
 */
public class MainFrm extends JFrame {
    public MainFrm() {
        initComponents();
    }

    private void thisWindowClosing(WindowEvent e) {
        System.exit(1);
    }

    /**
     * 关于开发者
     *
     * @return void
     * @since 2020/4/12
     */
    private void menuItem1ActionPerformed(ActionEvent e) {
        About about = new About();
        about.setVisible(true);
    }

    /**
     * 填充下拉框
     *
     * @return void
     * @since 2020/4/12
     */
    private void fillerItem(String type) {
        Connection conn = null;
        Booktype booktype = null;
        try {
            conn = dbUtil.getConnection();
            ResultSet rs = BookTypeDao.list(conn, new Booktype());
            if ("search".equals(type)) {
                booktype = new Booktype();
                booktype.setBooktypename("请选择...");
                booktype.setId(-1);
                this.booktypeCB.addItem(booktype);
            }
            while (rs.next()) {
                booktype = new Booktype();
                booktype.setBooktypename(rs.getString("bookTypeName"));
                booktype.setId(rs.getInt("id"));
                if ("search".equals(type)) {
                    this.booktypeCB.addItem(booktype);
                } else if ("modify".equals(type)) {
                    this.typeCB.addItem(booktype);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 安全退出事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void menuItem2ActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
        System.out.println(result);
        if (result == 0) {
            System.exit(1);
        }
    }


    /**
     * 图书类别管理
     *
     * @return void
     * @since 2020/4/12
     */
    private void menuItem5ActionPerformed(ActionEvent e) {
//        dispose();
        BookTypeManagerFrm bookTypeManagerFrm = new BookTypeManagerFrm();
        bookTypeManagerFrm.setVisible(true);
    }

    /**
     * 图书添加
     *
     * @return void
     * @since 2020/4/12
     */
    private void menuItem3ActionPerformed(ActionEvent e) {
        BookAddFrm bookAddFrm = new BookAddFrm();
        bookAddFrm.setVisible(true);
//        this.table.add(bookAddFrm);
    }

    /**
     * 查询事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void button1ActionPerformed(ActionEvent e) {
        String name = this.bookNameTXT.getText();
        String author = this.authorTXT.getText();
        Booktype booktype = (Booktype) this.booktypeCB.getSelectedItem();
        this.fillTable(new Book(name, author, booktype.getId()));
    }

    /**
     * 表格行点击事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void table1MouseClicked(MouseEvent e) {
        int row = this.table1.getSelectedRow();
        this.idTXT.setText(table1.getValueAt(row, 0).toString());
        this.BookNameTXT.setText(table1.getValueAt(row, 1).toString());
        this.bookauthorTXT.setText(table1.getValueAt(row, 2).toString());
        String sex = table1.getValueAt(row, 3).toString();
        if ("男".equals(sex)) {
            this.man.setSelected(true);
        } else if ("女".equals(sex)) {
            this.woman.setSelected(true);
        }
        this.bookDescTXT.setText(table1.getValueAt(row, 5).toString());
        this.priceTXT.setText(table1.getValueAt(row, 6).toString());
        String bookbype = table1.getValueAt(row, 4).toString();
        int n = this.typeCB.getItemCount();
        for (int i = 0; i < n; i++) {
            Booktype item = (Booktype) this.typeCB.getItemAt(i);
            if (item.getBooktypename().equals(bookbype)) {
                this.typeCB.setSelectedIndex(i);
            }
        }
    }

    /**
     * 图书修改事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void updateButtonActionPerformed(ActionEvent e) {
        String id = idTXT.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        String bookName = this.BookNameTXT.getText();
        String author = this.bookauthorTXT.getText();
        String price = this.priceTXT.getText();
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
        try {
            float pricef = Float.parseFloat(price);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "图书价格应为数字");
            return;
        }
        String sex = "";
        if (this.man.isSelected()) {
            sex = "男";
        } else {
            sex = "女";
        }

        Booktype booktype = (Booktype) this.typeCB.getSelectedItem();
        assert booktype != null;
        int booktypeId = booktype.getId();

        Book book = new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), booktypeId, bookDesc);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            int addNum = BookDao.update(conn, book);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "修改成功");
                resetValue();
                fillTable(new Book());
            } else {
                JOptionPane.showMessageDialog(null, "修改失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 图书删除事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void delButtonActionPerformed(ActionEvent e) {
        String id = idTXT.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除记录吗？");
        if (n == 0) {
            Connection connection = null;
            try {
                connection = dbUtil.getConnection();
                int deleteNum = BookDao.delete(connection, id);
                if (deleteNum == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new Book());
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "删除失败");
            } finally {
                try {
                    dbUtil.closeConnection(connection);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void resetValue() {
        this.BookNameTXT.setText("");
        this.bookauthorTXT.setText("");
        this.bookDescTXT.setText("");
        this.priceTXT.setText("");
        this.idTXT.setText("");
        this.idTXT.setText("");
        this.man.setSelected(true);
        this.typeCB.setSelectedIndex(0);
    }


    DbUtil dbUtil = new DbUtil();

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu5 = new JMenu();
        menuItem3 = new JMenuItem();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        panel1 = new JPanel();
        label1 = new JLabel();
        bookNameTXT = new JTextField();
        label2 = new JLabel();
        authorTXT = new JTextField();
        label3 = new JLabel();
        booktypeCB = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        label4 = new JLabel();
        idTXT = new JTextField();
        label5 = new JLabel();
        BookNameTXT = new JTextField();
        label6 = new JLabel();
        priceTXT = new JTextField();
        label7 = new JLabel();
        bookauthorTXT = new JTextField();
        label8 = new JLabel();
        man = new JRadioButton();
        woman = new JRadioButton();
        label9 = new JLabel();
        typeCB = new JComboBox();
        label10 = new JLabel();
        scrollPane2 = new JScrollPane();
        bookDescTXT = new JTextArea();
        updateButton = new JButton();
        delButton = new JButton();

        //======== this ========
        setTitle("\u4e3b\u754c\u9762");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u56fe\u4e66\u7c7b\u522b\u64cd\u4f5c");

                //---- menuItem5 ----
                menuItem5.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
                menuItem5.addActionListener(e -> menuItem5ActionPerformed(e));
                menu1.add(menuItem5);

                //---- menuItem2 ----
                menuItem2.setText("\u5b89\u5168\u9000\u51fa");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu5 ========
            {
                menu5.setText("\u56fe\u4e66\u7ba1\u7406");

                //---- menuItem3 ----
                menuItem3.setText("\u56fe\u4e66\u6dfb\u52a0");
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu5.add(menuItem3);
            }
            menuBar1.add(menu5);

            //======== menu2 ========
            {
                menu2.setText("\u5173\u4e8e");

                //---- menuItem1 ----
                menuItem1.setText("\u5173\u4e8e\u5f00\u53d1\u8005");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu2.add(menuItem1);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\u79f0");
            panel1.add(label1);
            label1.setBounds(20, 40, 60, label1.getPreferredSize().height);
            panel1.add(bookNameTXT);
            bookNameTXT.setBounds(75, 35, 125, bookNameTXT.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u4f5c\u8005");
            panel1.add(label2);
            label2.setBounds(260, 40, 30, label2.getPreferredSize().height);
            panel1.add(authorTXT);
            authorTXT.setBounds(295, 35, 90, authorTXT.getPreferredSize().height);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b");
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(435, 40), label3.getPreferredSize()));
            panel1.add(booktypeCB);
            booktypeCB.setBounds(490, 35, 131, booktypeCB.getPreferredSize().height);

            //---- button1 ----
            button1.setText("\u67e5\u8be2");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(670, 35, 90, button1.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(15, 10, 790, 90);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u7f16\u53f7", "\u56fe\u4e66\u540d\u79f0", "\u4f5c\u8005", "\u4f5c\u8005\u6027\u522b", "\u56fe\u4e66\u7c7b\u522b", "\u63cf\u8ff0", "\u4ef7\u683c"
                }
            ));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 120, 790, 175);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- label4 ----
            label4.setText("\u7f16\u53f7");
            panel2.add(label4);
            label4.setBounds(new Rectangle(new Point(45, 25), label4.getPreferredSize()));

            //---- idTXT ----
            idTXT.setEditable(false);
            panel2.add(idTXT);
            idTXT.setBounds(80, 20, 100, idTXT.getPreferredSize().height);

            //---- label5 ----
            label5.setText("\u56fe\u4e66\u540d\u79f0");
            panel2.add(label5);
            label5.setBounds(265, 25, 55, label5.getPreferredSize().height);
            panel2.add(BookNameTXT);
            BookNameTXT.setBounds(320, 20, 130, BookNameTXT.getPreferredSize().height);

            //---- label6 ----
            label6.setText("\u4ef7\u683c");
            panel2.add(label6);
            label6.setBounds(530, 25, 35, label6.getPreferredSize().height);
            panel2.add(priceTXT);
            priceTXT.setBounds(565, 20, 85, priceTXT.getPreferredSize().height);

            //---- label7 ----
            label7.setText("\u4f5c\u8005");
            panel2.add(label7);
            label7.setBounds(new Rectangle(new Point(45, 80), label7.getPreferredSize()));
            panel2.add(bookauthorTXT);
            bookauthorTXT.setBounds(80, 75, 95, bookauthorTXT.getPreferredSize().height);

            //---- label8 ----
            label8.setText("\u4f5c\u8005\u6027\u522b");
            panel2.add(label8);
            label8.setBounds(new Rectangle(new Point(265, 80), label8.getPreferredSize()));

            //---- man ----
            man.setText("\u7537");
            panel2.add(man);
            man.setBounds(new Rectangle(new Point(325, 75), man.getPreferredSize()));

            //---- woman ----
            woman.setText("\u5973");
            panel2.add(woman);
            woman.setBounds(new Rectangle(new Point(370, 75), woman.getPreferredSize()));

            //---- label9 ----
            label9.setText("\u56fe\u4e66\u7c7b\u522b");
            panel2.add(label9);
            label9.setBounds(new Rectangle(new Point(530, 80), label9.getPreferredSize()));
            panel2.add(typeCB);
            typeCB.setBounds(595, 75, 115, typeCB.getPreferredSize().height);

            //---- label10 ----
            label10.setText("\u56fe\u4e66\u63cf\u8ff0");
            panel2.add(label10);
            label10.setBounds(new Rectangle(new Point(45, 130), label10.getPreferredSize()));

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(bookDescTXT);
            }
            panel2.add(scrollPane2);
            scrollPane2.setBounds(105, 130, 605, 150);

            //---- updateButton ----
            updateButton.setText("\u4fee\u6539");
            updateButton.addActionListener(e -> updateButtonActionPerformed(e));
            panel2.add(updateButton);
            updateButton.setBounds(165, 300, 135, updateButton.getPreferredSize().height);

            //---- delButton ----
            delButton.setText("\u5220\u9664");
            delButton.addActionListener(e -> delButtonActionPerformed(e));
            panel2.add(delButton);
            delButton.setBounds(425, 300, 135, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(15, 310, 790, 350);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
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
        setSize(830, 735);
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(man);
        buttonGroup1.add(woman);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        fillerItem("search");
        fillerItem("modify");
        fillTable(new Book());
        this.man.setSelected(true);
    }

    /**
     * 填充表格
     *
     * @param book 要筛选的图书
     * @return void
     * @since 2020/4/12
     */
    private void fillTable(Book book) {
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            ResultSet resultSet = BookDao.list(conn, book);
            while (resultSet.next()) {
                Vector all = new Vector();
                all.add(resultSet.getString("id"));
                all.add(resultSet.getString("bookName"));
                all.add(resultSet.getString("author"));
                all.add(resultSet.getString("sex"));
                all.add(resultSet.getString("bookTypeName"));
                all.add(resultSet.getString("bookDesc"));
                all.add(resultSet.getFloat("price"));
                dtm.addRow(all);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem5;
    private JMenuItem menuItem2;
    private JMenu menu5;
    private JMenuItem menuItem3;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField bookNameTXT;
    private JLabel label2;
    private JTextField authorTXT;
    private JLabel label3;
    private JComboBox booktypeCB;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JLabel label4;
    private JTextField idTXT;
    private JLabel label5;
    private JTextField BookNameTXT;
    private JLabel label6;
    private JTextField priceTXT;
    private JLabel label7;
    private JTextField bookauthorTXT;
    private JLabel label8;
    private JRadioButton man;
    private JRadioButton woman;
    private JLabel label9;
    private JComboBox typeCB;
    private JLabel label10;
    private JScrollPane scrollPane2;
    private JTextArea bookDescTXT;
    private JButton updateButton;
    private JButton delButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
