package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    @Modifying
    @Query(value = "UPDATE m_customer SET isMember = :isMember WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id,
                      @Param("isMember") Boolean isMember);
}
