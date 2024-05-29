package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.dto.request.NewMenuRequest;
import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;
import com.wmb.wmbApp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public Menu createNewMenu(@RequestBody NewMenuRequest menuRequest){
        return menuService.create(menuRequest);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public Menu getMenuById(@PathVariable String id){
        return menuService.getById(id);
    }

    //get all
    @GetMapping
    public List<Menu> getAllMenu(
            @RequestParam(name = "menuName", required = false) String menuName,
            @RequestParam(name = "price" , required = false) Long price
    ){
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .name(menuName)
                .price(price)
                .build();

        return null;
    }

    @PutMapping
    public Menu updateMenu(@RequestBody Menu menu){
        return menuService.update(menu);
    }

    @DeleteMapping
    public String deleteById(@PathVariable String id){
        menuService.deleteById(id);
        return "success delete menu";
    }
}
