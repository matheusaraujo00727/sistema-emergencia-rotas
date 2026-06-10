import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { UsuarioService } from '../../services/usuario';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  modo: 'LOGIN' | 'CADASTRO' = 'LOGIN';

  nome = '';
  cpf = '';

  nomeCadastro = '';
  cpfCadastro = '';

  erro = '';
  sucesso = '';
  salvando = false;

  constructor(
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private cdr: ChangeDetectorRef,
  ) {}

  entrar(): void {
    this.erro = '';

    if (!this.nome.trim() || !this.validarCpf(this.cpf)) {
      this.erro = 'Informe nome e CPF válido.';
      return;
    }

    this.usuarioService.listar().subscribe({
      next: (usuarios) => {
        const usuarioEncontrado = usuarios.find(
          (usuario) =>
            usuario.nome.trim().toLowerCase() === this.nome.trim().toLowerCase() &&
            usuario.cpf === this.cpf,
        );

        if (!usuarioEncontrado) {
          this.erro = 'Usuário não encontrado. Verifique nome e CPF.';
          return;
        }

        this.authService.login(usuarioEncontrado);
      },
      error: () => {
        this.erro = 'Não foi possível validar o login.';
      },
    });
  }

  cadastrar(): void {
    this.erro = '';
    this.sucesso = '';

    if (!this.nomeCadastro.trim() || !this.validarCpf(this.cpfCadastro)) {
      this.erro = 'Informe nome e CPF válido.';
      return;
    }

    this.salvando = true;

    this.usuarioService
      .salvar({
        nome: this.nomeCadastro,
        cpf: this.cpfCadastro,
        perfil: 'USUARIO',
      })
      .subscribe({
        next: () => {
          this.salvando = false;
          this.sucesso = 'Usuário cadastrado com sucesso. Agora faça login.';

          this.modo = 'LOGIN';
          this.nome = this.nomeCadastro;
          this.cpf = this.cpfCadastro;

          this.nomeCadastro = '';
          this.cpfCadastro = '';

          this.cdr.detectChanges();
        },
        error: (erro) => {
          this.salvando = false;

          console.error('Erro ao cadastrar usuário:', erro);

          const mensagem = erro?.error?.message || erro?.error || '';

          if (erro.status === 500 && String(mensagem).includes('Unique')) {
            this.erro = 'CPF já cadastrado no sistema.';
          } else if (erro.status === 500 && String(mensagem).includes('cpf')) {
            this.erro = 'CPF já cadastrado no sistema.';
          } else if (erro.status === 400) {
            this.erro = 'Dados inválidos. Verifique o CPF informado.';
          } else {
            this.erro = 'CPF já cadastrado ou dados inválidos.';
          }

          this.cdr.detectChanges();
        },
      });
  }

  validarCpf(cpf: string): boolean {
    return /^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpf);
  }
}
