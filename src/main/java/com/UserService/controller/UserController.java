package com.UserService.controller;

import com.UserService.data.dto.request.AddRoleToUser;
import com.UserService.data.entity.User;
import com.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('user')")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('admin')")
    public User save(@RequestBody User user) {
        return userService.createOneUser(user);
    }

    @PatchMapping("/roles")
    public void addRoles(@RequestBody AddRoleToUser request) {
        userService.addRoleToUser(request);
    }
}
