package sample.repository;

import org.springframework.data.repository.CrudRepository;
import sample.entity.Company;

public interface CompanyRepository extends CrudRepository<Company,Integer> {

}
