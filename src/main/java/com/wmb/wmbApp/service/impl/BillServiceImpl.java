package com.wmb.wmbApp.service.impl;


import com.wmb.wmbApp.dto.request.BillRequest;
import com.wmb.wmbApp.dto.response.BillDetailResponse;
import com.wmb.wmbApp.dto.response.BillResponse;
import com.wmb.wmbApp.entity.*;
import com.wmb.wmbApp.repository.BillDetailRepository;
import com.wmb.wmbApp.repository.BillRepository;
import com.wmb.wmbApp.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;
    private final BillDetailService billDetailService;
    private final CustomerService customerService;
    private final TablesService tablesService;
    private final MenuService menuService;



    @Override
    public BillResponse create(BillRequest request) {

        Customer customer = customerService.getById(request.getCustomerId());
        Tables tables = tablesService.getById(request.getTableId());

        Bill bill = Bill.builder()
                .customer(customer)
                .tables(tables)
                .transDate(new Date())
                .build();

        List<BillDetail> billDetail = request.getBillDetails().stream().map(
                detailRequest -> {
                    log.info("qty dari detail request {}", detailRequest.getQty());

                    Menu menu = menuService.getById(detailRequest.getMenuId());

                    return BillDetail.builder()
                            .menuId(menu)
                            .bill(bill)
                            .qty(detailRequest.getQty())
                            .build();
                }
        ).toList();

        billDetailService.createBulk(billDetail);
        bill.setBillDetails(billDetail);

        List<BillDetailResponse> billDetailResponse = billDetail.stream()
                .map(detail -> {
                    return BillDetailResponse.builder()
                            .id(detail.getId())
                            .menuId(detail.getMenuId().getId())
                            .qty(detail.getQty())
                            .build();
                }).toList();

        return BillResponse.builder()
                .id(bill.getId())
                .customerId(bill.getCustomer().getId())
                .transDate(bill.getTransDate())
                .billDetails(billDetailResponse)
                .build();
    }

    @Override
    public List<BillResponse> getAll() {
        List<Bill> bills = billRepository.findAll();

        return bills.stream().map(bill -> {
            List<BillDetailResponse> billDetailResponse = bill.getBillDetails().stream().map(detail -> {
                return BillDetailResponse.builder()
                        .id(detail.getId())
                        .menuId(detail.getMenuId().getId())
                        .qty(detail.getQty())
                        .build();
            }).toList();

            return BillResponse.builder()
                    .id(bill.getId())
                    .customerId(bill.getCustomer().getId())
                    .transDate(bill.getTransDate())
                    .billDetails(billDetailResponse)
                    .build();
        }).toList();
    }
}
