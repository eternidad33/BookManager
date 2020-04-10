/*
 * Created by JFormDesigner on Fri Apr 10 17:49:13 CST 2020
 */

package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author vigilr
 */
public class About extends JFrame {
    public About() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setTitle("\u5173\u4e8e");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("\u5f00\u53d1\u8005\uff1aVigilr");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        contentPane.add(label1, BorderLayout.CENTER);

        //---- label2 ----
        label2.setText("\u5f00\u53d1\u65e5\u671f\uff1a2020\u5e744\u670810\u65e5");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        contentPane.add(label2, BorderLayout.SOUTH);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/images/susu.jpg")));
        contentPane.add(label3, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
