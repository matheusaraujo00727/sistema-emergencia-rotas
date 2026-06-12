import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Ocorrencia } from '../../models/ocorrencia';
import { OcorrenciaService } from '../../services/ocorrencia';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-ocorrencias',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './ocorrencias.html',
  styleUrl: './ocorrencias.css',
})
export class Ocorrencias implements OnInit {
  listaOcorrencias: Ocorrencia[] = [];
  carregando = false;
  erro = '';

  constructor(
    private ocorrenciaService: OcorrenciaService,
    private cdr: ChangeDetectorRef,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.carregarOcorrencias();
  }

  excluir(id?: number): void {
    if (!id) return;

    const confirmar = confirm('Deseja realmente excluir esta ocorrência?');

    if (!confirmar) return;

    this.ocorrenciaService.excluir(id).subscribe({
      next: () => {
        this.carregarOcorrencias();
      },
      error: (erro) => {
        console.error('Erro ao excluir ocorrência:', erro);
        this.erro =
          erro?.error?.message || erro?.error?.erro || 'Não foi possível excluir a ocorrência.';
        this.cdr.detectChanges();
      },
    });
  }

  carregarOcorrencias(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.ocorrenciaService.listar().subscribe({
      next: (dados) => {
        this.listaOcorrencias = dados;
        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: (erro) => {
        console.error('Erro ao carregar ocorrências:', erro);
        this.erro = 'Não foi possível carregar as ocorrências.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }
}
