export interface Ocorrencia {
  id?: number;
  titulo: string;
  tipo: string;
  prioridade: 'BAIXA' | 'MEDIA' | 'ALTA' | 'CRITICA';
  status?: 'ABERTA' | 'EM_ATENDIMENTO' | 'FINALIZADA';
  localizacao: string;
  descricao?: string;
  dataRegistro?: string;
}
