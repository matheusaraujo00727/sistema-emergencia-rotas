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

  constructor(private recursoService: RecursoService) {}

  ngOnInit(): void {
    this.carregarRecursos();
  }

  carregarRecursos(): void {
    this.recursoService.listar().subscribe({
      next: (dados) => {
        this.recursos = dados;
      },
      error: (erro) => {
        console.error(erro);
      },
    });
  }
}
