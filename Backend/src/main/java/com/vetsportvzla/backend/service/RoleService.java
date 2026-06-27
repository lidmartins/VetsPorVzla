package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RoleDto;
import com.vetsportvzla.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto createRole(RoleDto role) {
        return roleRepository.createRole(role);
    }

    public RoleDto updateRole(RoleDto role) {
        return roleRepository.updateRole(role);
    }

    public void deleteRole(int roleId) {
        roleRepository.deleteRole(roleId);
    }

    public List<RoleDto> searchRoles(Integer roleId, String name, String status) {
        return roleRepository.searchRoles(roleId, name, status);
    }
}
