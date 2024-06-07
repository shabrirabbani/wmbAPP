package com.wmb.wmbApp.repository;

import com.wmb.wmbApp.constant.UserRole;
import com.wmb.wmbApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
