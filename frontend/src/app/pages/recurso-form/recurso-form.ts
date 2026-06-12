import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { RecursoService } from '../../services/recurso';

@Component({
  selector: 'app-recurso-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './recurso-form.html',
  styleUrl: './recurso-form.css',
})
export class RecursoForm {
  nome = '';
  tipo: 'AMBULANCIA' | 'VIATURA' | 'HELICOPTERO' | 'EQUIPAMENTO_MEDICO' | 'OUTRO' = 'AMBULANCIA';
  placa = '';
  status: 'DISPONIVEL' | 'INDISPONIVEL' = 'DISPONIVEL';

  salvando = false;
  erro = '';

  constructor(
    private recursoService: RecursoService,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {}

  salvar(): void {
    this.erro = '';
    this.cdr.detectChanges();

    if (!this.nome.trim()) {
      this.erro = 'Informe o nome do recurso.';
      return;
    }

    this.salvando = true;

    this.recursoService
      .salvar({
        nome: this.nome,
        tipo: this.tipo,
        status: this.status,
        placa: this.placa,
      })
      .subscribe({
        next: () => {
          this.salvando = false;
          this.router.navigate(['/recursos']);
          this.cdr.detectChanges();
        },
        error: () => {
          this.salvando = false;
          this.erro = 'Não foi possível cadastrar o recurso.';
          this.cdr.detectChanges();
        },
      });
  }
}
