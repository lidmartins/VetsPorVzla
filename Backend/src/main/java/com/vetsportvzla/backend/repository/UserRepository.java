package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDto createUser(UserDto user) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_insert");

        Map<String, Object> inParams = Map.of(
                "p_us_ro_cd_role", user.getUsRoCdRole(),
                "p_us_nm_first_name", user.getUsNmFirstName(),
                "p_us_nm_last_name", user.getUsNmLastName(),
                "p_us_de_email", user.getUsDeEmail(),
                "p_us_de_phone", user.getUsDePhone(),
                "p_us_de_password_hash", user.getUsDePasswordHash(),
                "p_us_in_veterinarian", user.getUsInVeterinarian(),
                "p_us_st_user", user.getUsStUser(),
                "p_us_dt_last_login", user.getUsDtLastLogin()
        );

        jdbcCall.execute(inParams);
        return user;
    }

    public UserDto updateUser(UserDto user) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_update");

        Map<String, Object> inParams = Map.of(
                "p_us_cd_user", user.getUsCdUser(),
                "p_us_ro_cd_role", user.getUsRoCdRole(),
                "p_us_nm_first_name", user.getUsNmFirstName(),
                "p_us_nm_last_name", user.getUsNmLastName(),
                "p_us_de_email", user.getUsDeEmail(),
                "p_us_de_phone", user.getUsDePhone(),
                "p_us_de_password_hash", user.getUsDePasswordHash(),
                "p_us_in_veterinarian", user.getUsInVeterinarian(),
                "p_us_st_user", user.getUsStUser(),
                "p_us_dt_last_login", user.getUsDtLastLogin()
        );

        jdbcCall.execute(inParams);
        return user;
    }

    public void deleteUser(int userId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_delete");

        Map<String, Object> inParams = Map.of("p_us_cd_user", userId);

        jdbcCall.execute(inParams);
    }

    public List<UserDto> searchUsers(Integer userId, Integer roleId, String firstName, String lastName, String email, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_search")
                .returningResultSet("users", (rs, rowNum) -> {
                    UserDto user = new UserDto();
                    user.setUsCdUser(rs.getInt("us_cd_user"));
                    user.setUsRoCdRole(rs.getInt("us_ro_cd_role"));
                    user.setUsNmFirstName(rs.getString("us_nm_first_name"));
                    user.setUsNmLastName(rs.getString("us_nm_last_name"));
                    user.setUsDeEmail(rs.getString("us_de_email"));
                    user.setUsDePhone(rs.getString("us_de_phone"));
                    user.setUsInVeterinarian(rs.getString("us_in_veterinarian"));
                    user.setUsStUser(rs.getString("us_st_user"));
                    user.setUsDtLastLogin(rs.getDate("us_dt_last_login"));
                    user.setUsDtCreated(rs.getDate("us_dt_created"));
                    user.setUsDtUpdated(rs.getDate("us_dt_updated"));
                    return user;
                });

        Map<String, Object> inParams = Map.of(
                "p_us_cd_user", userId,
                "p_us_ro_cd_role", roleId,
                "p_us_nm_first_name", firstName,
                "p_us_nm_last_name", lastName,
                "p_us_de_email", email,
                "p_us_st_user", status
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<UserDto>) out.get("users");
    }
}
