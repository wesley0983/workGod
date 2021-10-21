package sample.entity.po;

import sample.entity.SimpleEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MenuItem extends SimpleEntity {

    @Column
    private int parent;

    @Column
    private String key;

    @Column
    private String value;

    @Column
    private Boolean expanded;

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
