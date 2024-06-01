package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.entity.Transtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranstypeRepository extends JpaRepository<Transtype, String> {

}
