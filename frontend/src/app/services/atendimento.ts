import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Atendimento, AtendimentoRequest } from '../models/atendimento';

@Injectable({
  providedIn: 'root',
})
export class AtendimentoService {
  private readonly apiUrl = 'http://localhost:8080/atendimentos';

  constructor(private http: HttpClient) {}

  listar(): Observable<Atendimento[]> {
    return this.http.get<Atendimento[]>(this.apiUrl);
  }

  salvar(atendimento: AtendimentoRequest): Observable<Atendimento> {
    return this.http.post<Atendimento>(this.apiUrl, atendimento);
  }

  buscarPorId(id: number): Observable<Atendimento> {
    return this.http.get<Atendimento>(`${this.apiUrl}/${id}`);
  }
}
