package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.NewMenuRequest;
import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    Menu create(NewMenuRequest menu);
    Menu getById(String id);
    Page<Menu> getAll(SearchMenuRequest request);
    Menu update(Menu menu);
    void deleteById(String id);
}
