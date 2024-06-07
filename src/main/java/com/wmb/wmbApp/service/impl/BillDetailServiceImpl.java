package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.entity.BillDetail;
import com.wmb.wmbApp.repository.BillDetailRepository;
import com.wmb.wmbApp.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> createBulk(List<BillDetail> billDetails) {
        return billDetailRepository.saveAllAndFlush(billDetails);
    }
}
