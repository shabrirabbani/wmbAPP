package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.dto.request.BillRequest;
import com.wmb.wmbApp.dto.response.BillResponse;
import com.wmb.wmbApp.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
}
