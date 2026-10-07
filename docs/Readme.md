# 🦷 Sorriso Marcado

Material de apresentação e documentação do projeto final de **Desenvolvimento Mobile Android**.

**Turma:** 3º ano do Ensino Médio · 
**Instituição:** IFPE — Campus Palmares · **Entrega:** 10/12/2026

O **Sorriso Marcado** é um aplicativo Android desenvolvido para ajudar pacientes a organizar e acompanhar suas consultas odontológicas de forma simples e prática.

---

## O que tem aqui

| Arquivo                                          | Para quê                                                               | Quando            |
| ------------------------------------------------ | ---------------------------------------------------------------------- | ----------------- |
| [`CANVAS.md`](CANVAS.md)                         | Apresentação da ideia, público-alvo, problema e proposta do aplicativo | M1                |
| [`PRD.md`](PRD.md)                               | Documento de requisitos, funcionalidades e objetivos do projeto        | M2                |
| [`RUBRICA.md`](RUBRICA.md)                       | Critérios de avaliação do projeto, conforme o material da disciplina   | Consulta contínua |
| [`docs/USO_DE_IA.md`](docs/USO_DE_IA.md)         | Registro do uso de Inteligência Artificial durante o desenvolvimento   | Contínuo          |
| [`docs/AGENTS_MODELO.md`](docs/AGENTS_MODELO.md) | Modelo de orientações para o uso de IA no projeto                      | M1                |
| [`docs/GUIA_GITHUB.md`](docs/GUIA_GITHUB.md)     | Orientações sobre repositório, commits e organização do código         | M1 em diante      |
| [`docs/README_MODELO.md`](docs/README_MODELO.md) | Modelo de documentação para o aplicativo                               | M6                |

---

## Sobre o aplicativo

O Sorriso Marcado tem como objetivo facilitar a organização das consultas odontológicas, reunindo as principais informações dos atendimentos em um único lugar.

### Funcionalidades principais

* Cadastro de consultas odontológicas;
* Visualização das consultas cadastradas;
* Edição das informações de uma consulta;
* Exclusão de consultas;
* Armazenamento local dos dados;
* Funcionamento das principais funcionalidades sem conexão com a internet.

As informações registradas incluem data, horário, dentista e procedimento.

### Fora do escopo

A versão 1.0 não contempla atendimento odontológico online, diagnóstico de problemas bucais, prescrição de medicamentos, integração direta com clínicas ou profissionais, servidores externos ou APIs externas.

---

## Como o projeto está sendo desenvolvido

O desenvolvimento do Sorriso Marcado é realizado de forma colaborativa pelos três integrantes da equipe. Todos participam das etapas de planejamento, programação, criação e ajustes das telas, banco de dados, identidade visual, testes, documentação e geração do aplicativo.

O projeto utiliza Kotlin, Jetpack Compose e Room para desenvolver uma aplicação Android com armazenamento local.

O processo de desenvolvimento envolve:

1. Planejamento da proposta e definição do público-alvo.
2. Organização dos requisitos e funcionalidades no `PRD.md`.
3. Criação e desenvolvimento das telas do aplicativo.
4. Implementação do cadastro, visualização, edição e exclusão de consultas.
5. Implementação do armazenamento local com Room.
6. Tratamento de erros e validação dos campos obrigatórios.
7. Testes das funcionalidades e ajustes da interface.
8. Preparação da documentação e do build final.

---

## Marcos

| Marco     | Prazo          | Entrega                                       |
| --------- | -------------- | --------------------------------------------- |
| M1        | 16/09/2026     | Repositório e `CANVAS.md` preenchido          |
| M2        | 30/09/2026     | `PRD.md` e rascunho das telas                 |
| M3        | 21/10/2026     | Funcionalidades básicas e tratamento de erros |
| M4        | 11/11/2026     | Persistência de dados com Room                |
| M5        | 25/11/2026     | Identidade visual e APK testado               |
| M6        | 02/12/2026     | AAB, material de loja e documentação          |
| **Final** | **10/12/2026** | **Versão 1.0 e apresentação do projeto**      |

---

## Os três princípios

1. **Simplicidade e qualidade.** O aplicativo prioriza funcionalidades essenciais, uma interface clara e um funcionamento estável.
2. **Organização antes da implementação.** Os requisitos e o escopo são definidos antes da implementação das funcionalidades.
3. **Desenvolvimento colaborativo e compreensão.** Todos os integrantes participam do projeto e devem compreender as principais partes do código, incluindo as alterações realizadas com auxílio de Inteligência Artificial.

---

## Tecnologias utilizadas

| Item               | Escolha         |
| ------------------ | --------------- |
| Linguagem          | Kotlin          |
| Interface          | Jetpack Compose |
| Persistência       | Room            |
| Plataforma         | Android         |
| Rede               | Não utilizada   |
| API externa        | Não utilizada   |
| Controle de versão | Git e GitHub    |
| Versão             | 1.0             |

### Organização do código

```text
app/src/main/java/br/edu/ifpe/sorrisomarcado/
├── ui/     # telas e componentes da interface
├── data/   # banco de dados e persistência
└── MainActivity.kt
```

---

## Nota sobre o desenvolvimento e a avaliação

O Sorriso Marcado é um projeto acadêmico desenvolvido para a disciplina de Desenvolvimento Mobile Android.

Além de implementar as funcionalidades propostas, a equipe busca garantir que o aplicativo seja compreensível, organizado e funcional. Todos os integrantes devem conseguir explicar as principais partes do código, as decisões tomadas durante o desenvolvimento e o funcionamento das funcionalidades implementadas.

A avaliação deverá seguir os critérios definidos pelo professor e pelo material oficial da disciplina.

---

## 👥 Equipe

O projeto é desenvolvido de forma colaborativa pelos três integrantes:

* **Lara Emanuelle**
* **Hiarlley Francisco**
* **Marina Calado**

Todos participam conjuntamente do planejamento, desenvolvimento, testes, identidade visual, documentação e build do aplicativo.

---

## 🤖 Uso de Inteligência Artificial

As ferramentas de Inteligência Artificial podem ser utilizadas como apoio para pesquisas, esclarecimento de dúvidas, implementação, revisão de código, identificação de erros e sugestões de melhorias.

As alterações sugeridas por IA devem ser revisadas e compreendidas pelos integrantes antes de serem incorporadas ao projeto.

O registro detalhado do uso de Inteligência Artificial está disponível em [`docs/USO_DE_IA.md`](docs/USO_DE_IA.md).

---

## 📚 Repositório

[GitHub — Sorriso Marcado](https://github.com/lesalara123/Sorriso-Marcado)

---

## 📄 Licença

Projeto acadêmico destinado ao uso educacional, desenvolvido no IFPE — Campus Palmares, durante a disciplina de Desenvolvimento Mobile Android, em 2026.
