import { Ocorrencia } from './ocorrencia';
import { Equipe } from './equipe';
import { Recurso } from './recurso';

export interface Atendimento {
  id?: number;
  ocorrenciaTitulo?: string;
  equipeNome?: string;
  recursoNome?: string;
  ocorrencia?: Ocorrencia;
  equipe?: Equipe;
  recurso?: Recurso;
  dataInicio?: string;
  dataFim?: string | null;
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO';
  observacoes?: string;
}

export interface AtendimentoRequest {
  ocorrenciaId: number;
  equipeId: number;
  recursoId: number;
  status?: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO';
  observacoes: string;
}
