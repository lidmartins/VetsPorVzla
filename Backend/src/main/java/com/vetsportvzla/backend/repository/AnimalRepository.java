package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.AnimalDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
                .withProcedureName("sp_animal_insert")
                .returningResultSet("animal", BeanPropertyRowMapper.newInstance(AnimalDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_an_re_cd_refugio", animal.getAnReCdRefugio());
        inParams.put("p_an_report_type", animal.getAnReportType());
        inParams.put("p_an_nm_animal", animal.getAnNmAnimal());
        inParams.put("p_an_tp_animal", animal.getAnTpAnimal());
        inParams.put("p_an_de_breed", animal.getAnDeBreed());
        inParams.put("p_an_de_color", animal.getAnDeColor());
        inParams.put("p_an_tp_size", animal.getAnTpSize());
        inParams.put("p_an_tp_sex", animal.getAnTpSex());
        inParams.put("p_an_nu_approx_age", animal.getAnNuApproxAge());
        inParams.put("p_an_de_animal", animal.getAnDeAnimal());
        inParams.put("p_an_in_require_vet_review", animal.getAnInRequireVetReview());
        inParams.put("p_an_de_observacion_vet", animal.getAnDeObservacionVet());
        inParams.put("p_an_st_vet_review", animal.getAnStVetReview());
        inParams.put("p_an_ubicacion", animal.getAnUbicacion());
        inParams.put("p_an_telefono", animal.getAnTelefono());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<AnimalDto> animals = (List<AnimalDto>) out.get("animal");
        return animals.get(0);
    }

    public AnimalDto updateAnimal(AnimalDto animal) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_update")
                .returningResultSet("animal", BeanPropertyRowMapper.newInstance(AnimalDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_an_cd_animal", animal.getAnCdAnimal());
        inParams.put("p_an_re_cd_refugio", animal.getAnReCdRefugio());
        inParams.put("p_an_report_type", animal.getAnReportType());
        inParams.put("p_an_nm_animal", animal.getAnNmAnimal());
        inParams.put("p_an_tp_animal", animal.getAnTpAnimal());
        inParams.put("p_an_de_breed", animal.getAnDeBreed());
        inParams.put("p_an_de_color", animal.getAnDeColor());
        inParams.put("p_an_tp_size", animal.getAnTpSize());
        inParams.put("p_an_tp_sex", animal.getAnTpSex());
        inParams.put("p_an_nu_approx_age", animal.getAnNuApproxAge());
        inParams.put("p_an_de_animal", animal.getAnDeAnimal());
        inParams.put("p_an_in_require_vet_review", animal.getAnInRequireVetReview());
        inParams.put("p_an_de_observacion_vet", animal.getAnDeObservacionVet());
        inParams.put("p_an_st_vet_review", animal.getAnStVetReview());
        inParams.put("p_an_ubicacion", animal.getAnUbicacion());
        inParams.put("p_an_telefono", animal.getAnTelefono());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<AnimalDto> animals = (List<AnimalDto>) out.get("animal");
        return animals.get(0);
    }

    public void deleteAnimal(int animalId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_an_cd_animal", animalId);

        jdbcCall.execute(inParams);
    }

    public List<AnimalDto> searchAnimals(Integer animalId, String type, String size, String sex, String vetReviewStatus) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_animal_search")
                .returningResultSet("animals", BeanPropertyRowMapper.newInstance(AnimalDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_an_cd_animal", animalId);
        inParams.put("p_an_tp_animal", type);
        inParams.put("p_an_tp_size", size);
        inParams.put("p_an_tp_sex", sex);
        inParams.put("p_an_st_vet_review", vetReviewStatus);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<AnimalDto>) out.get("animals");
    }
}
