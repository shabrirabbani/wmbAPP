package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends JpaRepository<Tables, String> {
}
