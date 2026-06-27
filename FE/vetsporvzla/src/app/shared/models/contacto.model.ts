export interface Contacto {
  co_cd_contacto:       number;
  co_nm_first_name:     string;
  co_nm_last_name:      string;
  co_de_email:          string;
  co_de_phone:          string;
  co_de_whatsapp?:      string;
  co_tp_contact_method: 'E' | 'T' | 'W';
  co_in_allow_public:   'S' | 'N';
  co_dt_created:        string;
  co_dt_updated:        string;
}
