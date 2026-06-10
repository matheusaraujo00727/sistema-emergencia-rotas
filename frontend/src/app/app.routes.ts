import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Ocorrencias } from './pages/ocorrencias/ocorrencias';
import { OcorrenciaForm } from './pages/ocorrencia-form/ocorrencia-form';
import { Recursos } from './pages/recursos/recursos';
import { Equipes } from './pages/equipes/equipes';
import { Relatorios } from './pages/relatorios/relatorios';
import { Login } from './pages/login/login';
import { Admin } from './pages/admin/admin';
import { UsuarioCadastro } from './pages/usuario-cadastro/usuario-cadastro';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  { path: 'login', component: Login },

  { path: 'admin', component: Admin },
  { path: 'usuario/cadastro', component: UsuarioCadastro },

  { path: 'dashboard', component: Dashboard },
  { path: 'ocorrencias', component: Ocorrencias },
  { path: 'ocorrencias/nova', component: OcorrenciaForm },
  { path: 'recursos', component: Recursos },
  { path: 'equipes', component: Equipes },
  { path: 'relatorios', component: Relatorios },

  { path: '**', redirectTo: 'login' },
];
