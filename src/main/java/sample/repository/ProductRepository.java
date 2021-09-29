package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.entity.po.Company;
import sample.entity.po.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCompany_Id(Integer id);

    Product findByNameAndCompany(String name, Company company);
}
