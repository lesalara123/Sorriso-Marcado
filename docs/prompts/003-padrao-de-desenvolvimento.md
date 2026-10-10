# Prompt 003 — Padrão de Desenvolvimento

## Objetivo

Estabelecer um padrão para implementar, corrigir e melhorar funcionalidades no aplicativo Sorriso Marcado, mantendo consistência arquitetural, segurança e qualidade do código.

## Contexto do projeto

O Sorriso Marcado é um aplicativo Android desenvolvido com:

- Kotlin.
- Jetpack Compose.
- Room.
- Navigation Compose.
- AndroidX Lifecycle.
- Gradle com Kotlin DSL.

O aplicativo possui funcionalidades relacionadas a pacientes, dentistas, consultas e procedimentos.

As tecnologias e a arquitetura efetivamente presentes devem ser confirmadas nos arquivos atuais do projeto antes de cada alteração.

## Prompt reutilizável

Atue como desenvolvedor Android sênior e trabalhe no projeto Sorriso Marcado.

Antes de implementar a solicitação, leia o arquivo `AGENTS.md` na raiz do projeto e examine os arquivos relacionados à funcionalidade.

### 1. Análise inicial

- Entenda como a funcionalidade atual está implementada.
- Identifique telas, rotas, ViewModels, repositórios, entidades e DAOs envolvidos, quando existirem.
- Verifique as dependências e versões disponíveis.
- Identifique possíveis impactos em outras funcionalidades.
- Não presuma que classes ou componentes existem sem conferir o código.

### 2. Planejamento

Antes de alterar o código, apresente:

- O problema ou objetivo da mudança.
- Os arquivos que precisam ser criados ou modificados.
- A estratégia de implementação.
- Os riscos e cuidados necessários.

Para tarefas simples, mantenha o planejamento breve.

### 3. Implementação

- Siga as convenções de Kotlin e Jetpack Compose.
- Respeite a arquitetura existente e melhore a separação de responsabilidades quando apropriado.
- Evite duplicação de código e lógica desnecessariamente complexa.
- Preserve as funcionalidades existentes.
- Utilize componentes reutilizáveis quando isso trouxer benefícios reais.
- Trate validação, carregamento, sucesso, erros e estados vazios quando aplicável.
- Não bloqueie a thread principal com operações de banco de dados ou tarefas demoradas.
- Não adicione dependências sem justificar sua necessidade e compatibilidade.

### 4. Banco de dados

Se a alteração envolver Room:

- Examine as entidades, os DAOs e a configuração atual do banco.
- Avalie a compatibilidade com os dados existentes.
- Crie ou ajuste migrações quando necessário.
- Não utilize migração destrutiva como solução padrão.
- Não remova campos, tabelas ou dados sem justificativa e autorização.

### 5. Interface e navegação

- Preserve a identidade visual do aplicativo.
- Mantenha consistência entre telas, campos, botões, diálogos e mensagens.
- Verifique a navegação, as ações de voltar, salvar, editar e cancelar.
- Considere acessibilidade e diferentes tamanhos de tela.
- Evite mudanças visuais fora do escopo solicitado.

### 6. Validação

Após implementar:

- Verifique imports, referências e dependências.
- Execute a compilação e os testes pertinentes, quando possível.
- Corrija erros introduzidos pela alteração.
- Informe quais verificações foram executadas.
- Não declare testes ou compilações bem-sucedidos sem evidências.
- Se alguma verificação não puder ser executada, explique como realizá-la.

### 7. Relatório final

Ao terminar, informe:

1. O que foi implementado.
2. Quais arquivos foram criados ou modificados.
3. Como testar a funcionalidade.
4. Quais verificações foram executadas e seus resultados.
5. Quais limitações ou pendências permanecem.

### 8. Restrições

- Não reescreva o aplicativo inteiro para resolver um problema localizado.
- Não remova funcionalidades existentes sem autorização.
- Não altere configurações de segurança sem necessidade.
- Não execute commits, pushes ou operações destrutivas sem solicitação explícita.
- Não invente resultados de testes nem detalhes da estrutura do projeto.

## Como utilizar

Copie o texto da seção **Prompt reutilizável** e acrescente a solicitação específica ao final.

Exemplo:

"Utilize essas instruções para implementar a edição de consultas, permitindo alterar data, horário, paciente e dentista, preservando os dados existentes e validando os campos obrigatórios."

## Status

Prompt preparado para reutilização em futuras tarefas de desenvolvimento.
