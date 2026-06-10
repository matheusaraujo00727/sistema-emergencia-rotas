import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../models/usuario';

export type PerfilUsuario = 'ADMIN' | 'USUARIO';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  login(usuario: Usuario): void {
    localStorage.setItem('usuarioNome', usuario.nome);
    localStorage.setItem('usuarioCpf', usuario.cpf);
    localStorage.setItem('perfil', usuario.perfil);

    this.router.navigate(['/dashboard']);
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  estaLogado(): boolean {
    return !!localStorage.getItem('usuarioCpf');
  }

  getPerfil(): PerfilUsuario | null {
    return localStorage.getItem('perfil') as PerfilUsuario | null;
  }

  isAdmin(): boolean {
    return this.getPerfil() === 'ADMIN';
  }
}
