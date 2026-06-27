package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    @JsonProperty("so_cd_solicitud")
    private int soCdSolicitud;

    @JsonProperty("so_an_cd_animal")
    private int soAnCdAnimal;

    @JsonProperty("so_co_cd_contacto")
    private int soCoCdContacto;

    @JsonProperty("so_ur_cd_ubicacion")
    private int soUrCdUbicacion;

    @JsonProperty("so_tp_solicitud")
    private String soTpSolicitud;

    @JsonProperty("so_dt_evento")
    private Date soDtEvento;

    @JsonProperty("so_st_solicitud")
    private String soStSolicitud;

    @JsonProperty("so_de_observacion_vet")
    private String soDeObservacionVet;

    @JsonProperty("so_de_s3_folder_path")
    private String soDeS3FolderPath;

    @JsonProperty("so_de_main_photo_url")
    private String soDeMainPhotoUrl;

    @JsonProperty("so_dt_created")
    private Date soDtCreated;

    @JsonProperty("so_dt_updated")
    private Date soDtUpdated;
}
