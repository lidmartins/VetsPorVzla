package com.vetsportvzla.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoDto {
    @JsonProperty("co_cd_contacto")
    private int coCdContacto;

    @JsonProperty("co_nm_first_name")
    private String coNmFirstName;

    @JsonProperty("co_nm_last_name")
    private String coNmLastName;

    @JsonProperty("co_de_email")
    private String coDeEmail;

    @JsonProperty("co_de_phone")
    private String coDePhone;

    @JsonProperty("co_de_whatsapp")
    private String coDeWhatsapp;

    @JsonProperty("co_tp_contact_method")
    private String coTpContactMethod;

    @JsonProperty("co_in_allow_public")
    private String coInAllowPublic;

    @JsonProperty("co_dt_created")
    private Date coDtCreated;

    @JsonProperty("co_dt_updated")
    private Date coDtUpdated;
}
