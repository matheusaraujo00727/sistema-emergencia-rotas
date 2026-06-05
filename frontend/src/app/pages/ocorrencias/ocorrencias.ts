import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Ocorrencia } from '../../models/ocorrencia';
import { OcorrenciaService } from '../../services/ocorrencia';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-ocorrencias',
  imports: [NgClass, RouterLink],
  templateUrl: './ocorrencias.html',
  styleUrl: './ocorrencias.css',
})
export class Ocorrencias implements OnInit {
  listaOcorrencias: Ocorrencia[] = [];
  carregando = false;
  erro = '';

  constructor(private ocorrenciaService: OcorrenciaService) {}

  ngOnInit(): void {
    this.carregarOcorrencias();
  }

  carregarOcorrencias(): void {
    this.carregando = true;

    this.ocorrenciaService.listar().subscribe({
      next: (dados) => {
        console.log('DADOS RECEBIDOS DO BACK:', dados);
        this.listaOcorrencias = dados;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('ERRO AO BUSCAR OCORRÊNCIAS:', erro);
        this.erro = 'Não foi possível carregar as ocorrências.';
        this.carregando = false;
      },
    });
  }
}
