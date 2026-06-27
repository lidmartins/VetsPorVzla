package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.UbicacionDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .withProcedureName("sp_ubicacion_insert");

        Map<String, Object> inParams = Map.of(
                "p_ur_es_cd_estado", ubicacion.getUrEsCdEstado(),
                "p_ur_nm_city", ubicacion.getUrNmCity(),
                "p_ur_nm_sector", ubicacion.getUrNmSector(),
                "p_ur_de_address", ubicacion.getUrDeAddress(),
                "p_ur_de_reference", ubicacion.getUrDeReference(),
                "p_ur_de_postal_code", ubicacion.getUrDePostalCode(),
                "p_ur_nu_latitude", ubicacion.getUrNuLatitude(),
                "p_ur_nu_longitude", ubicacion.getUrNuLongitude()
        );

        jdbcCall.execute(inParams);
        return ubicacion;
    }

    public UbicacionDto updateUbicacion(UbicacionDto ubicacion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_update");

        Map<String, Object> inParams = Map.of(
                "p_ur_cd_ubicacion", ubicacion.getUrCdUbicacion(),
                "p_ur_es_cd_estado", ubicacion.getUrEsCdEstado(),
                "p_ur_nm_city", ubicacion.getUrNmCity(),
                "p_ur_nm_sector", ubicacion.getUrNmSector(),
                "p_ur_de_address", ubicacion.getUrDeAddress(),
                "p_ur_de_reference", ubicacion.getUrDeReference(),
                "p_ur_de_postal_code", ubicacion.getUrDePostalCode(),
                "p_ur_nu_latitude", ubicacion.getUrNuLatitude(),
                "p_ur_nu_longitude", ubicacion.getUrNuLongitude()
        );

        jdbcCall.execute(inParams);
        return ubicacion;
    }

    public void deleteUbicacion(int ubicacionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_delete");

        Map<String, Object> inParams = Map.of("p_ur_cd_ubicacion", ubicacionId);

        jdbcCall.execute(inParams);
    }

    public List<UbicacionDto> searchUbicaciones(Integer ubicacionId, Integer estadoId, String city) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ubicacion_search")
                .returningResultSet("ubicaciones", (rs, rowNum) -> {
                    UbicacionDto ubicacion = new UbicacionDto();
                    ubicacion.setUrCdUbicacion(rs.getInt("ur_cd_ubicacion"));
                    ubicacion.setUrEsCdEstado(rs.getInt("ur_es_cd_estado"));
                    ubicacion.setUrNmCity(rs.getString("ur_nm_city"));
                    ubicacion.setUrNmSector(rs.getString("ur_nm_sector"));
                    ubicacion.setUrDeAddress(rs.getString("ur_de_address"));
                    ubicacion.setUrDeReference(rs.getString("ur_de_reference"));
                    ubicacion.setUrDePostalCode(rs.getString("ur_de_postal_code"));
                    ubicacion.setUrNuLatitude(rs.getBigDecimal("ur_nu_latitude"));
                    ubicacion.setUrNuLongitude(rs.getBigDecimal("ur_nu_longitude"));
                    ubicacion.setUrDtCreated(rs.getDate("ur_dt_created"));
                    ubicacion.setUrDtUpdated(rs.getDate("ur_dt_updated"));
                    return ubicacion;
                });

        Map<String, Object> inParams = Map.of(
                "p_ur_cd_ubicacion", ubicacionId,
                "p_ur_es_cd_estado", estadoId,
                "p_ur_nm_city", city
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<UbicacionDto>) out.get("ubicaciones");
    }
}
