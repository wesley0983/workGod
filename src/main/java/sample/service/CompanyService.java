package sample.service;

import sample.entity.response.Owner;
import sample.entity.response.VendorReport;

import java.util.List;

public interface CompanyService {

    List<VendorReport> init();

    List<Owner> findCompanyAll();

    VendorReport addCompany(String companyText);

    void editCompany(String companyOldName,String companyNewName);
}
