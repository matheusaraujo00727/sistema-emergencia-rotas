import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Equipes } from './pages/equipes/equipes';
import { OcorrenciaForm } from './pages/ocorrencia-form/ocorrencia-form';
import { Ocorrencias } from './pages/ocorrencias/ocorrencias';
import { Recursos } from './pages/recursos/recursos';
import { Relatorios } from './pages/relatorios/relatorios';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'ocorrencias', component: Ocorrencias },
  { path: 'ocorrencias/nova', component: OcorrenciaForm },
  { path: 'recursos', component: Recursos },
  { path: 'equipes', component: Equipes },
  { path: 'relatorios', component: Relatorios },
  { path: '**', redirectTo: 'dashboard' },
];
