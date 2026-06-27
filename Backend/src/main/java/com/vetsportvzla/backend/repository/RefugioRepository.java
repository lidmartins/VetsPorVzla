package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RefugioDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RefugioRepository {

    private final JdbcTemplate jdbcTemplate;

    public RefugioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RefugioDto createRefugio(RefugioDto refugio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_insert")
                .returningResultSet("refugio", BeanPropertyRowMapper.newInstance(RefugioDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_re_cd_contacto", refugio.getReCdContacto());
        inParams.put("p_re_ur_cd_ubicacion", refugio.getReUrCdUbicacion());
        inParams.put("p_re_nm_refugio", refugio.getReNmRefugio());
        inParams.put("p_re_st_refugio", refugio.getReStRefugio());
        inParams.put("p_re_nu_capacity_total", refugio.getReNuCapacityTotal());
        inParams.put("p_re_nu_capacity_available", refugio.getReNuCapacityAvailable());
        inParams.put("p_re_tp_species_allowed", refugio.getReTpSpeciesAllowed());
        inParams.put("p_re_tp_animal_special_needs", refugio.getReTpAnimalSpecialNeeds());
        inParams.put("p_re_in_has_pets", refugio.getReInHasPets());
        inParams.put("p_re_tp_housing", refugio.getReTpHousing());
        inParams.put("p_re_in_fence_housing", refugio.getReInFenceHousing());
        inParams.put("p_re_de_additional_note", refugio.getReDeAdditionalNote());
        inParams.put("p_re_de_observacion_vet", refugio.getReDeObservacionVet());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RefugioDto> refugios = (List<RefugioDto>) out.get("refugio");
        return refugios.get(0);
    }

    public RefugioDto updateRefugio(RefugioDto refugio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_update")
                .returningResultSet("refugio", BeanPropertyRowMapper.newInstance(RefugioDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_re_cd_refugio", refugio.getReCdRefugio());
        inParams.put("p_re_cd_contacto", refugio.getReCdContacto());
        inParams.put("p_re_ur_cd_ubicacion", refugio.getReUrCdUbicacion());
        inParams.put("p_re_nm_refugio", refugio.getReNmRefugio());
        inParams.put("p_re_st_refugio", refugio.getReStRefugio());
        inParams.put("p_re_nu_capacity_total", refugio.getReNuCapacityTotal());
        inParams.put("p_re_nu_capacity_available", refugio.getReNuCapacityAvailable());
        inParams.put("p_re_tp_species_allowed", refugio.getReTpSpeciesAllowed());
        inParams.put("p_re_tp_animal_special_needs", refugio.getReTpAnimalSpecialNeeds());
        inParams.put("p_re_in_has_pets", refugio.getReInHasPets());
        inParams.put("p_re_tp_housing", refugio.getReTpHousing());
        inParams.put("p_re_in_fence_housing", refugio.getReInFenceHousing());
        inParams.put("p_re_de_additional_note", refugio.getReDeAdditionalNote());
        inParams.put("p_re_de_observacion_vet", refugio.getReDeObservacionVet());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<RefugioDto> refugios = (List<RefugioDto>) out.get("refugio");
        return refugios.get(0);
    }

    public void deleteRefugio(int refugioId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_re_cd_refugio", refugioId);

        jdbcCall.execute(inParams);
    }

    public List<RefugioDto> searchRefugios(Integer refugioId, Integer ubicacionId, String status, String speciesAllowed) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_search")
                .returningResultSet("refugios", BeanPropertyRowMapper.newInstance(RefugioDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_re_cd_refugio", refugioId);
        inParams.put("p_re_ur_cd_ubicacion", ubicacionId);
        inParams.put("p_re_st_refugio", status);
        inParams.put("p_re_tp_species_allowed", speciesAllowed);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RefugioDto>) out.get("refugios");
    }
}
