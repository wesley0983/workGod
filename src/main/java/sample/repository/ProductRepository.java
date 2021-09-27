package sample.repository;

import org.springframework.data.repository.CrudRepository;
import sample.entity.po.Product;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
