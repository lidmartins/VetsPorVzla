import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitud } from '../../shared/models/solicitud.model';
import { Page } from '../../shared/models/pagination.model';
import { environment } from '../../../environments/environment';

export interface SolicitudFilter {
  tp?: 'P' | 'E' | '';
  st?: 'P' | 'R' | 'A' | '';
  page?: number;
  size?: number;
}

@Injectable({ providedIn: 'root' })
export class SolicitudService {
  private base = `${environment.apiUrl}/solicitudes`;

  constructor(private http: HttpClient) {}

  getAll(filter: SolicitudFilter = {}): Observable<Page<Solicitud>> {
    let params = new HttpParams()
      .set('page',  (filter.page ?? 0).toString())
      .set('size',  (filter.size ?? 5).toString());
    if (filter.tp) params = params.set('tp', filter.tp);
    if (filter.st) params = params.set('st', filter.st);
    return this.http.get<Page<Solicitud>>(this.base, { params });
  }

  getById(id: number): Observable<Solicitud> {
    return this.http.get<Solicitud>(`${this.base}/${id}`);
  }

  updateStatus(id: number, st: string, obs: string): Observable<Solicitud> {
    return this.http.patch<Solicitud>(`${this.base}/${id}/status`, { so_st_solicitud: st, so_de_observacion_vet: obs });
  }
}
