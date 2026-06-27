export interface Ubicacion {
  ur_cd_ubicacion:    number;
  ur_es_cd_estado:    number;
  ur_nm_city:         string;
  ur_nm_sector?:      string;
  ur_de_address?:     string;
  ur_de_reference?:   string;
  ur_de_postal_code?: string;
  ur_nu_latitude?:    number;
  ur_nu_longitude?:   number;
  ur_dt_created:      string;
  ur_dt_updated:      string;
}
