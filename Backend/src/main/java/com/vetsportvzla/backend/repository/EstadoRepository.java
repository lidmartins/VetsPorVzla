package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.EstadoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .withProcedureName("sp_estado_insert");

        Map<String, Object> inParams = Map.of(
                "p_es_cd_country", estado.getEsCdCountry(),
                "p_es_nm_estado", estado.getEsNmEstado(),
                "p_es_st_estado", estado.getEsStEstado()
        );

        jdbcCall.execute(inParams);
        return estado;
    }

    public EstadoDto updateEstado(EstadoDto estado) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_update");

        Map<String, Object> inParams = Map.of(
                "p_es_cd_estado", estado.getEsCdEstado(),
                "p_es_cd_country", estado.getEsCdCountry(),
                "p_es_nm_estado", estado.getEsNmEstado(),
                "p_es_st_estado", estado.getEsStEstado()
        );

        jdbcCall.execute(inParams);
        return estado;
    }

    public void deleteEstado(int estadoId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_delete");

        Map<String, Object> inParams = Map.of("p_es_cd_estado", estadoId);

        jdbcCall.execute(inParams);
    }

    public List<EstadoDto> searchEstados(Integer estadoId, String nombre, String status) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_estado_search")
                .returningResultSet("estados", (rs, rowNum) -> {
                    EstadoDto estado = new EstadoDto();
                    estado.setEsCdEstado(rs.getInt("es_cd_estado"));
                    estado.setEsCdCountry(rs.getInt("es_cd_country"));
                    estado.setEsNmEstado(rs.getString("es_nm_estado"));
                    estado.setEsStEstado(rs.getString("es_st_estado"));
                    estado.setEsDtCreated(rs.getDate("es_dt_created"));
                    estado.setEsDtUpdated(rs.getDate("es_dt_updated"));
                    return estado;
                });

        Map<String, Object> inParams = Map.of(
                "p_es_cd_estado", estadoId,
                "p_es_nm_estado", nombre,
                "p_es_st_estado", status
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<EstadoDto>) out.get("estados");
    }
}
