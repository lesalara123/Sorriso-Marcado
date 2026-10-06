# 📄 PRD — Documento de Requisitos do Produto

|                         |                                                    |
| ----------------------- | -------------------------------------------------- |
| **App**                 | Sorriso Marcado                                    |
| **Grupo**               | Desenvolvimento Mobile Android                     |
| **Autores**             | Lara Emanuelle, Hiarlley Francisco e Marina Calado |
| **Versão do documento** | 1.0                                                |
| **Última atualização**  | 06/10/2026                                         |
| **Status**              | ( ) Rascunho ( ) Em revisão ( ) Aprovado           |

---

## 1. Visão do produto

**Pitch:**
O Sorriso Marcado é um aplicativo Android desenvolvido para ajudar pacientes a organizar e acompanhar suas consultas odontológicas de maneira simples e prática.

**Problema:**
Pacientes podem esquecer datas e horários de consultas odontológicas ou ter dificuldade para encontrar informações relacionadas aos seus atendimentos. Essas informações podem ficar espalhadas em anotações, agendas, papéis, mensagens ou outros aplicativos.

**Por que vale a pena fazer isso:**
O Sorriso Marcado centraliza as informações das consultas odontológicas em um único lugar, permitindo que o usuário cadastre, visualize, edite e exclua seus atendimentos. Como os dados são armazenados localmente, as principais funcionalidades continuam disponíveis mesmo sem conexão com a internet.

---

## 2. Público e cenário de uso

**Usuário-alvo:**

Pacientes que realizam consultas odontológicas e precisam organizar seus compromissos.

**História de uso:**

> "O usuário acabou de marcar uma consulta odontológica e precisa guardar as informações do atendimento. Ele abre o Sorriso Marcado, cadastra a data, o horário, o dentista e o procedimento. Depois, consegue visualizar a consulta na tela principal e, caso alguma informação mude, pode editar ou excluir o registro."

---

## 3. Objetivos e não-objetivos

**Objetivos desta versão (v1.0):**

1. Permitir que o usuário cadastre suas consultas odontológicas.
2. Permitir que o usuário visualize, edite e exclua consultas cadastradas.
3. Manter os dados organizados e disponíveis localmente, sem depender de conexão com a internet.

**Não-objetivos (fora do escopo):**

* ❌ Atendimento odontológico online.
* ❌ Diagnóstico de problemas bucais.
* ❌ Prescrição de medicamentos.
* ❌ Integração direta com clínicas.
* ❌ Integração direta com profissionais.
* ❌ Sistema de comunicação com dentistas.
* ❌ Servidor externo.
* ❌ API externa.
* ❌ Atendimento médico ou odontológico por meio do aplicativo.

---

## 4. Requisitos funcionais

| ID   | História de usuário                                                                                                              | Critério de aceite                                                                                                      | Prioridade |
| ---- | -------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ---------- |
| RF01 | Como paciente, quero cadastrar uma consulta odontológica para organizar meu atendimento.                                         | Ao preencher os dados obrigatórios e salvar, a consulta é armazenada localmente.                                        | Must       |
| RF02 | Como paciente, quero validar os campos da consulta para evitar informações incompletas.                                          | Ao tentar salvar uma consulta com algum campo obrigatório vazio, o aplicativo informa que o preenchimento é necessário. | Must       |
| RF03 | Como paciente, quero armazenar minhas consultas para que elas permaneçam disponíveis no aplicativo.                              | Após o cadastro, os dados são armazenados no banco de dados local e podem ser consultados posteriormente.               | Must       |
| RF04 | Como paciente, quero visualizar minhas consultas para acompanhar meus próximos atendimentos.                                     | A tela principal apresenta as consultas cadastradas com data, horário, dentista e procedimento.                         | Must       |
| RF05 | Como paciente, quero editar uma consulta para atualizar suas informações.                                                        | Ao selecionar uma consulta, seus dados atuais são carregados e podem ser alterados e salvos.                            | Must       |
| RF06 | Como paciente, quero excluir uma consulta que não seja mais necessária.                                                          | Após a exclusão, a consulta deixa de aparecer na lista principal.                                                       | Must       |
| RF07 | Como paciente, quero que a lista seja atualizada depois de uma alteração para visualizar os dados corretos.                      | Após cadastrar, editar ou excluir uma consulta, a lista apresentada na tela principal é atualizada.                     | Must       |
| RF08 | Como paciente, quero utilizar as funções principais sem internet para conseguir consultar minhas informações a qualquer momento. | O cadastro, a visualização, a edição e a exclusão das consultas continuam funcionando sem conexão com a internet.       | Must       |

---

## 5. Requisitos não funcionais

| ID    | Requisito                                                                                                               | Como será verificado                               |
| ----- | ----------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------- |
| RNF01 | O aplicativo deve funcionar em dispositivos Android.                                                                    | Instalação e execução em dispositivo Android.      |
| RNF02 | O projeto deve utilizar Kotlin.                                                                                         | Revisão do código-fonte.                           |
| RNF03 | A interface deve utilizar Jetpack Compose.                                                                              | Revisão da implementação das telas.                |
| RNF04 | Os dados das consultas devem ser armazenados localmente utilizando Room.                                                | Teste e revisão da camada de persistência.         |
| RNF05 | O aplicativo não deve depender de conexão com a internet para executar suas funcionalidades principais.                 | Teste do aplicativo sem conexão.                   |
| RNF06 | As telas devem apresentar informações de forma clara e permitir que o usuário compreenda as ações disponíveis.          | Teste de utilização por pessoas externas à equipe. |
| RNF07 | Erros durante operações de salvamento, edição ou exclusão devem ser tratados sem encerrar o aplicativo inesperadamente. | Testes das situações de erro.                      |
| RNF08 | O aplicativo deve possuir nome, ícone e identidade visual próprios.                                                     | Verificação visual do aplicativo.                  |

---

## 6. Telas e navegação

**Mapa de navegação:**

```text
[Tela Principal — lista de consultas]
        │
        ├── toca no "+" → [Tela de Cadastro]
        │
        ├── seleciona uma consulta → [Tela de Edição/Detalhe]
        │
        └── nenhuma consulta → mensagem de estado vazio
```

| Tela           | O que mostra                                                                                        | Ações disponíveis                                                |
| -------------- | --------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| Principal      | Identidade visual do Sorriso Marcado, lista de consultas e informações principais dos atendimentos. | Visualizar consultas, cadastrar nova consulta, editar e excluir. |
| Cadastro       | Campos para data, horário, dentista e procedimento.                                                 | Preencher os dados e salvar a consulta.                          |
| Edição/Detalhe | Dados atuais da consulta selecionada.                                                               | Alterar os dados ou excluir a consulta.                          |

**Estado vazio da tela principal:**

Quando não houver consultas cadastradas, o aplicativo deve apresentar uma mensagem explicando que ainda não existem consultas registradas.

**Rascunhos das telas:**

* `docs/telas/01-principal.png`
* `docs/telas/02-cadastro.png`
* `docs/telas/03-edicao.png`

---

## 7. Dados

### Opção A — Room

**Entidade principal:** `Consulta`

| Campo          | Tipo   | Obrigatório | Observação                                  |
| -------------- | ------ | ----------- | ------------------------------------------- |
| `id`           | Long   | sim         | Identificador da consulta e chave primária. |
| `data`         | String | sim         | Data do atendimento.                        |
| `horario`      | String | sim         | Horário do atendimento.                     |
| `dentista`     | String | sim         | Nome do dentista.                           |
| `procedimento` | String | sim         | Procedimento odontológico.                  |

**Operações necessárias:**

* ☑ inserir
* ☑ listar
* ☑ atualizar
* ☑ excluir

O armazenamento será realizado localmente utilizando Room.

### Retrofit

**Não utilizado na versão 1.0.**

O Sorriso Marcado não utilizará API externa ou servidor na versão atual. Portanto, não haverá endpoint, chave de API ou dependência de internet para o funcionamento principal.

---

## 8. Arquitetura e tecnologias

| Item                   | Escolha                              |
| ---------------------- | ------------------------------------ |
| Linguagem              | Kotlin                               |
| Interface              | ☑ Jetpack Compose                    |
| Persistência           | ☑ Room                               |
| Rede                   | —                                    |
| Outras bibliotecas     | AndroidX                             |
| `minSdk` / `targetSdk` | A definir na configuração do projeto |

**Tecnologias utilizadas:**

* Kotlin
* Jetpack Compose
* Room
* AndroidX
* Android Studio
* Git/GitHub

**Organização de pastas do projeto:**

```text
app/src/main/java/br/edu/ifpe/sorrisomarcado/
├── ui/        # telas e componentes da interface
├── data/      # entidade, DAO e banco de dados Room
└── MainActivity.kt
```

A camada `ui` será responsável pela interface e pelos componentes visuais.

A camada `data` será responsável pelo armazenamento e gerenciamento dos dados das consultas.

---

## 9. Tratamento de erros

| Situação de falha           | O que o app faz                                                                                | Mensagem para o usuário                                                 |
| --------------------------- | ---------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| Lista vazia                 | Mantém a tela principal e apresenta o estado vazio.                                            | "Nenhuma consulta cadastrada."                                          |
| Campo obrigatório em branco | Impede o salvamento até que os campos necessários sejam preenchidos.                           | "Preencha os campos obrigatórios."                                      |
| Erro ao salvar no banco     | Mantém o usuário na tela e informa que a operação não foi concluída.                           | "Não foi possível salvar a consulta. Tente novamente."                  |
| Erro ao editar              | Mantém os dados anteriores e informa que a alteração não foi realizada.                        | "Não foi possível editar a consulta. Tente novamente."                  |
| Erro ao excluir             | Mantém a consulta na lista e informa que a exclusão não foi realizada.                         | "Não foi possível excluir a consulta. Tente novamente."                 |
| Sem internet                | As funcionalidades principais continuam disponíveis, pois os dados são armazenados localmente. | Não é necessário apresentar erro de conexão para as funções principais. |

---

## 10. Identidade visual e publicação

| Item                          | Definição                                                                                                          | Onde fica            |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------ | -------------------- |
| Nome do app                   | Sorriso Marcado                                                                                                    | `strings.xml`        |
| Cor principal                 | `#E86A92`                                                                                                          | `Color.kt`           |
| Cor secundária                | A definir pelo grupo                                                                                               | `Color.kt`           |
| Ícone 512×512                 | Tubarão menina fofinho usando roupa de dentista e segurando um calendário relacionado a uma consulta odontológica. | `loja/icone-512.png` |
| `applicationId`               | `br.edu.ifpe.sorrisomarcado`                                                                                       | `build.gradle.kts`   |
| `versionName` / `versionCode` | `1.0` / `1`                                                                                                        | `build.gradle.kts`   |

**Material da loja:**

| Artefato              | Limite        | Conteúdo                                                |
| --------------------- | ------------- | ------------------------------------------------------- |
| Título                | 30 caracteres | Sorriso Marcado                                         |
| Descrição curta       | 80 caracteres | Organize suas consultas odontológicas de forma simples. |
| Descrição completa    | —             | `loja/descricao.md`                                     |
| Imagem de destaque    | 1024×500      | `loja/destaque-1024x500.png`                            |
| Screenshots           | mín. 2        | `loja/screenshots/`                                     |
| Esboço de privacidade | —             | `loja/privacidade.md`                                   |
| Arquivo `.aab`        | —             | `loja/app-release.aab`                                  |

---

## 11. Plano de testes

| #  | O que testar                  | Passos                                                                       | Resultado esperado                                                        | OK? |
| -- | ----------------------------- | ---------------------------------------------------------------------------- | ------------------------------------------------------------------------- | --- |
| T1 | Abrir o app pela primeira vez | Instalar e abrir o aplicativo.                                               | A tela principal aparece e informa que não existem consultas cadastradas. |     |
| T2 | Cadastrar consulta            | Abrir o cadastro, preencher os campos e salvar.                              | A consulta é armazenada e aparece na tela principal.                      |     |
| T3 | Validar campos obrigatórios   | Tentar salvar uma consulta deixando um campo obrigatório vazio.              | O aplicativo informa que o preenchimento é necessário e não fecha.        |     |
| T4 | Visualizar consulta           | Cadastrar uma consulta e verificar a tela principal.                         | Data, horário, dentista e procedimento aparecem corretamente.             |     |
| T5 | Editar consulta               | Selecionar uma consulta e alterar seus dados.                                | Os dados atualizados aparecem na lista.                                   |     |
| T6 | Excluir consulta              | Selecionar uma consulta e excluí-la.                                         | A consulta deixa de aparecer na lista.                                    |     |
| T7 | Reabrir o aplicativo          | Fechar e abrir o aplicativo novamente.                                       | Os dados cadastrados continuam disponíveis.                               |     |
| T8 | Testar sem internet           | Desativar a conexão e repetir as funções principais.                         | Cadastro, visualização, edição e exclusão continuam funcionando.          |     |
| T9 | Teste com usuário externo     | Entregar o aplicativo para uma pessoa que não participou do desenvolvimento. | A pessoa consegue compreender e realizar o fluxo principal sem auxílio.   |     |

**Testado em:**
Preencher com o modelo dos celulares utilizados pelo grupo e a versão do Android.

---

## 12. Cronograma

| Marco                                | Prazo          | Responsável        | Status       |
| ------------------------------------ | -------------- | ------------------ | ------------ |
| M1 — Canvas + repositório            | 16/09/2026     | Grupo              | Concluído    |
| M2 — PRD aprovado + telas            | 30/09/2026     | Grupo              | Concluído    |
| M3 — Funcionalidade base             | 21/10/2026     | Grupo              | Em andamento |
| M4 — Dados completos                 | 11/11/2026     | Hiarlley Francisco | Pendente     |
| M5 — Identidade visual + APK         | 25/11/2026     | Marina Calado      | Pendente     |
| M6 — AAB + material de loja + README | 02/12/2026     | Grupo              | Pendente     |
| **Entrega e apresentação**           | **10/12/2026** | **Grupo**          | **Pendente** |

---

## 13. Riscos

| Risco                                         | Impacto | Plano B                                                     |
| --------------------------------------------- | ------- | ----------------------------------------------------------- |
| Problemas na implementação do Room            | Alto    | Simplificar a estrutura do banco e revisar a implementação. |
| Atraso no desenvolvimento das telas           | Médio   | Priorizar as funcionalidades principais do MVP.             |
| Erros durante operações de dados              | Alto    | Implementar tratamento de exceções e mensagens ao usuário.  |
| Dificuldade de integração entre telas e dados | Médio   | Revisar a comunicação entre as camadas `ui` e `data`.       |
| Problemas durante o build                     | Alto    | Revisar dependências e configuração do Gradle.              |

---

## 14. Como vamos orientar a implementação com IA

A Inteligência Artificial será utilizada como ferramenta de apoio durante o desenvolvimento do Sorriso Marcado.

**Recursos que vamos usar:**

* ☑ Chat
* ☑ Agent Mode
* ☑ Explain Code
* ☑ Ask Gemini no Logcat
* ☑ Generate Unit Tests
* ☑ Transform UI

**Regras que colocamos no `AGENTS.md`:**

* A IA deve seguir a estrutura e os padrões definidos pelo grupo.
* Nenhum código deve ser utilizado sem que os integrantes compreendam seu funcionamento.
* A IA deve ser utilizada como apoio, e não como substituição do conhecimento da equipe.
* Alterações sugeridas pela IA devem ser revisadas pelos integrantes antes de serem utilizadas.
* Senhas ou chaves de API não devem ser inseridas em prompts.
* Todos os integrantes devem conseguir explicar as principais partes do projeto.

**Divisão do perímetro explicável:**

| Parte do código              | Responsável             |
| ---------------------------- | ----------------------- |
| Telas (`ui/`)                | Lara Emanuelle          |
| Dados (`data/`)              | Hiarlley Francisco      |
| Identidade visual e recursos | Marina Calado           |
| Documentação e build         | Lara, Hiarlley e Marina |

**Decisões que o grupo tomou contra a sugestão da IA:**

* Utilizar Room para armazenamento local.
* Não utilizar servidor ou API externa na versão 1.0.
* Manter o foco na organização de consultas odontológicas.
* Utilizar uma identidade visual própria para o Sorriso Marcado.

---

## 15. Histórico de versões deste documento

| Versão | Data       | Autor                                              | O que mudou                                                             |
| ------ | ---------- | -------------------------------------------------- | ----------------------------------------------------------------------- |
| 1.0    | 06/10/2026 | Lara Emanuelle, Hiarlley Francisco e Marina Calado | Primeira versão do PRD preenchida com os requisitos do Sorriso Marcado. |
