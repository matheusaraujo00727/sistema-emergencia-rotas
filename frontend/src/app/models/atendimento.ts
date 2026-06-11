import { Ocorrencia } from './ocorrencia';
import { Equipe } from './equipe';

export interface Atendimento {
  id?: number;
  ocorrencia?: Ocorrencia;
  equipe?: Equipe;
  dataInicio?: string;
  dataFim?: string | null;
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO';
  observacoes?: string;
}

export interface AtendimentoRequest {
  ocorrenciaId: number;
  equipeId: number;
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDO';
  observacoes: string;
}
