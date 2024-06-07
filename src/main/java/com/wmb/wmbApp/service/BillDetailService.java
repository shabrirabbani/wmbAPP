package com.wmb.wmbApp.service;

import com.wmb.wmbApp.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> createBulk(List<BillDetail> billDetails);
}
