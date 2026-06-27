package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefugioDto {
    @JsonProperty("re_cd_refugio")
    private int reCdRefugio;

    @JsonProperty("re_cd_contacto")
    private int reCdContacto;

    @JsonProperty("re_ur_cd_ubicacion")
    private int reUrCdUbicacion;

    @JsonProperty("re_nm_refugio")
    private String reNmRefugio;

    @JsonProperty("re_st_refugio")
    private String reStRefugio;

    @JsonProperty("re_nu_capacity_total")
    private int reNuCapacityTotal;

    @JsonProperty("re_nu_capacity_available")
    private int reNuCapacityAvailable;

    @JsonProperty("re_tp_species_allowed")
    private String reTpSpeciesAllowed;

    @JsonProperty("re_tp_animal_special_needs")
    private String reTpAnimalSpecialNeeds;

    @JsonProperty("re_in_has_pets")
    private String reInHasPets;

    @JsonProperty("re_tp_housing")
    private String reTpHousing;

    @JsonProperty("re_in_fence_housing")
    private String reInFenceHousing;

    @JsonProperty("re_de_additional_note")
    private String reDeAdditionalNote;

    @JsonProperty("re_de_observacion_vet")
    private String reDeObservacionVet;

    @JsonProperty("re_dt_created")
    private Date reDtCreated;

    @JsonProperty("re_dt_updated")
    private Date reDtUpdated;
}
