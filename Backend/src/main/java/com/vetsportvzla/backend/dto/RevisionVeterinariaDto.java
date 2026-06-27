package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevisionVeterinariaDto {
    @JsonProperty("rv_cd_revision_vet")
    private int rvCdRevisionVet;

    @JsonProperty("rv_an_cd_animal")
    private int rvAnCdAnimal;

    @JsonProperty("rv_us_cd_user")
    private int rvUsCdUser;

    @JsonProperty("rv_st_vet_review")
    private String rvStVetReview;

    @JsonProperty("rv_de_comment")
    private String rvDeComment;

    @JsonProperty("rv_dt_created")
    private Date rvDtCreated;

    @JsonProperty("rv_dt_updated")
    private Date rvDtUpdated;
}
