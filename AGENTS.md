# AGENTS.md — Sorriso Marcado

## 1. Sobre o projeto

O **Sorriso Marcado** é um aplicativo Android desenvolvido em Kotlin para gerenciamento de informações odontológicas, incluindo pacientes, dentistas, consultas e procedimentos.

O objetivo é manter o aplicativo organizado, funcional, seguro e fácil de evoluir.

## 2. Tecnologias utilizadas

- **Linguagem:** Kotlin
- **Interface:** Jetpack Compose
- **Banco de dados local:** Room
- **Navegação:** Navigation Compose
- **Ciclo de vida:** AndroidX Lifecycle
- **Arquitetura desejada:** MVVM (Model-View-ViewModel)
- **Build:** Gradle com Kotlin DSL
- **Tema:** Material Design para Android

Antes de adicionar ou atualizar dependências, confira as versões existentes nos arquivos Gradle e priorize a compatibilidade com o projeto.

## 3. Regras de arquitetura

- Prefira a arquitetura MVVM para separar interface, estado e lógica de negócio.
- Mantenha as telas responsáveis pela apresentação e interação com o usuário.
- Utilize ViewModels para gerenciar o estado das telas e coordenar operações.
- Utilize repositórios para centralizar o acesso aos dados quando apropriado.
- Mantenha entidades, DAOs e configurações do Room organizados.
- Evite acessar o banco de dados diretamente dentro de componentes composables.
- Não concentre toda a lógica de negócio em uma única tela ou arquivo.
- Antes de criar novas classes ou pastas, examine a estrutura existente e reutilize os componentes adequados.
- Não refatore módulos inteiros sem necessidade.

## 4. Regras para Kotlin e Jetpack Compose

- Siga as convenções oficiais de nomenclatura do Kotlin.
- Utilize nomes claros e descritivos para classes, funções e variáveis.
- Prefira funções composables pequenas, reutilizáveis e com responsabilidades bem definidas.
- Evite operações bloqueantes na thread principal.
- Utilize corrotinas e mecanismos de estado adequados para operações assíncronas.
- Preserve o estado da interface durante recomposições e mudanças de configuração quando necessário.
- Trate estados de carregamento, sucesso, lista vazia e erro quando forem relevantes.
- Mantenha consistência visual entre telas, campos, botões, diálogos e mensagens.
- Considere acessibilidade, contraste, tamanhos de toque e adaptação a diferentes telas.

## 5. Banco de dados e integridade

- Preserve os dados existentes sempre que possível.
- Antes de alterar entidades, DAOs ou o banco de dados, analise as dependências e o impacto das mudanças.
- Confira a versão atual do banco Room e as estratégias de migração.
- Não altere a versão do banco sem avaliar se uma migração é necessária.
- Não utilize migrações destrutivas como solução padrão.
- Preserve relacionamentos e integridade dos dados de pacientes, dentistas, consultas e procedimentos.
- Evite excluir ou sobrescrever dados sem necessidade explícita.

## 6. Navegação e telas

- Preserve o funcionamento das rotas existentes.
- Antes de criar uma nova tela, verifique o grafo de navegação e os componentes disponíveis.
- Garanta que ações de voltar, salvar, editar e cancelar tenham comportamento consistente.
- Evite duplicar telas ou implementar novamente funcionalidades que já existem.
- Mantenha o tema e a identidade visual do Sorriso Marcado.
- Preserve a configuração do ícone do aplicativo e da Splash Screen, salvo quando houver uma solicitação específica para modificá-los.

## 7. Segurança e privacidade

- Trate os dados dos pacientes como informações sensíveis.
- Não registre informações pessoais ou clínicas desnecessárias em logs.
- Não inclua senhas, tokens, chaves privadas ou credenciais no código ou na documentação.
- Não envie dados pessoais para serviços externos sem necessidade e autorização apropriada.
- Valide entradas do usuário e apresente mensagens de erro compreensíveis.
- Evite alterações que possam causar perda de dados.

## 8. Processo obrigatório antes de alterar código

1. Examine os arquivos envolvidos e compreenda a implementação atual.
2. Identifique dependências, rotas e componentes afetados.
3. Explique brevemente o que será alterado e por quê.
4. Faça mudanças pequenas e coerentes com a arquitetura existente.
5. Preserve funcionalidades que não fazem parte da solicitação.
6. Verifique imports, dependências, recursos XML e configurações Gradle afetados.
7. Execute ou recomende a compilação e os testes pertinentes.
8. Informe quais arquivos foram alterados e quais verificações foram realizadas.
9. Se não for possível executar testes, informe isso claramente.

## 9. Regras para Git e GitHub

- Não execute `git commit`, `git push`, `git reset --hard` ou operações destrutivas sem solicitação explícita do usuário.
- Não descarte alterações existentes sem autorização.
- Antes de sugerir um commit, confira o estado do repositório.
- Utilize mensagens de commit claras e descritivas.
- Não inclua arquivos temporários, credenciais, arquivos locais pessoais ou artefatos de build sem necessidade.
- Preserve as alterações já realizadas pelo usuário.

## 10. Documentação

- Utilize `docs/prompts/` para registrar prompts relevantes usados durante o desenvolvimento.
- Utilize `docs/outputs/` para registrar relatórios, resultados de análises e documentação produzida a partir desses prompts.
- Mantenha o `AGENTS.md` na raiz como referência geral para agentes de IA.
- Documente decisões arquiteturais importantes e mudanças que afetem a manutenção do aplicativo.
- Não invente resultados de testes nem declare funcionalidades concluídas sem evidências.

## 11. Como responder às solicitações

- Responda em português brasileiro, salvo solicitação diferente.
- Explique as mudanças de forma clara e objetiva.
- Ao fornecer código, indique o caminho exato do arquivo.
- Quando for necessário substituir um arquivo, informe explicitamente.
- Prefira soluções completas, compatíveis com o projeto e prontas para aplicar.
- Explique riscos, limitações e alterações de banco de dados antes de mudanças relevantes.
- Não afirme que o código compilou ou que os testes passaram sem verificar.
- Se houver informações insuficientes, examine o projeto antes de assumir detalhes.

## 12. Princípio fundamental

**Preservar o que já funciona, compreender antes de modificar e priorizar código seguro, organizado, testável e sustentável.**

Todas as alterações devem respeitar a estrutura real do projeto Sorriso Marcado, e não presumir que funcionalidades ou classes existem sem verificar os arquivos.