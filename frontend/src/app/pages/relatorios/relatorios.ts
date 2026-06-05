import { Component, OnInit } from '@angular/core';
import { Relatorio } from '../../models/relatorio';
import { RelatorioService } from '../../services/relatorio';

@Component({
  selector: 'app-relatorios',
  imports: [],
  templateUrl: './relatorios.html',
  styleUrl: './relatorios.css',
})
export class Relatorios implements OnInit {
  relatorio?: Relatorio;
  carregando = false;
  erro = '';

  constructor(private relatorioService: RelatorioService) {}

  ngOnInit(): void {
    this.carregarRelatorio();
  }

  carregarRelatorio(): void {
    this.carregando = true;
    this.erro = '';

    this.relatorioService.buscarResumo().subscribe({
      next: (dados) => {
        this.relatorio = dados;
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Não foi possível carregar os relatórios.';
        this.carregando = false;
      },
    });
  }
}
