package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.AnimalDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AnimalRepository {

    private final JdbcTemplate jdbcTemplate;

    public AnimalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AnimalDto createAnimal(AnimalDto animal) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_insert");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_an_re_cd_refugio", animal.getAnReCdRefugio()),
                Map.entry("p_an_nm_animal", animal.getAnNmAnimal()),
                Map.entry("p_an_tp_animal", animal.getAnTpAnimal()),
                Map.entry("p_an_de_breed", animal.getAnDeBreed()),
                Map.entry("p_an_de_color", animal.getAnDeColor()),
                Map.entry("p_an_tp_size", animal.getAnTpSize()),
                Map.entry("p_an_tp_sex", animal.getAnTpSex()),
                Map.entry("p_an_nu_approx_age", animal.getAnNuApproxAge()),
                Map.entry("p_an_de_animal", animal.getAnDeAnimal()),
                Map.entry("p_an_in_require_vet_review", animal.getAnInRequireVetReview()),
                Map.entry("p_an_de_observacion_vet", animal.getAnDeObservacionVet()),
                Map.entry("p_an_st_vet_review", animal.getAnStVetReview())
        );

        jdbcCall.execute(inParams);
        return animal;
    }

    public AnimalDto updateAnimal(AnimalDto animal) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_update");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_an_cd_animal", animal.getAnCdAnimal()),
                Map.entry("p_an_re_cd_refugio", animal.getAnReCdRefugio()),
                Map.entry("p_an_nm_animal", animal.getAnNmAnimal()),
                Map.entry("p_an_tp_animal", animal.getAnTpAnimal()),
                Map.entry("p_an_de_breed", animal.getAnDeBreed()),
                Map.entry("p_an_de_color", animal.getAnDeColor()),
                Map.entry("p_an_tp_size", animal.getAnTpSize()),
                Map.entry("p_an_tp_sex", animal.getAnTpSex()),
                Map.entry("p_an_nu_approx_age", animal.getAnNuApproxAge()),
                Map.entry("p_an_de_animal", animal.getAnDeAnimal()),
                Map.entry("p_an_in_require_vet_review", animal.getAnInRequireVetReview()),
                Map.entry("p_an_de_observacion_vet", animal.getAnDeObservacionVet()),
                Map.entry("p_an_st_vet_review", animal.getAnStVetReview())
        );

        jdbcCall.execute(inParams);
        return animal;
    }

    public void deleteAnimal(int animalId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_delete");

        Map<String, Object> inParams = Map.of("p_an_cd_animal", animalId);

        jdbcCall.execute(inParams);
    }

    public List<AnimalDto> searchAnimals(Integer animalId, String type, String size, String sex, String vetReviewStatus) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_search")
                .returningResultSet("animals", (rs, rowNum) -> {
                    AnimalDto animal = new AnimalDto();
                    animal.setAnCdAnimal(rs.getInt("an_cd_animal"));
                    animal.setAnReCdRefugio(rs.getInt("an_re_cd_refugio"));
                    animal.setAnNmAnimal(rs.getString("an_nm_animal"));
                    animal.setAnTpAnimal(rs.getString("an_tp_animal"));
                    animal.setAnDeBreed(rs.getString("an_de_breed"));
                    animal.setAnDeColor(rs.getString("an_de_color"));
                    animal.setAnTpSize(rs.getString("an_tp_size"));
                    animal.setAnTpSex(rs.getString("an_tp_sex"));
                    animal.setAnNuApproxAge(rs.getInt("an_nu_approx_age"));
                    animal.setAnDeAnimal(rs.getString("an_de_animal"));
                    animal.setAnInRequireVetReview(rs.getString("an_in_require_vet_review"));
                    animal.setAnDeObservacionVet(rs.getString("an_de_observacion_vet"));
                    animal.setAnStVetReview(rs.getString("an_st_vet_review"));
                    animal.setAnDtCreated(rs.getDate("an_dt_created"));
                    animal.setAnDtUpdated(rs.getDate("an_dt_updated"));
                    return animal;
                });

        Map<String, Object> inParams = Map.of(
                "p_an_cd_animal", animalId,
                "p_an_tp_animal", type,
                "p_an_tp_size", size,
                "p_an_tp_sex", sex,
                "p_an_st_vet_review", vetReviewStatus
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<AnimalDto>) out.get("animals");
    }
}
