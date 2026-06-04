import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Ocorrencia } from '../../models/ocorrencia';

@Component({
  selector: 'app-ocorrencias',
  imports: [NgClass, RouterLink],
  templateUrl: './ocorrencias.html',
  styleUrl: './ocorrencias.css',
})
export class Ocorrencias {
  listaOcorrencias: Ocorrencia[] = [
    {
      id: 1,
      titulo: 'Acidente na Avenida Central',
      tipo: 'Acidente de trânsito',
      prioridade: 'ALTA',
      status: 'ABERTA',
      localizacao: 'Avenida Central, Setor Norte',
      dataRegistro: '2026-06-01 14:20',
    },
    {
      id: 2,
      titulo: 'Incêndio em residência',
      tipo: 'Incêndio',
      prioridade: 'CRITICA',
      status: 'EM_ATENDIMENTO',
      localizacao: 'Rua 12, Bairro Industrial',
      dataRegistro: '2026-06-01 15:05',
    },
    {
      id: 3,
      titulo: 'Queda de energia em hospital',
      tipo: 'Infraestrutura',
      prioridade: 'MEDIA',
      status: 'FINALIZADA',
      localizacao: 'Hospital Municipal de Cidália',
      dataRegistro: '2026-06-01 16:10',
    },
  ];
}
