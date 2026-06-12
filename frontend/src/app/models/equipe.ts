export interface Equipe {
  id?: number;
  nome: string;
  especialidade: string;
  status: 'DISPONIVEL' | 'EM_ATENDIMENTO' | 'INDISPONIVEL';
  quantidadeMembros: number;
}
