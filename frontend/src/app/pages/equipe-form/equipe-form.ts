import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EquipeService } from '../../services/equipe';

@Component({
  selector: 'app-equipe-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './equipe-form.html',
  styleUrl: './equipe-form.css',
})
export class EquipeForm implements OnInit {
  nome = '';
  especialidade = '';
  status: 'DISPONIVEL' | 'EM_ATENDIMENTO' | 'INDISPONIVEL' = 'DISPONIVEL';
  quantidadeMembros = 1;

  modoEdicao = false;
  idEquipe?: number;

  salvando = false;
  erro = '';

  constructor(
    private equipeService: EquipeService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicao = true;
      this.idEquipe = Number(id);

      this.equipeService.buscarPorId(this.idEquipe).subscribe({
        next: (equipe) => {
          this.nome = equipe.nome;
          this.especialidade = equipe.especialidade;
          this.status = equipe.status;
          this.quantidadeMembros = equipe.quantidadeMembros;
        },
        error: () => {
          this.erro = 'Não foi possível carregar a equipe.';
        },
      });
    }
  }

  salvar(): void {
    this.erro = '';

    if (!this.nome.trim() || !this.especialidade.trim()) {
      this.erro = 'Preencha nome e especialidade.';
      return;
    }

    this.salvando = true;

    const equipe = {
      nome: this.nome,
      especialidade: this.especialidade,
      status: this.status,
      quantidadeMembros: this.quantidadeMembros,
    };

    const requisicao = this.modoEdicao
      ? this.equipeService.atualizar(this.idEquipe!, equipe)
      : this.equipeService.salvar(equipe);

    requisicao.subscribe({
      next: () => {
        this.salvando = false;
        this.router.navigate(['/equipes']);
      },
      error: () => {
        this.salvando = false;
        this.erro = this.modoEdicao
          ? 'Não foi possível atualizar a equipe.'
          : 'Não foi possível cadastrar a equipe.';
      },
    });
  }
}
