package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.UserDto;
import com.vetsportvzla.backend.dto.UserLoginLookup;
import com.vetsportvzla.backend.dto.UserLoginResponse;
import com.vetsportvzla.backend.exception.UnauthorizedException;
import com.vetsportvzla.backend.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto user) {
        user.setUsDePasswordHash(passwordEncoder.encode(user.getUsDePasswordHash()));
        return userRepository.createUser(user);
    }

    public UserDto updateUser(UserDto user) {
        if (user.getUsDePasswordHash() != null && !user.getUsDePasswordHash().isBlank()) {
            user.setUsDePasswordHash(passwordEncoder.encode(user.getUsDePasswordHash()));
        }
        return userRepository.updateUser(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    public List<UserDto> searchUsers(Integer userId, Integer roleId, String firstName, String lastName, String email, String status) {
        return userRepository.searchUsers(userId, roleId, firstName, lastName, email, status);
    }

    public UserLoginResponse loginUser(String username, String password) {
        UserLoginLookup lookup;
        try {
            lookup = userRepository.loginUser(username);
        } catch (DataAccessException e) {
            Throwable cause = e;
            while (cause.getCause() != null && cause.getCause() != cause) {
                cause = cause.getCause();
            }
            if ("user/password not found".equals(cause.getMessage())) {
                throw new UnauthorizedException("user/password not found");
            }
            throw e;
        }

        if (!passwordEncoder.matches(password, lookup.getPasswordHash())) {
            throw new UnauthorizedException("user/password not found");
        }

        return new UserLoginResponse(
                lookup.getUserId(),
                lookup.getUsername(),
                lookup.getFullName(),
                lookup.getEmail(),
                lookup.getRoleId(),
                lookup.getRoleName()
        );
    }
}
