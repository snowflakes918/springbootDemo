package com.example.demo.service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDto userDto);
    void deleteUser(Long userId);
    void updateUserStatus(Long userId, boolean activate);
    List<User> getAllUsers();
    User getUserById(Long userId);
}
