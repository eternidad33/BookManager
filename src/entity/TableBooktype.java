package entity;

import java.io.Serializable;

/**
 * (TableBooktype)实体类
 *
 * @author makejava
 * @since 2020-04-10 18:47:15
 */
public class TableBooktype implements Serializable {
    private static final long serialVersionUID = -43707012852599825L;

    private Integer id;

    private String booktypename;

    private String booktypedesc;

    public TableBooktype(String booktypename, String booktypedesc) {
        this.booktypename = booktypename;
        this.booktypedesc = booktypedesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBooktypename() {
        return booktypename;
    }

    public void setBooktypename(String booktypename) {
        this.booktypename = booktypename;
    }

    public String getBooktypedesc() {
        return booktypedesc;
    }

    public void setBooktypedesc(String booktypedesc) {
        this.booktypedesc = booktypedesc;
    }

}