/*
 * Created by JFormDesigner on Fri Apr 10 16:43:38 CST 2020
 */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    private void menuItem2ActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
        System.out.println(result);
        if (result == 0) {
            System.exit(1);
        }
    }


    private void menuItem5ActionPerformed(ActionEvent e) {
//        dispose();
        BookTypeQueryFrm bookTypeQueryFrm= new BookTypeQueryFrm();
        bookTypeQueryFrm.setVisible(true);

    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        BookAddFrm bookAddFrm=new BookAddFrm();
        bookAddFrm.setVisible(true);
//        this.table.add(bookAddFrm);
    }

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
        table = new JDesktopPane();

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
                menu1.setText("\u57fa\u672c\u6570\u636e\u64cd\u4f5c");

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
        contentPane.add(table);
        table.setBounds(0, 0, 460, 375);

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
    private JDesktopPane table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
