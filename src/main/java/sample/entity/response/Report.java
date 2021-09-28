package sample.entity.response;

import sample.entity.po.Company;
import sample.entity.po.Product;

public class Report {//extends SimpleEntity {

    private String companyName;
    private String productName;
    private Integer company_id;

    public Report() {
    }

    public Report(Company company) {
        this.companyName = company.getName();
    }

    public Report(Company company, Product product){
        this.companyName = company.getName();
        this.productName = product.getName();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}

