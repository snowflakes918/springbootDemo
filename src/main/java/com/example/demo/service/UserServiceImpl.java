package com.example.demo.service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetail;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public User registerUser(UserRegistrationDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setStatus(true);

        User savedUser = userRepository.save(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstname(userDto.getFirstname());
        userDetail.setLastname(userDto.getLastname());
        userDetail.setEmail(userDto.getEmail());
        userDetail.setUser(savedUser);

        userDetailRepository.save(userDetail);
        return savedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUserStatus(Long userId, boolean activate) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus(activate);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
