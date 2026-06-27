package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.UbicacionDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UbicacionRepository {

    private final JdbcTemplate jdbcTemplate;

    public UbicacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UbicacionDto createUbicacion(UbicacionDto ubicacion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_insert")
                .returningResultSet("ubicacion", BeanPropertyRowMapper.newInstance(UbicacionDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ur_es_cd_estado", ubicacion.getUrEsCdEstado());
        inParams.put("p_ur_nm_city", ubicacion.getUrNmCity());
        inParams.put("p_ur_nm_sector", ubicacion.getUrNmSector());
        inParams.put("p_ur_de_address", ubicacion.getUrDeAddress());
        inParams.put("p_ur_de_reference", ubicacion.getUrDeReference());
        inParams.put("p_ur_de_postal_code", ubicacion.getUrDePostalCode());
        inParams.put("p_ur_nu_latitude", ubicacion.getUrNuLatitude());
        inParams.put("p_ur_nu_longitude", ubicacion.getUrNuLongitude());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<UbicacionDto> ubicaciones = (List<UbicacionDto>) out.get("ubicacion");
        return ubicaciones.get(0);
    }

    public UbicacionDto updateUbicacion(UbicacionDto ubicacion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_update")
                .returningResultSet("ubicacion", BeanPropertyRowMapper.newInstance(UbicacionDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ur_cd_ubicacion", ubicacion.getUrCdUbicacion());
        inParams.put("p_ur_es_cd_estado", ubicacion.getUrEsCdEstado());
        inParams.put("p_ur_nm_city", ubicacion.getUrNmCity());
        inParams.put("p_ur_nm_sector", ubicacion.getUrNmSector());
        inParams.put("p_ur_de_address", ubicacion.getUrDeAddress());
        inParams.put("p_ur_de_reference", ubicacion.getUrDeReference());
        inParams.put("p_ur_de_postal_code", ubicacion.getUrDePostalCode());
        inParams.put("p_ur_nu_latitude", ubicacion.getUrNuLatitude());
        inParams.put("p_ur_nu_longitude", ubicacion.getUrNuLongitude());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<UbicacionDto> ubicaciones = (List<UbicacionDto>) out.get("ubicacion");
        return ubicaciones.get(0);
    }

    public void deleteUbicacion(int ubicacionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ur_cd_ubicacion", ubicacionId);

        jdbcCall.execute(inParams);
    }

    public List<UbicacionDto> searchUbicaciones(Integer ubicacionId, Integer estadoId, String city) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_search")
                .returningResultSet("ubicaciones", BeanPropertyRowMapper.newInstance(UbicacionDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_ur_cd_ubicacion", ubicacionId);
        inParams.put("p_ur_es_cd_estado", estadoId);
        inParams.put("p_ur_nm_city", city);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<UbicacionDto>) out.get("ubicaciones");
    }
}
