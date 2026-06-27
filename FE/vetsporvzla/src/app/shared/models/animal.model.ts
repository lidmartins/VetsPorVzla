export interface CreateAnimalRequest {
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
}

export interface Animal {
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
}
