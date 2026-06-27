// user.model.ts
export interface User {
  us_cd_user:          number;
  us_ro_cd_role:       number;
  us_nm_first_name:    string;
  us_nm_last_name:     string;
  us_de_email:         string;
  us_de_phone:         string;
  us_in_veterinarian:  'S' | 'N';
  us_st_user:          'A' | 'I' | 'B';
}
