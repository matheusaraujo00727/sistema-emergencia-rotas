import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recurso } from '../models/recurso';

@Injectable({
  providedIn: 'root',
})
export class RecursoService {
  private readonly apiUrl = 'http://localhost:8080/recursos';

  constructor(private http: HttpClient) {}

  listar(): Observable<Recurso[]> {
    return this.http.get<Recurso[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<Recurso> {
    return this.http.get<Recurso>(`${this.apiUrl}/${id}`);
  }

  salvar(recurso: Recurso): Observable<Recurso> {
    return this.http.post<Recurso>(this.apiUrl, recurso);
  }

  atualizar(id: number, recurso: Recurso): Observable<Recurso> {
    return this.http.put<Recurso>(`${this.apiUrl}/${id}`, recurso);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
