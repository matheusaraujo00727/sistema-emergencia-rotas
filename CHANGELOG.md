## [0.2.0] - 2026-06-04

## Funcionalidades

- Cadastro de ocorrências
- Gerenciamento de equipes
- Gerenciamento de recursos operacionais
- Controle de atendimentos
- Relatórios e indicadores

### Added
- DTOs para Ocorrencia, Equipe, Recurso, Usuario e Atendimento
- Mappers para conversão DTO ↔ Entity
- ResourceNotFoundException
- GlobalExceptionHandler
- OcorrenciaController com operações CRUD
- Persistência H2 em arquivo


### Tested
- POST /ocorrencias
- GET /ocorrencias
- GET /ocorrencias/{id}
- 

### Backend

```bash
cd backend
./mvnw spring-boot:run