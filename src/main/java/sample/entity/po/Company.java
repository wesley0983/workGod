package sample.entity.po;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company extends SimpleEntity {

    private static final long serialVersionUID = 3322779769469363315L;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
