/*
 * Created by JFormDesigner on Fri Apr 10 21:02:51 CST 2020
 */

package view;

import dao.BookTypeDao;
import entity.TableBooktype;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author vigilr
 */
public class BookTypeManagerFrm extends JFrame {
    public BookTypeManagerFrm() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        bookTypeQuery(e);
    }

    /**
     * 图书查询事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void bookTypeQuery(ActionEvent ex) {
        String name = this.bookTypeNameTxt.getText();
        TableBooktype tableBooktype = new TableBooktype();
        tableBooktype.setBooktypename(name);
        this.fillTable(tableBooktype);
    }

    private void table1MousePressed(MouseEvent e) {
        bookTypeTableMousePressed(e);
    }

    /**
     * 选中表格数据事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void bookTypeTableMousePressed(MouseEvent ex) {
        //获取行号
        int row = this.table1.getSelectedRow();
        this.idTxt.setText(this.table1.getValueAt(row, 0).toString());
        this.nameTxt.setText(this.table1.getValueAt(row, 1).toString());
        this.descTxt.setText(this.table1.getValueAt(row, 2).toString());
    }

    private void button2ActionPerformed(ActionEvent e) {
        bookTypeupdate(e);
    }

    /**
     * 修改图书类别信息
     *
     * @return void
     * @since 2020/4/12
     */
    private void bookTypeupdate(ActionEvent ex) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String desc = descTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        TableBooktype tableBooktype = new TableBooktype(Integer.parseInt(id), name, desc);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            int modify = BookTypeDao.update(conn, tableBooktype);
            if (modify == 1) {
                JOptionPane.showMessageDialog(null, "修改成功");
                this.resetValue();
                this.fillTable(new TableBooktype());
            } else {
                JOptionPane.showMessageDialog(null, "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 清空输入框信息
     *
     * @return void
     * @since 2020/4/12
     */
    private void resetValue() {
        this.idTxt.setText("");
        this.nameTxt.setText("");
        this.descTxt.setText("");
    }

    /**
     * 删除按钮事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void button3ActionPerformed(ActionEvent e) {
        String id = idTxt.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除记录吗？");
        if (n == 0) {
            Connection connection = null;
            try {
                connection = dbUtil.getConnection();
                int deleteNum = BookTypeDao.delete(connection, id);
                if (deleteNum == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new TableBooktype());
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                try {
                    dbUtil.closeConnection(connection);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加按钮事件处理
     *
     * @return void
     * @since 2020/4/12
     */
    private void button4ActionPerformed(ActionEvent e) {
        dispose();
        new BookTypeAddFrm().setVisible(true);
    }

    private void initComponents() {
        dispose();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        bookTypeNameTxt = new JTextField();
        button1 = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        idTxt = new JTextField();
        label3 = new JLabel();
        nameTxt = new JTextField();
        label4 = new JLabel();
        scrollPane2 = new JScrollPane();
        descTxt = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u7c7b\u578b\u7ba1\u7406");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u7f16\u53f7", "\u7c7b\u578b\u540d\u79f0", "\u63cf\u8ff0"
                }
            ));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    table1MousePressed(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(10, 35, 435, 200);

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\u67e5\u8be2");
        contentPane.add(label1);
        label1.setBounds(10, 10, 90, 22);
        contentPane.add(bookTypeNameTxt);
        bookTypeNameTxt.setBounds(115, 5, 210, 27);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(330, 5, 80, 30);

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("\u8868\u5355\u64cd\u4f5c"));
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u7f16\u53f7");
            panel1.add(label2);
            label2.setBounds(35, 35, 55, label2.getPreferredSize().height);

            //---- idTxt ----
            idTxt.setEditable(false);
            panel1.add(idTxt);
            idTxt.setBounds(95, 35, 45, idTxt.getPreferredSize().height);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0");
            panel1.add(label3);
            label3.setBounds(165, 40, 75, label3.getPreferredSize().height);
            panel1.add(nameTxt);
            nameTxt.setBounds(270, 40, 135, nameTxt.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u63cf\u8ff0");
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(30, 75), label4.getPreferredSize()));

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(descTxt);
            }
            panel1.add(scrollPane2);
            scrollPane2.setBounds(95, 80, 310, 95);

            //---- button2 ----
            button2.setText("\u4fee\u6539");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(new Rectangle(new Point(165, 205), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("\u5220\u9664");
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);
            button3.setBounds(new Rectangle(new Point(295, 205), button3.getPreferredSize()));

            //---- button4 ----
            button4.setText("\u6dfb\u52a0");
            button4.addActionListener(e -> {
			button4ActionPerformed(e);
			button4ActionPerformed(e);
			button4ActionPerformed(e);
			button4ActionPerformed(e);
		});
            panel1.add(button4);
            button4.setBounds(new Rectangle(new Point(40, 205), button4.getPreferredSize()));

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
        panel1.setBounds(15, 245, 430, 245);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new TableBooktype());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JTextField bookTypeNameTxt;
    private JButton button1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField idTxt;
    private JLabel label3;
    private JTextField nameTxt;
    private JLabel label4;
    private JScrollPane scrollPane2;
    private JTextArea descTxt;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    DbUtil dbUtil = new DbUtil();

    /**
     * 填充表格
     *
     * @return void
     * @since 2020/4/12
     */
    private void fillTable(TableBooktype tableBooktype) {
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            ResultSet resultSet = BookTypeDao.list(conn, tableBooktype);
            while (resultSet.next()) {
                Vector all = new Vector();
                all.add(resultSet.getString("id"));
                all.add(resultSet.getString("bookTypeName"));
                all.add(resultSet.getString("bookTypeDesc"));
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
}
