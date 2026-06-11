import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Atendimento } from '../../models/atendimento';
import { AtendimentoService } from '../../services/atendimento';

@Component({
  selector: 'app-atendimentos',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './atendimentos.html',
  styleUrl: './atendimentos.css',
})
export class Atendimentos implements OnInit {
  atendimentos: Atendimento[] = [];
  carregando = false;
  erro = '';

  constructor(
    private atendimentoService: AtendimentoService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.carregarAtendimentos();
  }

  carregarAtendimentos(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.atendimentoService.listar().subscribe({
      next: (dados) => {
        this.atendimentos = dados;
        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: (erro) => {
        console.error('Erro ao carregar atendimentos:', erro);
        this.erro = 'Não foi possível carregar os atendimentos.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }
}
