export interface Ocorrencia {
  id?: number;
  titulo: string;
  tipo: string;
  prioridade: 'BAIXA' | 'MEDIA' | 'ALTA' | 'CRITICA';
  status?: 'ABERTA' | 'EM_ATENDIMENTO' | 'FINALIZADA' | 'CANCELADA';
  localizacao: string;
  descricao?: string;
  dataAbertura?: string;
}
