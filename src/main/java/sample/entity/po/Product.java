package sample.entity.po;

import sample.entity.SimpleEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Product extends SimpleEntity {

    private static final long serialVersionUID = 5438129052270178695L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
