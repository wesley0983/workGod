package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.entity.response.Report;
import sample.repository.CompanyRepository;
import sample.repository.ProductRepository;
import sample.service.CompanyService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Report> init() {
        System.out.println("++++++++++++++");

        Iterable<Company> all = companyRepository.findAll();
        List<Report> reportList = new ArrayList<>();

        Iterator<Company> it = all.iterator();
        while (it.hasNext()) {
            Company company = it.next();
//            List<Product> productList = productRepository.findByCompany_id(company.getId());
//            Report report = new Report(company, product);
            Report report = new Report();
            report.setCompanyName(company.getName());
            report.setProductName("bbb");
            reportList.add(report);
        }
        return reportList;
    }

    @Override
    public void add(String companyText) {
        Company company = new Company();
        company.setName(companyText);
        companyRepository.save(company);
    }
}
