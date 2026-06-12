import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OcorrenciaService } from '../../services/ocorrencia';

@Component({
  selector: 'app-ocorrencia-form',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './ocorrencia-form.html',
  styleUrl: './ocorrencia-form.css',
})
export class OcorrenciaForm implements OnInit {
  titulo = '';
  tipo: string = 'ACIDENTE';
  status: 'ABERTA' | 'EM_ATENDIMENTO' | 'FINALIZADA' | 'CANCELADA' = 'ABERTA';
  prioridade: 'BAIXA' | 'MEDIA' | 'ALTA' | 'CRITICA' = 'BAIXA';
  localizacao = '';
  descricao = '';

  modoEdicao = false;
  idOcorrencia?: number;

  salvando = false;
  erro = '';

  constructor(
    private ocorrenciaService: OcorrenciaService,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicao = true;
      this.idOcorrencia = Number(id);

      this.ocorrenciaService.buscarPorId(this.idOcorrencia).subscribe({
        next: (ocorrencia) => {
          this.titulo = ocorrencia.titulo;
          this.tipo = ocorrencia.tipo;
          this.prioridade = ocorrencia.prioridade;
          this.status = ocorrencia.status || 'ABERTA';
          this.localizacao = ocorrencia.localizacao;
          this.descricao = ocorrencia.descricao || '';
          this.cdr.detectChanges();
        },
        error: () => {
          this.erro = 'Não foi possível carregar a ocorrência.';
          this.cdr.detectChanges();
        },
      });
    }
  }

  salvar(): void {
    this.salvando = true;
    this.erro = '';

    const ocorrencia = {
      titulo: this.titulo,
      tipo: this.tipo,
      prioridade: this.prioridade,
      status: this.status,
      localizacao: this.localizacao,
      descricao: this.descricao,
    };

    const requisicao = this.modoEdicao
      ? this.ocorrenciaService.atualizar(this.idOcorrencia!, ocorrencia)
      : this.ocorrenciaService.salvar(ocorrencia);

    requisicao.subscribe({
      next: () => {
        this.salvando = false;
        this.router.navigate(['/ocorrencias']);
        this.cdr.detectChanges();
      },
      error: () => {
        this.salvando = false;
        this.erro = this.modoEdicao
          ? 'Não foi possível atualizar a ocorrência.'
          : 'Não foi possível salvar a ocorrência.';
        this.cdr.detectChanges();
      },
    });
  }
}
