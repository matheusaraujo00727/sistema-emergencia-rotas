import { TestBed } from '@angular/core/testing';
import { Ocorrencia } from '../models/ocorrencia';
import { OcorrenciaForm } from '../pages/ocorrencia-form/ocorrencia-form';

describe('Ocorrencia', () => {
  let service: Ocorrencia;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OcorrenciaForm);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
