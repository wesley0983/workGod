package sample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.controller.MainController;
import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.entity.response.Owner;
import sample.entity.response.Report;
import sample.repository.CompanyRepository;
import sample.repository.ProductRepository;
import sample.service.CompanyService;
import java.util.Objects;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Report> init() {
        Iterable<Company> all = companyRepository.findAll();
        List<Report> reportList = new ArrayList<>();

        all.forEach(item -> {
           reportList.add(new Report(item));
        });

        logger.debug("init, companyData size: {}",reportList.size());
        for(Report report :reportList){
            List<Product> productList = productRepository.findByCompany_Id(report.getCompany_id());
            for(Product product: productList){
                report.setProductName(product.getName());
            }
        }

        return reportList;
    }

    @Override
    public List<Owner> findCompanyAll() {
        List<Company> companyList = companyRepository.findAll();
        List<Owner> ownerList = new ArrayList<>();
        for (Company company: companyList){
            ownerList.add(new Owner(company));
        }

        return ownerList;
    }

    @Override
    public Report addCompany(String companyText) {
        Company company = companyRepository.findByName(companyText);
        if (!Objects.isNull(company)){
            throw new RuntimeException("重複廠商");
        }
        Company addCompany = new Company();
        addCompany.setName(companyText);
        logger.debug("add, create company- Name: {}",addCompany.getName());
        Company save = companyRepository.save(addCompany);
        return new Report(save);
    }
}
