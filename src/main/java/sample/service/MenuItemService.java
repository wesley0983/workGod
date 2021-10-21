package sample.service;

import sample.entity.po.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem findMenuRoot();

//    List<MenuItem> findMenuItemAllList();

    List<MenuItem> getMenuItemByParent(int parentId);
}
