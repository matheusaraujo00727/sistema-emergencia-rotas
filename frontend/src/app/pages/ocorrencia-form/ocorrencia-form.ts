import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { OcorrenciaService } from '../../services/ocorrencia';

@Component({
  selector: 'app-ocorrencia-form',
  imports: [RouterLink, FormsModule],
  templateUrl: './ocorrencia-form.html',
  styleUrl: './ocorrencia-form.css',
})
export class OcorrenciaForm {
  titulo = '';
  tipo = 'ACIDENTE_TRANSITO';
  prioridade: 'BAIXA' | 'MEDIA' | 'ALTA' | 'CRITICA' = 'BAIXA';
  localizacao = '';
  descricao = '';

  salvando = false;
  erro = '';

  constructor(
    private ocorrenciaService: OcorrenciaService,
    private router: Router,
  ) {}

  salvar(): void {
    this.salvando = true;
    this.erro = '';

    const novaOcorrencia = {
      titulo: this.titulo,
      tipo: this.tipo,
      prioridade: this.prioridade,
      localizacao: this.localizacao,
      descricao: this.descricao,
    };

    this.ocorrenciaService.salvar(novaOcorrencia).subscribe({
      next: () => {
        this.salvando = false;
        this.router.navigate(['/ocorrencias']);
      },
      error: () => {
        this.salvando = false;
        this.erro = 'Não foi possível salvar a ocorrência.';
      },
    });
  }
}
