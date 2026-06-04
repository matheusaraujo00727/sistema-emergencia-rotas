import { Component } from '@angular/core';
import { NgClass } from '@angular/common';

interface Recurso {
  id: number;
  codigo: string;
  nome: string;
  tipo: string;
  status: 'DISPONIVEL' | 'EM_ATENDIMENTO' | 'EM_MANUTENCAO';
  localizacao: string;
}

@Component({
  selector: 'app-recursos',
  imports: [NgClass],
  templateUrl: './recursos.html',
  styleUrl: './recursos.css',
})
export class Recursos {
  recursos: Recurso[] = [
    {
      id: 1,
      codigo: 'AMB-001',
      nome: 'Ambulância Alfa',
      tipo: 'Ambulância',
      status: 'DISPONIVEL',
      localizacao: 'Base Central',
    },
    {
      id: 2,
      codigo: 'AMB-002',
      nome: 'Ambulância Beta',
      tipo: 'Ambulância',
      status: 'EM_ATENDIMENTO',
      localizacao: 'Setor Norte',
    },
    {
      id: 3,
      codigo: 'VTR-001',
      nome: 'Viatura Operacional',
      tipo: 'Viatura',
      status: 'DISPONIVEL',
      localizacao: 'Setor Sul',
    },
    {
      id: 4,
      codigo: 'RES-001',
      nome: 'Unidade de Resgate',
      tipo: 'Resgate',
      status: 'EM_MANUTENCAO',
      localizacao: 'Garagem Técnica',
    },
  ];
}
