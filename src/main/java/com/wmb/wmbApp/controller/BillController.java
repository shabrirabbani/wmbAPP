package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.dto.request.BillRequest;
import com.wmb.wmbApp.dto.response.BillResponse;
import com.wmb.wmbApp.dto.response.CommonResponse;
import com.wmb.wmbApp.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BILL_API)
public class BillController {

    private final BillService billService;

    @PostMapping
    public BillResponse createNewBill(
            @RequestBody BillRequest request
            ){
        return billService.create(request);
    }

    @GetMapping
    public List<BillResponse> getAllBill(){
        return billService.getAll();
    }

}
