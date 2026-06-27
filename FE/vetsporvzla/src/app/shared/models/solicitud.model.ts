import { Animal } from './animal.model';
import { Contacto } from './contacto.model';
import { Ubicacion } from './ubicacion.model';

export interface Solicitud {
  so_cd_solicitud:        number;
  so_an_cd_animal:        number;
  so_co_cd_contacto:      number;
  so_ur_cd_ubicacion:     number;
  so_tp_solicitud:        'P' | 'E';
  so_dt_evento:           string;
  so_st_solicitud:        'P' | 'R' | 'A';
  so_de_observacion_vet?: string;
  so_de_s3_folder_path:   string;
  so_de_main_photo_url:   string;
  so_dt_created:          string;
  so_dt_updated:          string;
  contacto?:              Contacto;
  animal?:                Animal;
  ubicacion?:             Ubicacion;
}
