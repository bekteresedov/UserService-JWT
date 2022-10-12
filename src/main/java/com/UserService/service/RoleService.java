package com.UserService.service;

import com.UserService.data.entity.Role;

public interface RoleService {

    Role createOneRole(Role role);

    Role getOneRole(String name);
}
