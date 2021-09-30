package sample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.po.Company;
import sample.entity.po.Product;
import sample.entity.response.Owner;
import sample.entity.response.VendorReport;
import sample.repository.CompanyRepository;
import sample.repository.ProductRepository;
import sample.service.CompanyService;
import java.util.Objects;



import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<VendorReport> init() {
        List<Company> companyList = companyRepository.findAll();
        List<VendorReport> vendorReportList = new ArrayList<>();

        for (Company company: companyList){
            List<Product> productList = productRepository.findByCompany_Id(company.getId());
            if (productList.isEmpty()){
                vendorReportList.add(new VendorReport(company));
            }
            for (Product product: productList){
                vendorReportList.add(new VendorReport(company,product));
            }
        }

        return vendorReportList;
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
    public VendorReport addCompany(String companyText) {
        Company company = companyRepository.findByName(companyText);
        if (!Objects.isNull(company)){
            throw new RuntimeException("重複廠商");
        }
        Company addCompany = new Company();
        addCompany.setName(companyText);
        logger.debug("add, create company- Name: {}",addCompany.getName());
        Company save = companyRepository.save(addCompany);
        return new VendorReport(save);
    }


    @Override
    public void editCompany(String companyOldName, String companyNewName) {
        Company company = companyRepository.findByName(companyOldName);
        if (!Objects.isNull(company)){
            company.setName(companyNewName);
            companyRepository.save(company);
        }
    }
}
