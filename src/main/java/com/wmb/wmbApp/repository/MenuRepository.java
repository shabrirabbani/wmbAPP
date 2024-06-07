package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
}
