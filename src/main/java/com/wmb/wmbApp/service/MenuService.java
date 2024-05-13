package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;

import java.util.List;

public interface MenuService {
    Menu create(Menu menu);
    Menu getById(String id);
    List<Menu> getAll(SearchMenuRequest request);
    Menu update(Menu menu);
    void deleteById(String id);
}
