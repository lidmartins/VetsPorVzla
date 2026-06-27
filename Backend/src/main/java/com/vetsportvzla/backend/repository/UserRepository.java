package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
                .withProcedureName("sp_user_insert")
                .returningResultSet("user", BeanPropertyRowMapper.newInstance(UserDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_us_ro_cd_role", user.getUsRoCdRole());
        inParams.put("p_us_nm_first_name", user.getUsNmFirstName());
        inParams.put("p_us_nm_last_name", user.getUsNmLastName());
        inParams.put("p_us_de_email", user.getUsDeEmail());
        inParams.put("p_us_de_phone", user.getUsDePhone());
        inParams.put("p_us_de_password_hash", user.getUsDePasswordHash());
        inParams.put("p_us_in_veterinarian", user.getUsInVeterinarian());
        inParams.put("p_us_st_user", user.getUsStUser());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<UserDto> users = (List<UserDto>) out.get("user");
        return users.get(0);
    }

    public UserDto updateUser(UserDto user) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_update")
                .returningResultSet("user", BeanPropertyRowMapper.newInstance(UserDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_us_cd_user", user.getUsCdUser());
        inParams.put("p_us_ro_cd_role", user.getUsRoCdRole());
        inParams.put("p_us_nm_first_name", user.getUsNmFirstName());
        inParams.put("p_us_nm_last_name", user.getUsNmLastName());
        inParams.put("p_us_de_email", user.getUsDeEmail());
        inParams.put("p_us_de_phone", user.getUsDePhone());
        inParams.put("p_us_de_password_hash", user.getUsDePasswordHash());
        inParams.put("p_us_in_veterinarian", user.getUsInVeterinarian());
        inParams.put("p_us_st_user", user.getUsStUser());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<UserDto> users = (List<UserDto>) out.get("user");
        return users.get(0);
    }

    public void deleteUser(int userId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_us_cd_user", userId);

        jdbcCall.execute(inParams);
    }

    public List<UserDto> searchUsers(Integer userId, Integer roleId, String firstName, String lastName, String email, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_user_search")
                .returningResultSet("users", BeanPropertyRowMapper.newInstance(UserDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_us_cd_user", userId);
        inParams.put("p_us_ro_cd_role", roleId);
        inParams.put("p_us_nm_first_name", firstName);
        inParams.put("p_us_nm_last_name", lastName);
        inParams.put("p_us_de_email", email);
        inParams.put("p_us_st_user", status);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<UserDto>) out.get("users");
    }
}
