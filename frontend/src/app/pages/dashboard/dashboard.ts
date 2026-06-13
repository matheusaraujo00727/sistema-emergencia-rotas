import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OcorrenciaService } from '../../services/ocorrencia';
import { RecursoService } from '../../services/recurso';
import { EquipeService } from '../../services/equipe';
import { AtendimentoService } from '../../services/atendimento';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  totalOcorrencias = 0;
  ocorrenciasAbertas = 0;
  recursosDisponiveis = 0;
  equipesAtivas = 0;
  atendimentosAndamento = 0;
  tempoMedioResposta = 0;

  alertasPrioritarios: any[] = [];
  ultimasOcorrencias: any[] = [];

  carregando = false;
  erro = '';

  constructor(
    private ocorrenciaService: OcorrenciaService,
    private recursoService: RecursoService,
    private equipeService: EquipeService,
    private cdr: ChangeDetectorRef,
    private atendimentoService: AtendimentoService,
  ) {}

  ngOnInit(): void {
    this.carregarDashboard();
  }

  calcularTempoMedio(atendimentos: any[]): void {
    const concluidos = atendimentos.filter(
      (item) => item.status === 'CONCLUIDO' && item.dataInicio && item.dataFim,
    );

    if (concluidos.length === 0) {
      this.tempoMedioResposta = 0;
      return;
    }

    const totalMinutos = concluidos.reduce((total, item) => {
      const inicio = new Date(item.dataInicio).getTime();
      const fim = new Date(item.dataFim).getTime();

      const diferencaMinutos = Math.max(0, (fim - inicio) / 60000);

      return total + diferencaMinutos;
    }, 0);

    this.tempoMedioResposta = Math.round(totalMinutos / concluidos.length);
  }

  carregarDashboard(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.ocorrenciaService.listar().subscribe({
      next: (ocorrencias) => {
        this.totalOcorrencias = ocorrencias.length;
        this.ocorrenciasAbertas = ocorrencias.filter((item) => item.status === 'ABERTA').length;

        this.alertasPrioritarios = ocorrencias
          .filter(
            (item) =>
              item.prioridade === 'CRITICA' ||
              item.prioridade === 'ALTA' ||
              item.prioridade === 'MEDIA' ||
              item.prioridade === 'BAIXA',
          )
          .slice(0, 5);

        this.recursoService.listar().subscribe({
          next: (recursos) => {
            this.totalOcorrencias = ocorrencias.length;

            this.ocorrenciasAbertas = ocorrencias.filter((item) => item.status === 'ABERTA').length;

            this.recursosDisponiveis = recursos.filter(
              (item) => item.status === 'DISPONIVEL',
            ).length;

            this.atendimentosAndamento = ocorrencias.filter(
              (item) => item.status === 'EM_ATENDIMENTO',
            ).length;

            this.ultimasOcorrencias = [...ocorrencias]
              .sort((a: any, b: any) => (b.id ?? 0) - (a.id ?? 0))
              .slice(0, 5);

            this.equipeService.listar().subscribe({
              next: (equipes) => {
                this.equipesAtivas = equipes.filter(
                  (item: any) => item.ativa === true || item.status === 'DISPONIVEL',
                ).length;

                this.atendimentoService.listar().subscribe({
                  next: (atendimentos) => {
                    this.calcularTempoMedio(atendimentos);

                    this.carregando = false;
                    this.cdr.detectChanges();
                  },
                  error: () => {
                    this.tempoMedioResposta = 0;

                    this.carregando = false;
                    this.cdr.detectChanges();
                  },
                });
              },

              error: () => {
                this.carregando = false;
                this.cdr.detectChanges();
              },
            });
          },
          error: () => {
            this.carregando = false;
            this.cdr.detectChanges();
          },
        });
      },
      error: () => {
        this.erro = 'Não foi possível carregar os dados do dashboard.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }
}
