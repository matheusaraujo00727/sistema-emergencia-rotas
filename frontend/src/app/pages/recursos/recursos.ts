import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RecursoService } from '../../services/recurso';
import { Recurso } from '../../models/recurso';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-recursos',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './recursos.html',
  styleUrl: './recursos.css',
})
export class Recursos implements OnInit {
  recursos: Recurso[] = [];
  carregando = false;
  erro = '';

  constructor(
    private recursoService: RecursoService,
    private cdr: ChangeDetectorRef,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.carregarRecursos();
  }

  carregarRecursos(): void {
    this.carregando = true;
    this.erro = '';
    this.cdr.detectChanges();

    this.recursoService.listar().subscribe({
      next: (dados) => {
        this.recursos = dados;
        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: (erro) => {
        console.error('Erro ao carregar recursos:', erro);

        this.erro =
          erro?.error?.message || erro?.error?.erro || 'Não foi possível carregar os recursos.';

        this.carregando = false;
        this.cdr.detectChanges();
      },
    });
  }

  excluir(id?: number): void {
    if (!id) return;

    const confirmar = confirm('Deseja realmente excluir este recurso?');

    if (!confirmar) return;

    this.recursoService.excluir(id).subscribe({
      next: () => {
        this.carregarRecursos();
      },
      error: (erro) => {
        console.error('Erro ao excluir recurso:', erro);

        this.erro =
          erro?.error?.message || erro?.error?.erro || 'Não foi possível excluir o recurso.';

        this.cdr.detectChanges();
      },
    });
  }
}
