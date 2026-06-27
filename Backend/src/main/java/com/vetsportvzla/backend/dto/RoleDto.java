package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @JsonProperty("ro_cd_role")
    private int roCdRole;

    @JsonProperty("ro_nm_role")
    private String roNmRole;

    @JsonProperty("ro_st_role")
    private String roStRole;

    @JsonProperty("ro_dt_created")
    private Date roDtCreated;

    @JsonProperty("ro_dt_updated")
    private Date roDtUpdated;
}
