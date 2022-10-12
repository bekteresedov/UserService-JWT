package com.UserService.service;

import com.UserService.data.dto.request.AddRoleToUser;
import com.UserService.data.entity.User;

import java.util.List;

public interface UserService {

    User createOneUser(User user);

    List<User>getAllUsers();

    User getOneUser(String username);

    void addRoleToUser(AddRoleToUser addRoleToUser);

}
