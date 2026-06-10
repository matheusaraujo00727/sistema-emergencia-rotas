import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export type PerfilUsuario = 'ADMIN' | 'USUARIO';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  login(email: string, senha: string): boolean {
    if (email === 'admin@vitalis.com' && senha === 'admin') {
      localStorage.setItem('perfil', 'ADMIN');
      this.router.navigate(['/dashboard']);
      return true;
    }

    if (email && senha) {
      localStorage.setItem('perfil', 'USUARIO');
      this.router.navigate(['/dashboard']);
      return true;
    }

    return false;
  }

  logout(): void {
    localStorage.removeItem('perfil');
    this.router.navigate(['/login']);
  }

  estaLogado(): boolean {
    return localStorage.getItem('perfil') !== null;
  }

  getPerfil(): PerfilUsuario | null {
    return localStorage.getItem('perfil') as PerfilUsuario | null;
  }

  isAdmin(): boolean {
    return this.getPerfil() === 'ADMIN';
  }
}
