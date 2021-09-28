package sample.entity.po;

import sample.entity.SimpleEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company extends SimpleEntity {

    private static final long serialVersionUID = 3322779769469363315L;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "company")//mappedBy屬性表示為非被維護方
    private Set<Product> productSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
