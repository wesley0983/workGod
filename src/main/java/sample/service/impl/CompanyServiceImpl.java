package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Company;
import sample.repository.CompanyRepository;
import sample.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void add(String companyText) {
        Company company = new Company();
        company.setName(companyText);
        companyRepository.save(company);
    }
}
