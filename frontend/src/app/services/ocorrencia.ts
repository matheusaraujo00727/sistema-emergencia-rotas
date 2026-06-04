import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ocorrencia } from '../models/ocorrencia';

@Injectable({
  providedIn: 'root',
})
export class OcorrenciaService {
  private readonly apiUrl = 'http://localhost:8080/ocorrencias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Ocorrencia[]> {
    return this.http.get<Ocorrencia[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<Ocorrencia> {
    return this.http.get<Ocorrencia>(`${this.apiUrl}/${id}`);
  }

  salvar(ocorrencia: Ocorrencia): Observable<Ocorrencia> {
    return this.http.post<Ocorrencia>(this.apiUrl, ocorrencia);
  }

  atualizar(id: number, ocorrencia: Ocorrencia): Observable<Ocorrencia> {
    return this.http.put<Ocorrencia>(`${this.apiUrl}/${id}`, ocorrencia);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
