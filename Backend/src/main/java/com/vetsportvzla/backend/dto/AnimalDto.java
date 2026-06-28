package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    @JsonProperty("an_cd_animal")
    private int anCdAnimal;

    @JsonProperty("an_re_cd_refugio")
    private Integer anReCdRefugio;

    @JsonProperty("an_nm_animal")
    private String anNmAnimal;

    @JsonProperty("an_tp_animal")
    private String anTpAnimal;

    @JsonProperty("an_de_breed")
    private String anDeBreed;

    @JsonProperty("an_de_color")
    private String anDeColor;

    @JsonProperty("an_tp_size")
    private String anTpSize;

    @JsonProperty("an_tp_sex")
    private String anTpSex;

    @JsonProperty("an_nu_approx_age")
    private Integer anNuApproxAge;

    @JsonProperty("an_de_animal")
    private String anDeAnimal;

    @JsonProperty("an_in_require_vet_review")
    private String anInRequireVetReview;

    @JsonProperty("an_de_observacion_vet")
    private String anDeObservacionVet;

    @JsonProperty("an_st_vet_review")
    private String anStVetReview;

    @JsonProperty("an_dt_created")
    private Date anDtCreated;

    @JsonProperty("an_dt_updated")
    private Date anDtUpdated;

    @JsonProperty("an_report_type")
    private String anReportType;

    @JsonProperty("an_ubicacion")
    private String anUbicacion;

    @JsonProperty("an_telefono")
    private String anTelefono;

}
