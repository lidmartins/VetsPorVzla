package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @JsonProperty("us_cd_user")
    private int usCdUser;

    @JsonProperty("us_ro_cd_role")
    private int usRoCdRole;

    @JsonProperty("us_nm_first_name")
    private String usNmFirstName;

    @JsonProperty("us_nm_last_name")
    private String usNmLastName;

    @JsonProperty("us_de_email")
    private String usDeEmail;

    @JsonProperty("us_de_phone")
    private String usDePhone;

    @JsonProperty("us_de_password_hash")
    private String usDePasswordHash;

    @JsonProperty("us_in_veterinarian")
    private String usInVeterinarian;

    @JsonProperty("us_st_user")
    private String usStUser;

    @JsonProperty("us_dt_last_login")
    private Date usDtLastLogin;

    @JsonProperty("us_dt_created")
    private Date usDtCreated;

    @JsonProperty("us_dt_updated")
    private Date usDtUpdated;
}
