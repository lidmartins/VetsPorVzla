package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.SolicitudDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
                .withProcedureName("sp_solicitud_insert")
                .returningResultSet("solicitud", BeanPropertyRowMapper.newInstance(SolicitudDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_so_an_cd_animal", solicitud.getSoAnCdAnimal());
        inParams.put("p_so_co_cd_contacto", solicitud.getSoCoCdContacto());
        inParams.put("p_so_ur_cd_ubicacion", solicitud.getSoUrCdUbicacion());
        inParams.put("p_so_tp_solicitud", solicitud.getSoTpSolicitud());
        inParams.put("p_so_dt_evento", solicitud.getSoDtEvento());
        inParams.put("p_so_st_solicitud", solicitud.getSoStSolicitud());
        inParams.put("p_so_de_observacion_vet", solicitud.getSoDeObservacionVet());
        inParams.put("p_so_de_s3_folder_path", solicitud.getSoDeS3FolderPath());
        inParams.put("p_so_de_main_photo_url", solicitud.getSoDeMainPhotoUrl());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<SolicitudDto> solicitudes = (List<SolicitudDto>) out.get("solicitud");
        return solicitudes.get(0);
    }

    public SolicitudDto updateSolicitud(SolicitudDto solicitud) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_update")
                .returningResultSet("solicitud", BeanPropertyRowMapper.newInstance(SolicitudDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_so_cd_solicitud", solicitud.getSoCdSolicitud());
        inParams.put("p_so_an_cd_animal", solicitud.getSoAnCdAnimal());
        inParams.put("p_so_co_cd_contacto", solicitud.getSoCoCdContacto());
        inParams.put("p_so_ur_cd_ubicacion", solicitud.getSoUrCdUbicacion());
        inParams.put("p_so_tp_solicitud", solicitud.getSoTpSolicitud());
        inParams.put("p_so_dt_evento", solicitud.getSoDtEvento());
        inParams.put("p_so_st_solicitud", solicitud.getSoStSolicitud());
        inParams.put("p_so_de_observacion_vet", solicitud.getSoDeObservacionVet());
        inParams.put("p_so_de_s3_folder_path", solicitud.getSoDeS3FolderPath());
        inParams.put("p_so_de_main_photo_url", solicitud.getSoDeMainPhotoUrl());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<SolicitudDto> solicitudes = (List<SolicitudDto>) out.get("solicitud");
        return solicitudes.get(0);
    }

    public void deleteSolicitud(int solicitudId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_so_cd_solicitud", solicitudId);

        jdbcCall.execute(inParams);
    }

    public List<SolicitudDto> searchSolicitudes(Integer solicitudId, String type, String status, Integer ubicacionId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_solicitud_search")
                .returningResultSet("solicitudes", BeanPropertyRowMapper.newInstance(SolicitudDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_so_cd_solicitud", solicitudId);
        inParams.put("p_so_tp_solicitud", type);
        inParams.put("p_so_st_solicitud", status);
        inParams.put("p_so_ur_cd_ubicacion", ubicacionId);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<SolicitudDto>) out.get("solicitudes");
    }
}
