package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.po.MenuItem;
import sample.repository.MenuItemRepository;
import sample.service.MenuItemService;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;


    @Override
    public MenuItem findMenuRoot() {
        Optional<MenuItem> optional = menuItemRepository.findById(0);
        if (optional.isEmpty())
            throw new RuntimeException("找無帳號");

        return optional.get();
    }

//    @Override
//    public List<MenuItem> findMenuItemAllList() {
//        Iterable<MenuItem> all = menuItemRepository.findAll();
//        return all.iterator().
//    }

    @Override
    public List<MenuItem> getMenuItemByParent(int parentId) {
        return menuItemRepository.findByParent(parentId);
    }

}
