package sample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.entity.po.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem,Integer> {

    List<MenuItem> findByParent(int parentId);
}
