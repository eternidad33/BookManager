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
        // TODO add your code here
        System.exit(1);
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        // TODO add your code here
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

    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        new BookTypeAddFrm().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menu4 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu3 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        desktopPane1 = new JDesktopPane();

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
                menu1.setText("\u57fa\u672c\u6570\u636e\u7ef4\u62a4");

                //======== menu4 ========
                {
                    menu4.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");

                    //---- menuItem3 ----
                    menuItem3.setText("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
                    menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                    menu4.add(menuItem3);

                    //---- menuItem4 ----
                    menuItem4.setText("\u56fe\u4e66\u7c7b\u522b\u7ef4\u62a4");
                    menu4.add(menuItem4);
                }
                menu1.add(menu4);

                //======== menu3 ========
                {
                    menu3.setText("\u56fe\u4e66\u7ba1\u7406");

                    //---- menuItem5 ----
                    menuItem5.setText("\u56fe\u4e66\u6dfb\u52a0");
                    menu3.add(menuItem5);

                    //---- menuItem6 ----
                    menuItem6.setText("\u56fe\u4e66\u7ef4\u62a4");
                    menu3.add(menuItem6);
                }
                menu1.add(menu3);

                //---- menuItem2 ----
                menuItem2.setText("\u5b89\u5168\u9000\u51fa");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

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
        contentPane.add(desktopPane1);
        desktopPane1.setBounds(0, 0, 460, 375);

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
    private JMenu menu4;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenu menu3;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JDesktopPane desktopPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
