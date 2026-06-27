package com.vetsportvzla.backend.repository;

import com.vetsportvzla.backend.dto.RefugioDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RefugioRepository {

    private final JdbcTemplate jdbcTemplate;

    public RefugioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RefugioDto createRefugio(RefugioDto refugio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_insert");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_re_cd_contacto", refugio.getReCdContacto()),
                Map.entry("p_re_ur_cd_ubicacion", refugio.getReUrCdUbicacion()),
                Map.entry("p_re_nm_refugio", refugio.getReNmRefugio()),
                Map.entry("p_re_st_refugio", refugio.getReStRefugio()),
                Map.entry("p_re_nu_capacity_total", refugio.getReNuCapacityTotal()),
                Map.entry("p_re_nu_capacity_available", refugio.getReNuCapacityAvailable()),
                Map.entry("p_re_tp_species_allowed", refugio.getReTpSpeciesAllowed()),
                Map.entry("p_re_tp_animal_special_needs", refugio.getReTpAnimalSpecialNeeds()),
                Map.entry("p_re_in_has_pets", refugio.getReInHasPets()),
                Map.entry("p_re_tp_housing", refugio.getReTpHousing()),
                Map.entry("p_re_in_fence_housing", refugio.getReInFenceHousing()),
                Map.entry("p_re_de_additional_note", refugio.getReDeAdditionalNote()),
                Map.entry("p_re_de_observacion_vet", refugio.getReDeObservacionVet())
        );

        jdbcCall.execute(inParams);
        return refugio;
    }

    public RefugioDto updateRefugio(RefugioDto refugio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_update");

        Map<String, Object> inParams = Map.ofEntries(
                Map.entry("p_re_cd_refugio", refugio.getReCdRefugio()),
                Map.entry("p_re_cd_contacto", refugio.getReCdContacto()),
                Map.entry("p_re_ur_cd_ubicacion", refugio.getReUrCdUbicacion()),
                Map.entry("p_re_nm_refugio", refugio.getReNmRefugio()),
                Map.entry("p_re_st_refugio", refugio.getReStRefugio()),
                Map.entry("p_re_nu_capacity_total", refugio.getReNuCapacityTotal()),
                Map.entry("p_re_nu_capacity_available", refugio.getReNuCapacityAvailable()),
                Map.entry("p_re_tp_species_allowed", refugio.getReTpSpeciesAllowed()),
                Map.entry("p_re_tp_animal_special_needs", refugio.getReTpAnimalSpecialNeeds()),
                Map.entry("p_re_in_has_pets", refugio.getReInHasPets()),
                Map.entry("p_re_tp_housing", refugio.getReTpHousing()),
                Map.entry("p_re_in_fence_housing", refugio.getReInFenceHousing()),
                Map.entry("p_re_de_additional_note", refugio.getReDeAdditionalNote()),
                Map.entry("p_re_de_observacion_vet", refugio.getReDeObservacionVet())
        );

        jdbcCall.execute(inParams);
        return refugio;
    }

    public void deleteRefugio(int refugioId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_delete");

        Map<String, Object> inParams = Map.of("p_re_cd_refugio", refugioId);

        jdbcCall.execute(inParams);
    }

    public List<RefugioDto> searchRefugios(Integer refugioId, Integer ubicacionId, String status, String speciesAllowed) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_refugio_search")
                .returningResultSet("refugios", (rs, rowNum) -> {
                    RefugioDto refugio = new RefugioDto();
                    refugio.setReCdRefugio(rs.getInt("re_cd_refugio"));
                    refugio.setReCdContacto(rs.getInt("re_cd_contacto"));
                    refugio.setReUrCdUbicacion(rs.getInt("re_ur_cd_ubicacion"));
                    refugio.setReNmRefugio(rs.getString("re_nm_refugio"));
                    refugio.setReStRefugio(rs.getString("re_st_refugio"));
                    refugio.setReNuCapacityTotal(rs.getInt("re_nu_capacity_total"));
                    refugio.setReNuCapacityAvailable(rs.getInt("re_nu_capacity_available"));
                    refugio.setReTpSpeciesAllowed(rs.getString("re_tp_species_allowed"));
                    refugio.setReTpAnimalSpecialNeeds(rs.getString("re_tp_animal_special_needs"));
                    refugio.setReInHasPets(rs.getString("re_in_has_pets"));
                    refugio.setReTpHousing(rs.getString("re_tp_housing"));
                    refugio.setReInFenceHousing(rs.getString("re_in_fence_housing"));
                    refugio.setReDeAdditionalNote(rs.getString("re_de_additional_note"));
                    refugio.setReDeObservacionVet(rs.getString("re_de_observacion_vet"));
                    refugio.setReDtCreated(rs.getDate("re_dt_created"));
                    refugio.setReDtUpdated(rs.getDate("re_dt_updated"));
                    return refugio;
                });

        Map<String, Object> inParams = Map.of(
                "p_re_cd_refugio", refugioId,
                "p_re_ur_cd_ubicacion", ubicacionId,
                "p_re_st_refugio", status,
                "p_re_tp_species_allowed", speciesAllowed
        );

        Map<String, Object> out = jdbcCall.execute(inParams);
        return (List<RefugioDto>) out.get("refugios");
    }
}
