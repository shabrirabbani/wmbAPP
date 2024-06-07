package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.constant.UserRole;
import com.wmb.wmbApp.entity.Role;
import com.wmb.wmbApp.repository.RoleRepository;
import com.wmb.wmbApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role).orElseGet(
                () -> roleRepository.saveAndFlush(Role.builder()
                                .role(role).build())
        );
    }
}
