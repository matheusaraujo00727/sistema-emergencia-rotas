import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Ocorrencias } from './pages/ocorrencias/ocorrencias';
import { OcorrenciaForm } from './pages/ocorrencia-form/ocorrencia-form';
import { Recursos } from './pages/recursos/recursos';
import { Equipes } from './pages/equipes/equipes';
import { Relatorios } from './pages/relatorios/relatorios';
import { Login } from './pages/login/login';
import { EquipeForm } from './pages/equipe-form/equipe-form';
import { RecursoForm } from './pages/recurso-form/recurso-form';
import { authGuard } from './guards/auth-guard';
import { adminGuard } from './guards/admin-guard';

export const routes: Routes = [
  { path: 'login', component: Login },

  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'ocorrencias', component: Ocorrencias, canActivate: [authGuard] },
  { path: 'ocorrencias/nova', component: OcorrenciaForm, canActivate: [authGuard] },
  { path: 'equipes', component: Equipes, canActivate: [authGuard] },

  { path: 'recursos', component: Recursos, canActivate: [authGuard, adminGuard] },
  { path: 'relatorios', component: Relatorios, canActivate: [authGuard, adminGuard] },
  { path: 'equipes/nova', component: EquipeForm, canActivate: [authGuard, adminGuard] },
  { path: 'recursos/novo', component: RecursoForm, canActivate: [authGuard, adminGuard] },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' },
];
