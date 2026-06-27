package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RoleDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .withProcedureName("sp_role_insert");

        Map<String, Object> inParams = Map.of(
                "p_ro_nm_role", role.getRoNmRole(),
                "p_ro_st_role", role.getRoStRole()
        );

        jdbcCall.execute(inParams);
        return role;
    }

    public RoleDto updateRole(RoleDto role) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_update");

        Map<String, Object> inParams = Map.of(
                "p_ro_cd_role", role.getRoCdRole(),
                "p_ro_nm_role", role.getRoNmRole(),
                "p_ro_st_role", role.getRoStRole()
        );

        jdbcCall.execute(inParams);
        return role;
    }

    public void deleteRole(int roleId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_delete");

        Map<String, Object> inParams = Map.of("p_ro_cd_role", roleId);

        jdbcCall.execute(inParams);
    }

    public List<RoleDto> searchRoles(Integer roleId, String name, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_role_search")
                .returningResultSet("roles", (rs, rowNum) -> {
                    RoleDto role = new RoleDto();
                    role.setRoCdRole(rs.getInt("ro_cd_role"));
                    role.setRoNmRole(rs.getString("ro_nm_role"));
                    role.setRoStRole(rs.getString("ro_st_role"));
                    role.setRoDtCreated(rs.getDate("ro_dt_created"));
                    role.setRoDtUpdated(rs.getDate("ro_dt_updated"));
                    return role;
                });

        Map<String, Object> inParams = Map.of(
                "p_ro_cd_role", roleId,
                "p_ro_nm_role", name,
                "p_ro_st_role", status
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RoleDto>) out.get("roles");
    }
}
