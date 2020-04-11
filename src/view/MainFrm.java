/*
 * Created by JFormDesigner on Fri Apr 10 16:43:38 CST 2020
 */

package view;

import dao.BookDao;
import dao.BookTypeDao;
import entity.Book;
import entity.TableBooktype;
import util.DbUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
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

    private void menuItem1ActionPerformed(ActionEvent e) {
        About about = new About();
        about.setVisible(true);
    }

    private void fillerItem(String type) {
        Connection conn = null;
        TableBooktype booktype = null;
        try {
            conn = dbUtil.getConnection();
            ResultSet rs = BookTypeDao.list(conn, new TableBooktype());
            if ("search".equals(type)) {
                booktype = new TableBooktype();
                booktype.setBooktypename("请选择...");
                booktype.setId(-1);
                this.booktypeCB.addItem(booktype);
            }
            while (rs.next()) {
                booktype = new TableBooktype();
                booktype.setBooktypename(rs.getString("bookTypeName"));
                booktype.setId(rs.getInt("id"));
                if ("search".equals(type)) {
                    this.booktypeCB.addItem(booktype);
                } else if ("modify".equals(type)) {

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

    private void menuItem2ActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
        System.out.println(result);
        if (result == 0) {
            System.exit(1);
        }
    }


    private void menuItem5ActionPerformed(ActionEvent e) {
//        dispose();
        BookTypeQueryFrm bookTypeQueryFrm = new BookTypeQueryFrm();
        bookTypeQueryFrm.setVisible(true);

    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        BookAddFrm bookAddFrm = new BookAddFrm();
        bookAddFrm.setVisible(true);
//        this.table.add(bookAddFrm);
    }

    private void button1ActionPerformed(ActionEvent e) {
        String name = this.bookNameTXT.getText();
        String author = this.authorTXT.getText();
        TableBooktype tableBooktype = (TableBooktype) this.booktypeCB.getSelectedItem();
        this.fillTable(new Book(name, author, tableBooktype.getId()));
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
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        bookNameTXT = new JTextField();
        label2 = new JLabel();
        authorTXT = new JTextField();
        label3 = new JLabel();
        booktypeCB = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

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
                menuItem5.addActionListener(e -> {
                    menuItem5ActionPerformed(e);
                });
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

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u7f16\u53f7", "\u56fe\u4e66\u540d\u79f0", "\u4f5c\u8005", "\u4f5c\u8005\u6027\u522b", "\u56fe\u4e66\u7c7b\u522b", "\u63cf\u8ff0", "\u4ef7\u683c"
                    }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 120, 790, 250);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\u79f0");
            panel1.add(label1);
            label1.setBounds(20, 40, 60, label1.getPreferredSize().height);
            panel1.add(bookNameTXT);
            bookNameTXT.setBounds(95, 35, 125, bookNameTXT.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u4f5c\u8005");
            panel1.add(label2);
            label2.setBounds(260, 40, 30, label2.getPreferredSize().height);
            panel1.add(authorTXT);
            authorTXT.setBounds(320, 35, 90, authorTXT.getPreferredSize().height);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b");
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(435, 40), label3.getPreferredSize()));
            panel1.add(booktypeCB);
            booktypeCB.setBounds(500, 35, 131, booktypeCB.getPreferredSize().height);

            //---- button1 ----
            button1.setText("\u67e5\u8be2");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(670, 35, 90, button1.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
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

        //---- button2 ----
        button2.setText("\u4fee\u6539");
        contentPane.add(button2);
        button2.setBounds(140, 395, 135, button2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u5220\u9664");
        contentPane.add(button3);
        button3.setBounds(470, 395, 135, 30);

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
        this.fillTable(new Book());
        this.fillerItem("search");
        setSize(830, 510);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

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
                all.add(resultSet.getInt("bookTypeId"));
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
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField bookNameTXT;
    private JLabel label2;
    private JTextField authorTXT;
    private JLabel label3;
    private JComboBox booktypeCB;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
