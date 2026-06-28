export interface CreateAnimalRequest {
  an_report_type:           'P' | 'E';
  an_tp_animal:             'G' | 'P';
  an_nm_animal?:            string;
  an_de_breed?:             string;
  an_de_color:              string;
  an_tp_size:               'P' | 'M' | 'G';
  an_tp_sex:                'M' | 'H';
  an_nu_approx_age?:        number;
  an_de_animal:             string;
  an_in_require_vet_review: 'S' | 'N';
  an_re_cd_refugio?:        number;
  an_ubicacion:             string;
  an_telefono:              string;
}

export interface Animal {
  an_report_type:           'P' | 'E';
  an_cd_animal:             number;
  an_re_cd_refugio?:        number;
  an_nm_animal?:            string;
  an_tp_animal:             'G' | 'P';
  an_de_breed?:             string;
  an_de_color:              string;
  an_tp_size:               'P' | 'M' | 'G';
  an_tp_sex:                'M' | 'H';
  an_nu_approx_age?:        number;
  an_de_animal:             string;
  an_in_require_vet_review: 'S' | 'N';
  an_st_vet_review:         'P' | 'A' | 'R';
  an_dt_created:            string;
  an_dt_updated:            string;
  an_ubicacion:             string;
  an_telefono:              string;
}
