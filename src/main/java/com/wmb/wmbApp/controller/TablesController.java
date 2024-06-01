package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.entity.Tables;
import com.wmb.wmbApp.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TABLES_API)
public class TablesController {

    private final TablesService tablesService;

    @PostMapping
    public Tables createNewTables(@RequestBody Tables request){
        return tablesService.create(request);
    }
}
