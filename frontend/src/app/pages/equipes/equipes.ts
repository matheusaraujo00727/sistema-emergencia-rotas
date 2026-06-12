import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Equipe } from '../../models/equipe';
import { EquipeService } from '../../services/equipe';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-equipes',
  standalone: true,
  imports: [NgClass, RouterLink, CommonModule],
  templateUrl: './equipes.html',
  styleUrl: './equipes.css',
})
export class Equipes implements OnInit {
  equipes: Equipe[] = [];
  carregando = false;
  erro = '';

  constructor(
    private equipeService: EquipeService,
    private cdr: ChangeDetectorRef,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.carregarEquipes();
  }

  carregarEquipes(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.equipeService.listar().subscribe({
      next: (dados) => {
        this.equipes = dados;
        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: (erro) => {
        console.error('Erro ao carregar equipes:', erro);

        this.erro =
          erro?.error?.message || erro?.error?.erro || 'Não foi possível carregar as equipes.';

        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }

  excluir(id?: number): void {
    if (!id) return;

    const confirmar = confirm('Deseja realmente excluir esta equipe?');

    if (!confirmar) return;

    this.equipeService.excluir(id).subscribe({
      next: () => {
        this.carregarEquipes();
      },
      error: (erro) => {
        console.error('Erro ao excluir equipe:', erro);

        this.erro =
          erro?.error?.message || erro?.error?.erro || 'Não foi possível excluir a equipe.';

        this.cdr.detectChanges();
      },
    });
  }
}
