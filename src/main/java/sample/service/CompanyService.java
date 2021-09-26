package sample.service;

import sample.entity.response.Report;

import java.util.List;

public interface CompanyService {

    List<Report> init();

    void add(String companyText);
}
