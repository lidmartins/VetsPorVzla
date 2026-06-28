import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API } from '../config/api.config';
import { Animal, CreateAnimalRequest } from '../../shared/models/animal.model';

@Injectable({ providedIn: 'root' })
export class AnimalService {
  constructor(private http: HttpClient) {}

  createAnimal(payload: CreateAnimalRequest): Observable<Animal> {
    const headers = new HttpHeaders({
      'correlation-id': crypto.randomUUID(),
    });

    return this.http.post<Animal>(API.ANIMAL, payload, { headers }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(err: HttpErrorResponse): Observable<never> {
    let msg = 'Ocurrió un error. Intenta de nuevo.';
    if (err.status === 0)   msg = 'Sin conexión con el servidor.';
    if (err.status === 400) msg = 'Datos inválidos. Revisa el formulario.';
    if (err.status === 500) msg = 'Error interno. Intenta más tarde.';
    return throwError(() => new Error(msg));
  }
}
