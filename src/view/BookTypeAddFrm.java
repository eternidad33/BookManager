/*
 * Created by JFormDesigner on Fri Apr 10 19:06:11 CST 2020
 */

package view;

import dao.BookTypeDao;
import entity.TableBooktype;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

/**
 * @author vigilr
 */
public class BookTypeAddFrm extends JFrame {
    public BookTypeAddFrm() {
        initComponents();
    }

    private void button2ActionPerformed(ActionEvent e) {
        this.resetValue();
    }

    private void resetValue() {
        this.bookTypeNameTxt.setText("");
        this.bookTypeDescTxt.setText("");
    }

    private void button1ActionPerformed(ActionEvent e) {
        String bookTypeName = this.bookTypeNameTxt.getText();
        String bookTypeDesc = this.bookTypeDescTxt.getText();
        if (StringUtil.isEmpty(bookTypeName)) {
            JOptionPane.showMessageDialog(null, "图书类别名称不能为空");
            return;
        }
        TableBooktype tableBooktype = new TableBooktype(bookTypeName, bookTypeDesc);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            int n = BookTypeDao.add(conn, tableBooktype);
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "图书类别添加成功");
                this.resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "图书类别添加失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "图书类别添加失败");
        } finally {
            try {
                dbUtil.closeConnection(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        dispose();
        new MainFrm().setVisible(true);
    }

    DbUtil dbUtil = new DbUtil();

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label3 = new JLabel();
        label4 = new JLabel();
        bookTypeNameTxt = new JTextField();
        scrollPane1 = new JScrollPane();
        bookTypeDescTxt = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label3 ----
        label3.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0");
        contentPane.add(label3);
        label3.setBounds(25, 45, 110, 25);

        //---- label4 ----
        label4.setText("\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0");
        contentPane.add(label4);
        label4.setBounds(25, 90, 80, label4.getPreferredSize().height);
        contentPane.add(bookTypeNameTxt);
        bookTypeNameTxt.setBounds(160, 50, 155, bookTypeNameTxt.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bookTypeDescTxt);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(160, 95, 155, 130);

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(55, 285), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(190, 285), button2.getPreferredSize()));

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
        setSize(350, 385);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label3;
    private JLabel label4;
    private JTextField bookTypeNameTxt;
    private JScrollPane scrollPane1;
    private JTextArea bookTypeDescTxt;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
