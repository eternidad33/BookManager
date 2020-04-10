package model;

/**
 * 项目名 BookManager
 * <br>包名 model
 * <br>创建时间 2020/4/10 11:23
 * <br>描述 用户实体
 *
 * @author Vigilr
 */
public class User {
    private int id;
    private String userName;
    private String password;

    public User() {
        super();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
