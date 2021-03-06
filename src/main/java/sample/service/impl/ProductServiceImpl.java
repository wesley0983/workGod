package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.repository.CompanyRepository;
import sample.repository.ProductRepository;
import sample.service.ProductService;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Product addProduct(String productText, String comboboxValue) {
        Company company = companyRepository.findByName(comboboxValue);
        Product product = new Product();
        product.setName(productText);
        product.setCompany(company);
        return productRepository.save(product);
    }

    @Override
    public void editProduct(String companyOldName,String productOld, String productNewName) {
        Company company = companyRepository.findByName(companyOldName);
        Product product = productRepository.findByNameAndCompany(productOld,company);
        if (!Objects.isNull(product)){
            product.setName(productNewName);
            productRepository.save(product);
        }
    }
}
