package com.UserService.service.impl;

import com.UserService.data.entity.Role;
import com.UserService.data.repository.RoleRepository;
import com.UserService.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class RoleServiceImpl implements RoleService {

    final  RoleRepository roleRepository;

    @Override
    public Role createOneRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getOneRole(String name) {
        return roleRepository.findByName(name);
    }
}
