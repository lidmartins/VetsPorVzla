package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.UserDto;
import com.vetsportvzla.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto user) {
        return userRepository.createUser(user);
    }

    public UserDto updateUser(UserDto user) {
        return userRepository.updateUser(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    public List<UserDto> searchUsers(Integer userId, Integer roleId, String firstName, String lastName, String email, String status) {
        return userRepository.searchUsers(userId, roleId, firstName, lastName, email, status);
    }
}
