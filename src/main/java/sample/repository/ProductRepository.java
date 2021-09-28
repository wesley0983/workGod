package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.entity.po.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

//    public List<Product> findByCompanyid(Integer companyId);

    List<Product> findByCompany_Id(Integer id);
}
