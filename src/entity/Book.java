package entity;

import java.io.Serializable;

/**
 * (Book)实体类
 *
 * @author makejava
 * @since 2020-04-11 10:34:06
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 920269858498853741L;
    
    private Integer id;
    
    private String bookname;
    
    private String author;
    
    private String sex;
    
    private Float price;
    
    private Integer booktypeid;
    
    private String bookdesc;

    public Book(String bookname, String author, String sex, float price, Integer booktypeid, String bookdesc) {
        this.bookname = bookname;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.booktypeid = booktypeid;
        this.bookdesc = bookdesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getBooktypeid() {
        return booktypeid;
    }

    public void setBooktypeid(Integer booktypeid) {
        this.booktypeid = booktypeid;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }

}