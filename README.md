# Scheduling API

> API REST para gerenciar agendamentos, estados e consultas de compromissos.

**Stack:** Java 21 · Spring Boot 3 · Spring Data JPA · H2 · Maven  
**Status:** Projeto de estudo / portfólio

## Funcionalidades

- Criação, consulta e exclusão de agendamentos
- Busca por dia, cliente e status
- Atualização do estado do atendimento
- Resumo de agendamentos por situação

## Endpoints principais

| Método | Rota | Objetivo |
|---|---|---|
| `GET` | `/api/appointments` | Lista agendamentos |
| `GET` | `/api/appointments/date/{date}` | Filtra por data ISO |
| `GET` | `/api/appointments/status/{status}` | Filtra por status |
| `GET` | `/api/appointments/summary` | Resumo de estados |
| `POST` | `/api/appointments` | Cria agendamento |
| `PATCH` | `/api/appointments/{id}/status?status=...` | Altera estado |
| `DELETE` | `/api/appointments/{id}` | Exclui |

## Executar localmente

Requisitos: **JDK 21** e conexão com o banco configurada conforme `src/main/resources/application.properties`.

```bash
git clone https://github.com/fgscarvalho37-a11y/scheduling-api.git
cd scheduling-api
./mvnw spring-boot:run
```

No Windows, utilize `mvnw.cmd spring-boot:run`. A porta e as propriedades de banco dependem da configuração da aplicação.

## Organização

`controller` recebe as requisições; `service` centraliza regras; `repository` acessa dados; `model` e `dto` representam entidades e dados de entrada/saída.

## Notas

Interface complementar: [scheduling-front](https://github.com/fgscarvalho37-a11y/scheduling-front). Aplicação demonstrativa.

---

Desenvolvido por [Felipe Gomes](https://github.com/fgscarvalho37-a11y).