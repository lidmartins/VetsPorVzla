package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionDto {
    @JsonProperty("ur_cd_ubicacion")
    private int urCdUbicacion;

    @JsonProperty("ur_es_cd_estado")
    private int urEsCdEstado;

    @JsonProperty("ur_nm_city")
    private String urNmCity;

    @JsonProperty("ur_nm_sector")
    private String urNmSector;

    @JsonProperty("ur_de_address")
    private String urDeAddress;

    @JsonProperty("ur_de_reference")
    private String urDeReference;

    @JsonProperty("ur_de_postal_code")
    private String urDePostalCode;

    @JsonProperty("ur_nu_latitude")
    private BigDecimal urNuLatitude;

    @JsonProperty("ur_nu_longitude")
    private BigDecimal urNuLongitude;

    @JsonProperty("ur_dt_created")
    private Date urDtCreated;

    @JsonProperty("ur_dt_updated")
    private Date urDtUpdated;
}
