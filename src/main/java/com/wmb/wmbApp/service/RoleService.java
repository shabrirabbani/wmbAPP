package com.wmb.wmbApp.service;

import com.wmb.wmbApp.constant.UserRole;
import com.wmb.wmbApp.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);
}
