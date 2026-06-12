import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { RecursoService } from '../../services/recurso';

@Component({
  selector: 'app-recurso-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './recurso-form.html',
  styleUrl: './recurso-form.css',
})
export class RecursoForm implements OnInit {
  nome = '';
  tipo: 'AMBULANCIA' | 'VIATURA' | 'HELICOPTERO' | 'EQUIPAMENTO_MEDICO' | 'OUTRO' = 'AMBULANCIA';
  placa = '';
  status: 'DISPONIVEL' | 'INDISPONIVEL' = 'DISPONIVEL';

  modoEdicao = false;
  idRecurso?: number;

  salvando = false;
  erro = '';

  constructor(
    private recursoService: RecursoService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicao = true;
      this.idRecurso = Number(id);

      this.recursoService.buscarPorId(this.idRecurso).subscribe({
        next: (recurso) => {
          this.nome = recurso.nome;
          this.tipo = recurso.tipo;
          this.placa = recurso.placa || '';
          this.status = recurso.status;
          this.cdr.detectChanges();
        },
        error: () => {
          this.erro = 'Não foi possível carregar o recurso.';
          this.cdr.detectChanges();
        },
      });
    }
  }

  salvar(): void {
    this.erro = '';
    this.cdr.detectChanges();

    if (!this.nome.trim()) {
      this.erro = 'Informe o nome do recurso.';
      this.cdr.detectChanges();
      return;
    }

    this.salvando = true;

    const recurso = {
      nome: this.nome,
      tipo: this.tipo,
      status: this.status,
      placa: this.placa,
    };

    const requisicao = this.modoEdicao
      ? this.recursoService.atualizar(this.idRecurso!, recurso)
      : this.recursoService.salvar(recurso);

    requisicao.subscribe({
      next: () => {
        this.salvando = false;
        this.router.navigate(['/recursos']);
        this.cdr.detectChanges();
      },
      error: (erro) => {
        console.error('Erro ao salvar recurso:', erro);

        this.salvando = false;
        this.erro =
          erro?.error?.message ||
          erro?.error?.erro ||
          (this.modoEdicao
            ? 'Não foi possível atualizar o recurso.'
            : 'Não foi possível cadastrar o recurso.');

        this.cdr.detectChanges();
      },
    });
  }
}
