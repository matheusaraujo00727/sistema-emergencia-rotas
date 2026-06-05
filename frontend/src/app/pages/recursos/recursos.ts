import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { RecursoService } from '../../services/recurso';
import { Recurso } from '../../models/recurso';

@Component({
  selector: 'app-recursos',
  imports: [NgClass],
  templateUrl: './recursos.html',
  styleUrl: './recursos.css',
})
export class Recursos {
  recursos: Recurso[] = [];
  carregando = false;
  erro = '';

  constructor(private recursoService: RecursoService) {}

  ngOnInit(): void {
    this.carregarRecursos();
  }

  carregarRecursos(): void {
    this.carregando = true;
    this.erro = '';

    this.recursoService.listar().subscribe({
      next: (dados) => {
        this.recursos = dados;
        this.carregando = false;
      },
      error: (erro) => {
        console.error(erro);
      },
    });
  }
}
