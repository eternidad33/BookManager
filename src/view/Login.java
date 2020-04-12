package view;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import dao.UserDao;
import entity.User;
import util.DbUtil;
import util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


/**
 * 项目名 BookManager
 * <br>包名 view
 * <br>创建时间 2020/4/10 12:48
 * <br>描述 登录界面
 *
 * @author Vigilr
 */
public class Login {
    private JFrame frame;
    private JPanel panel1;
    private JTextField userNameTxt;
    private JTextField passwordTxt;
    private JButton loginButton;
    private JButton resetButton;

    private DbUtil dbUtil = new DbUtil();
    private UserDao userDao = new UserDao();

    public Login() {
        this.frame = new JFrame("管理员登录");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAction(e);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonActon(e);
            }
        });
        this.frame.setLocationRelativeTo(null);
    }

    /**
     * 创建登录事件
     *
     * @return void
     */
    private void loginButtonActon(ActionEvent evt) {
        String userName = this.userNameTxt.getText();
        String password = this.passwordTxt.getText();
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
        }
        User user = new User(userName, password);
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            User currentUser = userDao.login(conn, user);
            if (currentUser != null) {
                this.frame.setVisible(false);
                new MainFrm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误");
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
     * 重置事件处理
     *
     * @return void
     */
    private void resetAction(ActionEvent evt) {
        this.userNameTxt.setText("");
        this.passwordTxt.setText("");
    }

    public static void main(String[] args) throws Exception {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getConnection();
            System.out.println("数据库连接成功");
        } catch (CommunicationsException e) {
            System.out.println("数据库连接失败");
            JOptionPane.showMessageDialog(null,"数据库连接失败");
            return;
        }
        new Login();
    }
}
