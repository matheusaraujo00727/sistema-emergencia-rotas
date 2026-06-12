import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface Token {
  tipo: string;
  valor: string;
}

@Component({
  selector: 'app-relatorios',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './relatorios.html',
  styleUrl: './relatorios.css',
})
export class Relatorios {
  consulta = '';
  resultadoConsulta = '';
  tokens: Token[] = [];

  validarConsulta(): void {
    this.tokens = [];
    this.resultadoConsulta = '';

    const regex =
      /^parametro\.[a-zA-Z]+ = "[a-zA-Z0-9_À-ÿ ]+"( AND parametro\.[a-zA-Z]+ = "[a-zA-Z0-9_À-ÿ ]+")*$/;

    if (!regex.test(this.consulta.trim())) {
      this.resultadoConsulta = 'Consulta inválida conforme a gramática definida.';
      return;
    }

    this.tokens = this.analisarLexico(this.consulta);
    this.resultadoConsulta = 'Consulta sintaticamente válida.';
  }

  analisarLexico(entrada: string): Token[] {
    const partes = entrada.match(/parametro\.[a-zA-Z]+|=|"[^"]+"|AND/g) || [];

    return partes.map((parte) => {
      if (parte.startsWith('parametro.')) {
        return { tipo: 'IDENTIFICADOR', valor: parte };
      }

      if (parte === '=') {
        return { tipo: 'OPERADOR', valor: parte };
      }

      if (parte === 'AND') {
        return { tipo: 'CONJUNÇÃO', valor: parte };
      }

      return { tipo: 'VALOR_LITERAL', valor: parte };
    });
  }
}
