package sample.service;

import sample.entity.po.Product;

public interface ProductService {

    Product addProduct(String productText, String comboboxValue);

    void editProduct(String companyOldName,String productOld,String productNewName);
}
