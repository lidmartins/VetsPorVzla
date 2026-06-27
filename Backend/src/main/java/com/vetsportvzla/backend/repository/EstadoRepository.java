package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.EstadoDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EstadoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EstadoDto createEstado(EstadoDto estado) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_insert")
                .returningResultSet("estado", BeanPropertyRowMapper.newInstance(EstadoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_es_cd_country", estado.getEsCdCountry());
        inParams.put("p_es_nm_estado", estado.getEsNmEstado());
        inParams.put("p_es_st_estado", estado.getEsStEstado());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<EstadoDto> estados = (List<EstadoDto>) out.get("estado");
        return estados.get(0);
    }

    public EstadoDto updateEstado(EstadoDto estado) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_update")
                .returningResultSet("estado", BeanPropertyRowMapper.newInstance(EstadoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_es_cd_estado", estado.getEsCdEstado());
        inParams.put("p_es_cd_country", estado.getEsCdCountry());
        inParams.put("p_es_nm_estado", estado.getEsNmEstado());
        inParams.put("p_es_st_estado", estado.getEsStEstado());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<EstadoDto> estados = (List<EstadoDto>) out.get("estado");
        return estados.get(0);
    }

    public void deleteEstado(int estadoId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_es_cd_estado", estadoId);

        jdbcCall.execute(inParams);
    }

    public List<EstadoDto> searchEstados(Integer estadoId, String nombre, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_search")
                .returningResultSet("estados", BeanPropertyRowMapper.newInstance(EstadoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_es_cd_estado", estadoId);
        inParams.put("p_es_nm_estado", nombre);
        inParams.put("p_es_st_estado", status);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<EstadoDto>) out.get("estados");
    }
}
