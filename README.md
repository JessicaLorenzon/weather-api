# Weather API

Projeto feito seguindo o roadmap de projetos do [roadmap.sh](https://roadmap.sh/projects/weather-api-wrapper-service), com o objetivo de praticar desenvolvimento de APIs REST com Java e Spring Boot, com funcionalidades de cache e controle de requisições.

## Descrição

O **Weather API** é uma aplicação backend que permite consultar a previsão do tempo para uma cidade.
A API integra dados do serviço externo [Visual Crossing Weather API](https://www.visualcrossing.com/weather-api/).

## Tecnologias utilizadas

- **Java** – Linguagem principal
- **Spring Boot** – Framework para construção da API
- **Redis** – Cache de respostas
- **Bucket4j** – Rate limiting
- **RestTemplate** – Consumo de APIs externas
- **Visual Crossing Weather API** – Fonte de dados climáticos
- **Maven** – Gerenciador de dependências
- **REST API** – Interface de comunicação baseada em HTTP
- **Postman** – Testes dos endpoints

## Como rodar o projeto

### 1. Baixe ou clone o repositório

```bash
git clone https://github.com/JessicaLorenzon/weather-api.git
```

### 2. Execute o aplicativo

```bash
java -jar target/weather-api-0.0.1.jar
```

## Endpoint disponível

### Consultar previsão do tempo por cidade

```http
GET /weather/{location}
```

## Funcionalidades adicionais

- **Consumo de APIs externas:** as previsões são obtidas do serviço externo via RestTemplate.
- **Cache com Redis:** respostas são armazenadas por tempo configurável para reduzir chamadas externas e melhorar performance.
- **Rate limiting com Bucket4j:** cada cliente possui um limite de requisições por minuto configurável, evitando abusos.