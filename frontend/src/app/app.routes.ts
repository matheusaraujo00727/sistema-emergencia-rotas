import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { Ocorrencias } from './pages/ocorrencias/ocorrencias';
import { OcorrenciaForm } from './pages/ocorrencia-form/ocorrencia-form';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'ocorrencias', component: Ocorrencias },
  { path: 'ocorrencias/nova', component: OcorrenciaForm },
];
