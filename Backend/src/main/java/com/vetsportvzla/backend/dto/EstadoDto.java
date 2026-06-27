package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDto {
    @JsonProperty("es_cd_estado")
    private int esCdEstado;

    @JsonProperty("es_cd_country")
    private int esCdCountry;

    @JsonProperty("es_nm_estado")
    private String esNmEstado;

    @JsonProperty("es_st_estado")
    private String esStEstado;

    @JsonProperty("es_dt_created")
    private Date esDtCreated;

    @JsonProperty("es_dt_updated")
    private Date esDtUpdated;
}
