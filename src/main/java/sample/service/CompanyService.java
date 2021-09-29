package sample.service;

import sample.entity.po.Company;
import sample.entity.response.Owner;
import sample.entity.response.Report;

import java.util.List;

public interface CompanyService {

    List<Report> init();

    List<Owner> findCompanyAll();

    Report addCompany(String companyText);

    void editCompany(String companyOldName,String companyNewName);
}
