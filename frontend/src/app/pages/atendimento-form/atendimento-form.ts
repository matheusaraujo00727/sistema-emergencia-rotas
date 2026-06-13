import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AtendimentoService } from '../../services/atendimento';
import { OcorrenciaService } from '../../services/ocorrencia';
import { EquipeService } from '../../services/equipe';
import { Ocorrencia } from '../../models/ocorrencia';
import { Equipe } from '../../models/equipe';
import { RecursoService } from '../../services/recurso';
import { Recurso } from '../../models/recurso';

@Component({
  selector: 'app-atendimento-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './atendimento-form.html',
  styleUrl: './atendimento-form.css',
})
export class AtendimentoForm implements OnInit {
  ocorrencias: Ocorrencia[] = [];
  equipes: Equipe[] = [];
  recursos: Recurso[] = [];

  recursoId?: number;
  ocorrenciaId?: number;
  equipeId?: number;
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO' = 'PENDENTE';
  observacoes = '';

  salvando = false;
  carregando = false;
  erro = '';

  constructor(
    private atendimentoService: AtendimentoService,
    private ocorrenciaService: OcorrenciaService,
    private equipeService: EquipeService,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private recursoService: RecursoService,
  ) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.ocorrenciaService.listar().subscribe({
      next: (ocorrencias) => {
        this.ocorrencias = ocorrencias.filter((item) => item.status === 'ABERTA');

        this.equipeService.listar().subscribe({
          next: (equipes) => {
            this.equipes = equipes.filter((item) => item.status === 'DISPONIVEL');
            this.carregando = false;
            this.cdr.detectChanges();
          },
          error: () => {
            this.erro = 'Não foi possível carregar as equipes.';
            this.carregando = false;
            this.cdr.detectChanges();
          },
        });
      },
      error: () => {
        this.erro = 'Não foi possível carregar as ocorrências.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
    this.recursoService.listar().subscribe({
      next: (recursos) => {
        this.recursos = recursos.filter((item) => item.status === 'DISPONIVEL');

        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.erro = 'Não foi possível carregar os recursos.';
        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }

  salvar(): void {
    this.erro = '';

    if (!this.ocorrenciaId || !this.equipeId || !this.recursoId) {
      this.erro = 'Selecione uma ocorrência, uma equipe e um recurso.';
      return;
    }

    this.salvando = true;

    this.atendimentoService
      .salvar({
        ocorrenciaId: Number(this.ocorrenciaId),
        equipeId: Number(this.equipeId),
        recursoId: Number(this.recursoId),
        observacoes: this.observacoes,
      })
      .subscribe({
        next: () => {
          this.salvando = false;
          this.router.navigate(['/atendimentos']);
          this.cdr.detectChanges();
        },
        error: (erro) => {
          console.error('Erro ao salvar atendimento:', erro);
          this.erro =
            erro?.error?.message || erro?.error?.erro || 'Não foi possível salvar o atendimento.';
          this.salvando = false;
          this.cdr.detectChanges();
        },
      });
  }
}
