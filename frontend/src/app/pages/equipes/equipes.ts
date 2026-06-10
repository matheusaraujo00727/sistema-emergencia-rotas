import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { NgClass } from '@angular/common';
import { Equipe } from '../../models/equipe';
import { EquipeService } from '../../services/equipe';

@Component({
  selector: 'app-equipes',
  imports: [NgClass],
  templateUrl: './equipes.html',
  styleUrl: './equipes.css',
})
export class Equipes implements OnInit {
  equipes: Equipe[] = [];
  carregando = false;
  erro = '';

  constructor(
    private equipeService: EquipeService,
    private cdr: ChangeDetectorRef
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
      error: () => {
        this.erro = 'Não foi possível carregar as equipes.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }
}
