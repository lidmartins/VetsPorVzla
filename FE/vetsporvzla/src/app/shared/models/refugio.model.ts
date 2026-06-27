// refugio.model.ts
export interface Refugio {
  re_cd_refugio:              number;
  re_cd_contacto:             number;
  re_ur_cd_ubicacion:         number;
  re_nm_refugio:              string;
  re_st_refugio:              'P' | 'A' | 'X' | 'I' | 'R';
  re_nu_capacity_total:       number;
  re_nu_capacity_available:   number;
  re_tp_species_allowed:      'G' | 'P' | 'A';
  re_tp_animal_special_needs?: 'AH' | 'CA' | 'AM';
  re_in_has_pets:             'S' | 'N';
  re_tp_housing:              'CP' | 'CS' | 'AP';
  re_in_fence_housing:        'C' | 'P' | 'N';
  re_de_additional_note?:     string;
  re_de_observacion_vet?:     string;
  re_dt_created:              string;
  re_dt_updated:              string;
}
