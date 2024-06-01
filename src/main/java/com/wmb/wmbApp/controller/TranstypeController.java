package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.entity.Transtype;
import com.wmb.wmbApp.service.TranstypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSTYPE_API)
public class TranstypeController {

    private final TranstypeService transtypeService;

    @PostMapping
    public Transtype createNewTranstype(@RequestBody Transtype request){
        return transtypeService.create(request);
    }
}
