package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.dto.request.NewMenuRequest;
import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.dto.response.CommonResponse;
import com.wmb.wmbApp.dto.response.PagingResponse;
import com.wmb.wmbApp.entity.Menu;
import com.wmb.wmbApp.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<CommonResponse<Menu>> createNewMenu(@RequestBody NewMenuRequest menuRequest){
        Menu newMenu = menuService.create(menuRequest);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully create new menu")
                .data(newMenu)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Menu>> getById(@PathVariable String id){
        Menu menu = menuService.getById(id);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("succesfully fetch data")
                .data(menu)
                .build();

        return ResponseEntity.ok(response);
    }

    //get all
    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getAllMenu(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price" , required = false) Long price
    ){
        SearchMenuRequest request = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .price(price)
                .build();

        Page<Menu> menuAll = menuService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(menuAll.getTotalPages())
                .totalElements(menuAll.getTotalElements())
                .page(menuAll.getPageable().getPageNumber() + 1)
                .size(menuAll.getPageable().getPageSize())
                .hasNext(menuAll.hasNext())
                .hasPrevious(menuAll.hasPrevious())
                .build();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get all menu")
                .data(menuAll.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Menu>> updateMenu(@RequestBody Menu menu){
        Menu update = menuService.update(menu);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("succesfully update data")
                .data(update)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteById(@PathVariable String id){
        menuService.deleteById(id);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("OK, Success Delete Product " + id)
                .build();

        return ResponseEntity.ok(response);
    }
}
