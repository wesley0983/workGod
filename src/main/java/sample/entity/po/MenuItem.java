package sample.entity.po;

import sample.entity.SimpleEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MenuItem extends SimpleEntity {

    @Column//-1為根，0為子
    private int parent;

    @Column
    private String usViewBeanName;

    @Column
    private String usControllerBeanName;

    @Column
    private String barName;

    @Column//1為展開、0反之
    private Boolean expanded;

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getUsViewBeanName() {
        return usViewBeanName;
    }

    public void setUsViewBeanName(String usViewBeanName) {
        this.usViewBeanName = usViewBeanName;
    }

    public String getUsControllerBeanName() {
        return usControllerBeanName;
    }

    public void setUsControllerBeanName(String usControllerBeanName) {
        this.usControllerBeanName = usControllerBeanName;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String value) {
        this.barName = value;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return this.barName;
    }
}
