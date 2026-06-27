package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RoleDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RoleDto createRole(RoleDto role) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_insert")
                .returningResultSet("role", BeanPropertyRowMapper.newInstance(RoleDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ro_nm_role", role.getRoNmRole());
        inParams.put("p_ro_st_role", role.getRoStRole());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RoleDto> roles = (List<RoleDto>) out.get("role");
        return roles.get(0);
    }

    public RoleDto updateRole(RoleDto role) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_update")
                .returningResultSet("role", BeanPropertyRowMapper.newInstance(RoleDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ro_cd_role", role.getRoCdRole());
        inParams.put("p_ro_nm_role", role.getRoNmRole());
        inParams.put("p_ro_st_role", role.getRoStRole());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RoleDto> roles = (List<RoleDto>) out.get("role");
        return roles.get(0);
    }

    public void deleteRole(int roleId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ro_cd_role", roleId);

        jdbcCall.execute(inParams);
    }

    public List<RoleDto> searchRoles(Integer roleId, String name, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_search")
                .returningResultSet("roles", BeanPropertyRowMapper.newInstance(RoleDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ro_cd_role", roleId);
        inParams.put("p_ro_nm_role", name);
        inParams.put("p_ro_st_role", status);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RoleDto>) out.get("roles");
    }
}
