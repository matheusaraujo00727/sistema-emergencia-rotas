import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EquipeService } from '../../services/equipe';

@Component({
  selector: 'app-equipe-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './equipe-form.html',
  styleUrl: './equipe-form.css',
})
export class EquipeForm {
  nome = '';
  especialidade = '';
  status: 'DISPONIVEL' | 'EM_ATENDIMENTO' | 'INDISPONIVEL' = 'DISPONIVEL';
  quantidadeMembros = 1;

  salvando = false;
  erro = '';

  constructor(
    private equipeService: EquipeService,
    private router: Router,
  ) {}

  salvar(): void {
    this.erro = '';

    if (!this.nome.trim() || !this.especialidade.trim()) {
      this.erro = 'Preencha nome e especialidade.';
      return;
    }

    this.salvando = true;

    this.equipeService
      .salvar({
        nome: this.nome,
        especialidade: this.especialidade,
        status: this.status,
        quantidadeMembros: this.quantidadeMembros,
      })
      .subscribe({
        next: () => {
          this.salvando = false;
          this.router.navigate(['/equipes']);
        },
        error: () => {
          this.salvando = false;
          this.erro = 'Não foi possível cadastrar a equipe.';
        },
      });
  }
}
