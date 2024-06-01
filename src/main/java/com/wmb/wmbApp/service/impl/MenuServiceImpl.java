package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.dto.request.NewMenuRequest;
import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;
import com.wmb.wmbApp.repository.MenuRepository;
import com.wmb.wmbApp.service.MenuService;
import com.wmb.wmbApp.spesification.CustomerSpecification;
import com.wmb.wmbApp.spesification.MenuSpecification;
import com.wmb.wmbApp.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Menu create(NewMenuRequest menuRequest) {
        validationUtil.validate(menuRequest);

        Menu newMenu = Menu.builder()
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();

        return menuRepository.saveAndFlush(newMenu);
    }

    @Override
    public Menu getById(String id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "menu not found");
        }
        return optionalMenu.get();
    }

    @Override
    public Page<Menu> getAll(SearchMenuRequest menuRequest) {

        if(menuRequest.getPage() <= 0){
            menuRequest.setPage(1);
        }

        String validSortBy;
        if("name".equalsIgnoreCase(menuRequest.getSortBy()) || "price".equalsIgnoreCase(menuRequest.getSortBy())){
            validSortBy = menuRequest.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(menuRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((menuRequest.getPage() - 1), menuRequest.getSize(), sort);

        if (menuRequest.getName() == null && menuRequest.getPrice() == null){
            return menuRepository.findAll(pageable);
        }

        Specification<Menu> specification = MenuSpecification.getSpecification(menuRequest);

        return menuRepository.findAll(specification, pageable);
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
