package com.UserService.service.impl;

import com.UserService.data.dto.request.AddRoleToUser;
import com.UserService.data.entity.Role;
import com.UserService.data.entity.User;
import com.UserService.data.repository.UserRepository;
import com.UserService.service.RoleService;
import com.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    final RoleService roleService;
    final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User createOneUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(AddRoleToUser addRoleToUser) {

        User user=userRepository.findByUsername(addRoleToUser.getUsername());
        Role role=roleService.getOneRole(addRoleToUser.getRoleName());
        user.getRoles().add(role);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User is not found.");
            }
            List<SimpleGrantedAuthority>list=user.getRoles().stream().map(e->new
                    SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),list);
    }
}
