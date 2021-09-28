package sample.entity.response;

import sample.entity.po.Company;

public class Owner {

    private String name;

    public Owner() {
    }

    public Owner(Company company) {
        this.name = company.getName();
    }

    public Owner(Report report) {
        this.name = report.getCompanyName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
