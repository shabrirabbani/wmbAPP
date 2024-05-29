package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.BillRequest;
import com.wmb.wmbApp.dto.response.BillResponse;
import com.wmb.wmbApp.entity.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    BillResponse create(BillRequest request);
    List<BillResponse> getAll();
}
