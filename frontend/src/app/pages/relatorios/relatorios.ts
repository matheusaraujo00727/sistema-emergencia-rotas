import { Component, OnInit } from '@angular/core';
import { Relatorio } from '../../models/relatorio';
import { RelatorioService } from '../../services/relatorio';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-relatorios',
  imports: [FormsModule],
  templateUrl: './relatorios.html',
  styleUrl: './relatorios.css',
})
export class Relatorios implements OnInit {
  relatorio?: Relatorio;
  consulta = '';
  resultadoConsulta = '';
  carregando = false;
  erro = '';

  constructor(private relatorioService: RelatorioService) {}

  ngOnInit(): void {
    this.carregarRelatorio();
  }

  validarConsulta(): void {
    const regex =
      /^parametro\.[a-zA-Z]+ = "[a-zA-Z_ ]+"( AND parametro\.[a-zA-Z]+ = "[a-zA-Z_ ]+")*$/;

    if (regex.test(this.consulta)) {
      this.resultadoConsulta = 'Consulta sintaticamente válida.';
    } else {
      this.resultadoConsulta = 'Consulta inválida conforme a gramática definida.';
    }
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
