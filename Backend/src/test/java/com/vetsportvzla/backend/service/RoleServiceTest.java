package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RoleDto;
import com.vetsportvzla.backend.repository.RoleRepository;
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
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private RoleDto roleDto;

    @BeforeEach
    void setUp() {
        roleDto = new RoleDto(1, "ADMIN", "A", new Date(), new Date());
    }

    @Test
    void createRole() {
        when(roleRepository.createRole(any(RoleDto.class))).thenReturn(roleDto);
        RoleDto created = roleService.createRole(roleDto);
        assertEquals(roleDto, created);
        verify(roleRepository, times(1)).createRole(roleDto);
    }

    @Test
    void updateRole() {
        when(roleRepository.updateRole(any(RoleDto.class))).thenReturn(roleDto);
        RoleDto updated = roleService.updateRole(roleDto);
        assertEquals(roleDto, updated);
        verify(roleRepository, times(1)).updateRole(roleDto);
    }

    @Test
    void deleteRole() {
        doNothing().when(roleRepository).deleteRole(1);
        roleService.deleteRole(1);
        verify(roleRepository, times(1)).deleteRole(1);
    }

    @Test
    void searchRoles() {
        when(roleRepository.searchRoles(any(), any(), any())).thenReturn(List.of(roleDto));
        List<RoleDto> roles = roleService.searchRoles(1, "ADMIN", "A");
        assertEquals(1, roles.size());
        assertEquals(roleDto, roles.get(0));
        verify(roleRepository, times(1)).searchRoles(1, "ADMIN", "A");
    }
}
