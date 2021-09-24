package sample.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class SimpleEntity implements Serializable {

    private static final long serialVersionUID = -3114586408094374691L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主鍵由資料庫自動生成
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
