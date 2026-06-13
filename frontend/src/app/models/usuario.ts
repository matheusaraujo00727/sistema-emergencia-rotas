export interface Usuario {
  id?: number;
  nome: string;
  cpf: string;
  perfil: 'ADMIN' | 'USUARIO';
}
