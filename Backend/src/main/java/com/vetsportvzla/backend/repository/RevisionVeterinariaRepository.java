package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RevisionVeterinariaDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RevisionVeterinariaRepository {

    private final JdbcTemplate jdbcTemplate;

    public RevisionVeterinariaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RevisionVeterinariaDto createRevisionVeterinaria(RevisionVeterinariaDto revision) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_insert")
                .returningResultSet("revision", BeanPropertyRowMapper.newInstance(RevisionVeterinariaDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_rv_an_cd_animal", revision.getRvAnCdAnimal());
        inParams.put("p_rv_us_cd_user", revision.getRvUsCdUser());
        inParams.put("p_rv_st_vet_review", revision.getRvStVetReview());
        inParams.put("p_rv_de_comment", revision.getRvDeComment());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RevisionVeterinariaDto> revisiones = (List<RevisionVeterinariaDto>) out.get("revision");
        return revisiones.get(0);
    }

    public RevisionVeterinariaDto updateRevisionVeterinaria(RevisionVeterinariaDto revision) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_update")
                .returningResultSet("revision", BeanPropertyRowMapper.newInstance(RevisionVeterinariaDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_rv_cd_revision_vet", revision.getRvCdRevisionVet());
        inParams.put("p_rv_an_cd_animal", revision.getRvAnCdAnimal());
        inParams.put("p_rv_us_cd_user", revision.getRvUsCdUser());
        inParams.put("p_rv_st_vet_review", revision.getRvStVetReview());
        inParams.put("p_rv_de_comment", revision.getRvDeComment());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RevisionVeterinariaDto> revisiones = (List<RevisionVeterinariaDto>) out.get("revision");
        return revisiones.get(0);
    }

    public void deleteRevisionVeterinaria(int revisionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_rv_cd_revision_vet", revisionId);

        jdbcCall.execute(inParams);
    }

    public List<RevisionVeterinariaDto> searchRevisionesVeterinarias(Integer revisionId, Integer animalId, Integer userId, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_search")
                .returningResultSet("revisiones", BeanPropertyRowMapper.newInstance(RevisionVeterinariaDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_rv_cd_revision_vet", revisionId);
        inParams.put("p_rv_an_cd_animal", animalId);
        inParams.put("p_rv_us_cd_user", userId);
        inParams.put("p_rv_st_vet_review", status);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RevisionVeterinariaDto>) out.get("revisiones");
    }
}
