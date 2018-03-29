package com.java_dapps.store.service;

import com.java_dapps.store.entity.Role;

public interface RoleService {
    Role getDefaultRole();

    Role getRoleByAuthority(String authority);
}
