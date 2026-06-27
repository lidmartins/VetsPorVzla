package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.SolicitudDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SolicitudRepository {

    private final JdbcTemplate jdbcTemplate;

    public SolicitudRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SolicitudDto createSolicitud(SolicitudDto solicitud) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_insert");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_so_an_cd_animal", solicitud.getSoAnCdAnimal()),
                Map.entry("p_so_co_cd_contacto", solicitud.getSoCoCdContacto()),
                Map.entry("p_so_ur_cd_ubicacion", solicitud.getSoUrCdUbicacion()),
                Map.entry("p_so_tp_solicitud", solicitud.getSoTpSolicitud()),
                Map.entry("p_so_dt_evento", solicitud.getSoDtEvento()),
                Map.entry("p_so_st_solicitud", solicitud.getSoStSolicitud()),
                Map.entry("p_so_de_observacion_vet", solicitud.getSoDeObservacionVet()),
                Map.entry("p_so_de_s3_folder_path", solicitud.getSoDeS3FolderPath()),
                Map.entry("p_so_de_main_photo_url", solicitud.getSoDeMainPhotoUrl())
        );

        jdbcCall.execute(inParams);
        return solicitud;
    }

    public SolicitudDto updateSolicitud(SolicitudDto solicitud) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_update");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_so_cd_solicitud", solicitud.getSoCdSolicitud()),
                Map.entry("p_so_an_cd_animal", solicitud.getSoAnCdAnimal()),
                Map.entry("p_so_co_cd_contacto", solicitud.getSoCoCdContacto()),
                Map.entry("p_so_ur_cd_ubicacion", solicitud.getSoUrCdUbicacion()),
                Map.entry("p_so_tp_solicitud", solicitud.getSoTpSolicitud()),
                Map.entry("p_so_dt_evento", solicitud.getSoDtEvento()),
                Map.entry("p_so_st_solicitud", solicitud.getSoStSolicitud()),
                Map.entry("p_so_de_observacion_vet", solicitud.getSoDeObservacionVet()),
                Map.entry("p_so_de_s3_folder_path", solicitud.getSoDeS3FolderPath()),
                Map.entry("p_so_de_main_photo_url", solicitud.getSoDeMainPhotoUrl())
        );

        jdbcCall.execute(inParams);
        return solicitud;
    }

    public void deleteSolicitud(int solicitudId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_delete");

        Map<String, Object> inParams = Map.of("p_so_cd_solicitud", solicitudId);

        jdbcCall.execute(inParams);
    }

    public List<SolicitudDto> searchSolicitudes(Integer solicitudId, String type, String status, Integer ubicacionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_search")
                .returningResultSet("solicitudes", (rs, rowNum) -> {
                    SolicitudDto solicitud = new SolicitudDto();
                    solicitud.setSoCdSolicitud(rs.getInt("so_cd_solicitud"));
                    solicitud.setSoAnCdAnimal(rs.getInt("so_an_cd_animal"));
                    solicitud.setSoCoCdContacto(rs.getInt("so_co_cd_contacto"));
                    solicitud.setSoUrCdUbicacion(rs.getInt("so_ur_cd_ubicacion"));
                    solicitud.setSoTpSolicitud(rs.getString("so_tp_solicitud"));
                    solicitud.setSoDtEvento(rs.getDate("so_dt_evento"));
                    solicitud.setSoStSolicitud(rs.getString("so_st_solicitud"));
                    solicitud.setSoDeObservacionVet(rs.getString("so_de_observacion_vet"));
                    solicitud.setSoDeS3FolderPath(rs.getString("so_de_s3_folder_path"));
                    solicitud.setSoDeMainPhotoUrl(rs.getString("so_de_main_photo_url"));
                    solicitud.setSoDtCreated(rs.getDate("so_dt_created"));
                    solicitud.setSoDtUpdated(rs.getDate("so_dt_updated"));
                    return solicitud;
                });

        Map<String, Object> inParams = Map.of(
                "p_so_cd_solicitud", solicitudId,
                "p_so_tp_solicitud", type,
                "p_so_st_solicitud", status,
                "p_so_ur_cd_ubicacion", ubicacionId
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<SolicitudDto>) out.get("solicitudes");
    }
}
