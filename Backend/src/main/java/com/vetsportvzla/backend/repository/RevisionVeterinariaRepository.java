package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RevisionVeterinariaDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .withProcedureName("sp_revision_veterinaria_insert");

        Map<String, Object> inParams = Map.of(
                "p_rv_an_cd_animal", revision.getRvAnCdAnimal(),
                "p_rv_us_cd_user", revision.getRvUsCdUser(),
                "p_rv_st_vet_review", revision.getRvStVetReview(),
                "p_rv_de_comment", revision.getRvDeComment()
        );

        jdbcCall.execute(inParams);
        return revision;
    }

    public RevisionVeterinariaDto updateRevisionVeterinaria(RevisionVeterinariaDto revision) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_update");

        Map<String, Object> inParams = Map.of(
                "p_rv_cd_revision_vet", revision.getRvCdRevisionVet(),
                "p_rv_an_cd_animal", revision.getRvAnCdAnimal(),
                "p_rv_us_cd_user", revision.getRvUsCdUser(),
                "p_rv_st_vet_review", revision.getRvStVetReview(),
                "p_rv_de_comment", revision.getRvDeComment()
        );

        jdbcCall.execute(inParams);
        return revision;
    }

    public void deleteRevisionVeterinaria(int revisionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_delete");

        Map<String, Object> inParams = Map.of("p_rv_cd_revision_vet", revisionId);

        jdbcCall.execute(inParams);
    }

    public List<RevisionVeterinariaDto> searchRevisionesVeterinarias(Integer revisionId, Integer animalId, Integer userId, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_revision_veterinaria_search")
                .returningResultSet("revisiones", (rs, rowNum) -> {
                    RevisionVeterinariaDto revision = new RevisionVeterinariaDto();
                    revision.setRvCdRevisionVet(rs.getInt("rv_cd_revision_vet"));
                    revision.setRvAnCdAnimal(rs.getInt("rv_an_cd_animal"));
                    revision.setRvUsCdUser(rs.getInt("rv_us_cd_user"));
                    revision.setRvStVetReview(rs.getString("rv_st_vet_review"));
                    revision.setRvDeComment(rs.getString("rv_de_comment"));
                    revision.setRvDtCreated(rs.getDate("rv_dt_created"));
                    revision.setRvDtUpdated(rs.getDate("rv_dt_updated"));
                    return revision;
                });

        Map<String, Object> inParams = Map.of(
                "p_rv_cd_revision_vet", revisionId,
                "p_rv_an_cd_animal", animalId,
                "p_rv_us_cd_user", userId,
                "p_rv_st_vet_review", status
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RevisionVeterinariaDto>) out.get("revisiones");
    }
}
