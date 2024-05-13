package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;
import com.wmb.wmbApp.repository.MenuRepository;
import com.wmb.wmbApp.service.MenuService;
import com.wmb.wmbApp.spesification.CustomerSpecification;
import com.wmb.wmbApp.spesification.MenuSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public Menu getById(String id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isEmpty()){
            throw new RuntimeException("menu not found");
        }
        return optionalMenu.get();
    }

    @Override
    public List<Menu> getAll(SearchMenuRequest request) {
        Specification<Menu> menuSpecification = MenuSpecification.getSpecification(request);
        if (request.getMenuName() == null && request.getPrice() == null){
            return menuRepository.findAll();
        }
        return menuRepository.findAll(menuSpecification);
    }


    @Override
    public Menu update(Menu menu) {
        getById(menu.getId());
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void deleteById(String id) {
        Menu currentMenu = findByIdOrThrowNotFound(id);
        menuRepository.delete(currentMenu);
    }

    public Menu findByIdOrThrowNotFound(String id){
        return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("menu not found"));
    }
}
