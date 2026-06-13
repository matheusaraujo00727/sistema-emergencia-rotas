import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  constructor(
    public router: Router,
    public authService: AuthService,
  ) {}

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }

  sair(): void {
    this.authService.logout();
  }
}
