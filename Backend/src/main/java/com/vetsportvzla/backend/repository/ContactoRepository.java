package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.ContactoDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ContactoDto createContacto(ContactoDto contacto) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_insert")
                .returningResultSet("contacto", BeanPropertyRowMapper.newInstance(ContactoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_co_nm_first_name", contacto.getCoNmFirstName());
        inParams.put("p_co_nm_last_name", contacto.getCoNmLastName());
        inParams.put("p_co_de_email", contacto.getCoDeEmail());
        inParams.put("p_co_de_phone", contacto.getCoDePhone());
        inParams.put("p_co_de_whatsapp", contacto.getCoDeWhatsapp());
        inParams.put("p_co_tp_contact_method", contacto.getCoTpContactMethod());
        inParams.put("p_co_in_allow_public", contacto.getCoInAllowPublic());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<ContactoDto> contactos = (List<ContactoDto>) out.get("contacto");
        return contactos.get(0);
    }

    public ContactoDto updateContacto(ContactoDto contacto) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_update")
                .returningResultSet("contacto", BeanPropertyRowMapper.newInstance(ContactoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_co_cd_contacto", contacto.getCoCdContacto());
        inParams.put("p_co_nm_first_name", contacto.getCoNmFirstName());
        inParams.put("p_co_nm_last_name", contacto.getCoNmLastName());
        inParams.put("p_co_de_email", contacto.getCoDeEmail());
        inParams.put("p_co_de_phone", contacto.getCoDePhone());
        inParams.put("p_co_de_whatsapp", contacto.getCoDeWhatsapp());
        inParams.put("p_co_tp_contact_method", contacto.getCoTpContactMethod());
        inParams.put("p_co_in_allow_public", contacto.getCoInAllowPublic());

        Map<String, Object> out = jdbcCall.execute(inParams);
        List<ContactoDto> contactos = (List<ContactoDto>) out.get("contacto");
        return contactos.get(0);
    }

    public void deleteContacto(int contactoId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_delete");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_co_cd_contacto", contactoId);

        jdbcCall.execute(inParams);
    }

    public List<ContactoDto> searchContactos(Integer contactoId, String firstName, String lastName, String email) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_search")
                .returningResultSet("contactos", BeanPropertyRowMapper.newInstance(ContactoDto.class));

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_co_cd_contacto", contactoId);
        inParams.put("p_co_nm_first_name", firstName);
        inParams.put("p_co_nm_last_name", lastName);
        inParams.put("p_co_de_email", email);

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<ContactoDto>) out.get("contactos");
    }
}
