import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario';

@Component({
  selector: 'app-usuario-cadastro',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './usuario-cadastro.html',
  styleUrl: './usuario-cadastro.css',
})
export class UsuarioCadastro {
  nome = '';
  cpf = '';
  perfil: 'ADMIN' | 'USUARIO' = 'USUARIO';

  salvando = false;
  erro = '';
  sucesso = '';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
  ) {}

  salvar(): void {
    this.erro = '';
    this.sucesso = '';

    if (!this.nome.trim() || !this.cpf.trim()) {
      this.erro = 'Preencha nome e CPF.';
      return;
    }

    this.salvando = true;

    this.usuarioService
      .salvar({
        nome: this.nome,
        cpf: this.cpf,
        perfil: this.perfil,
      })
      .subscribe({
        next: () => {
          this.salvando = false;
          this.sucesso = 'Usuário cadastrado com sucesso.';

          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 1000);
        },
        error: () => {
          this.salvando = false;
          this.erro = 'Não foi possível cadastrar o usuário.';
        },
      });
  }
}
