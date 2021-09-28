package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.repository.CompanyRepository;
import sample.repository.ProductRepository;
import sample.service.ProductService;

import javax.persistence.EntityManager;

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
        EntityManager em = null;
        Company car = em.getReference(Company.class, company.getId());

        product.setCompany(car);
        em.persist(product);
        return productRepository.save(product);
    }
}
