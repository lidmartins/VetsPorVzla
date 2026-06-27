package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.ContactoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .withProcedureName("sp_contacto_insert");

        Map<String, Object> inParams = Map.of(
                "p_co_nm_first_name", contacto.getCoNmFirstName(),
                "p_co_nm_last_name", contacto.getCoNmLastName(),
                "p_co_de_email", contacto.getCoDeEmail(),
                "p_co_de_phone", contacto.getCoDePhone(),
                "p_co_de_whatsapp", contacto.getCoDeWhatsapp(),
                "p_co_tp_contact_method", contacto.getCoTpContactMethod(),
                "p_co_in_allow_public", contacto.getCoInAllowPublic()
        );

        jdbcCall.execute(inParams);
        return contacto;
    }

    public ContactoDto updateContacto(ContactoDto contacto) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_update");

        Map<String, Object> inParams = Map.of(
                "p_co_cd_contacto", contacto.getCoCdContacto(),
                "p_co_nm_first_name", contacto.getCoNmFirstName(),
                "p_co_nm_last_name", contacto.getCoNmLastName(),
                "p_co_de_email", contacto.getCoDeEmail(),
                "p_co_de_phone", contacto.getCoDePhone(),
                "p_co_de_whatsapp", contacto.getCoDeWhatsapp(),
                "p_co_tp_contact_method", contacto.getCoTpContactMethod(),
                "p_co_in_allow_public", contacto.getCoInAllowPublic()
        );

        jdbcCall.execute(inParams);
        return contacto;
    }

    public void deleteContacto(int contactoId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_delete");

        Map<String, Object> inParams = Map.of("p_co_cd_contacto", contactoId);

        jdbcCall.execute(inParams);
    }

    public List<ContactoDto> searchContactos(Integer contactoId, String firstName, String lastName, String email) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_contacto_search")
                .returningResultSet("contactos", (rs, rowNum) -> {
                    ContactoDto contacto = new ContactoDto();
                    contacto.setCoCdContacto(rs.getInt("co_cd_contacto"));
                    contacto.setCoNmFirstName(rs.getString("co_nm_first_name"));
                    contacto.setCoNmLastName(rs.getString("co_nm_last_name"));
                    contacto.setCoDeEmail(rs.getString("co_de_email"));
                    contacto.setCoDePhone(rs.getString("co_de_phone"));
                    contacto.setCoDeWhatsapp(rs.getString("co_de_whatsapp"));
                    contacto.setCoTpContactMethod(rs.getString("co_tp_contact_method"));
                    contacto.setCoInAllowPublic(rs.getString("co_in_allow_public"));
                    contacto.setCoDtCreated(rs.getDate("co_dt_created"));
                    contacto.setCoDtUpdated(rs.getDate("co_dt_updated"));
                    return contacto;
                });

        Map<String, Object> inParams = Map.of(
                "p_co_cd_contacto", contactoId,
                "p_co_nm_first_name", firstName,
                "p_co_nm_last_name", lastName,
                "p_co_de_email", email
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<ContactoDto>) out.get("contactos");
    }
}
