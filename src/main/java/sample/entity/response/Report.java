package sample.entity.response;

import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.entity.po.SimpleEntity;

public class Report {//extends SimpleEntity {

    private String companyName;
    private String productName;

    public Report() {
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
}
