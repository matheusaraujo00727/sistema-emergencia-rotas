export interface Recurso {
  id?: number;
  nome: string;
  tipo: 'AMBULANCIA' | 'VIATURA' | 'HELICOPTERO' | 'EQUIPAMENTO_MEDICO' | 'OUTRO';
  status: 'DISPONIVEL' | 'INDISPONIVEL';
  placa?: string;
}
