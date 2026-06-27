package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.UserDto;
import com.vetsportvzla.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1, 1, "John", "Doe", "john.doe@example.com", "1234567890", "password", "S", "A", new Date(), new Date(), new Date());
    }

    @Test
    void createUser() {
        when(userRepository.createUser(any(UserDto.class))).thenReturn(userDto);
        UserDto created = userService.createUser(userDto);
        assertEquals(userDto, created);
        verify(userRepository, times(1)).createUser(userDto);
    }

    @Test
    void updateUser() {
        when(userRepository.updateUser(any(UserDto.class))).thenReturn(userDto);
        UserDto updated = userService.updateUser(userDto);
        assertEquals(userDto, updated);
        verify(userRepository, times(1)).updateUser(userDto);
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteUser(1);
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteUser(1);
    }

    @Test
    void searchUsers() {
        when(userRepository.searchUsers(any(), any(), any(), any(), any(), any())).thenReturn(List.of(userDto));
        List<UserDto> users = userService.searchUsers(1, 1, "John", "Doe", "john.doe@example.com", "A");
        assertEquals(1, users.size());
        assertEquals(userDto, users.get(0));
        verify(userRepository, times(1)).searchUsers(1, 1, "John", "Doe", "john.doe@example.com", "A");
    }
}
