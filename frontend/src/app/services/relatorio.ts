import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Relatorio } from '../models/relatorio';

@Injectable({
  providedIn: 'root',
})
export class RelatorioService {
  private readonly apiUrl = 'http://localhost:8080/relatorios';

  constructor(private http: HttpClient) {}

  buscarResumo(): Observable<Relatorio> {
    return this.http.get<Relatorio>(`${this.apiUrl}/resumo`);
  }
}
