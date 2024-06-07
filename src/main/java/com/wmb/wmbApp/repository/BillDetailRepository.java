package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, String> {
}
