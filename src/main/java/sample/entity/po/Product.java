package sample.entity.po;

import sample.entity.SimpleEntity;

import javax.persistence.*;

@Entity
public class Product extends SimpleEntity {

    private static final long serialVersionUID = 5438129052270178695L;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;


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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
