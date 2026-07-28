# Plano de Renomeação de Variáveis

## Overview
O usuário solicitou a renomeação de variáveis no código para torná-las mais intuitivas, substituindo letras soltas ou abreviações obscuras por nomes que reflitam seu propósito. No entanto, de acordo com a última instrução, **abreviações comuns da linguagem e do ecossistema** (como `DAO`, `DTO`, `conn`, `rs`, `pstm`) podem ser mantidas, pois já são padronizadas e amplamente compreendidas por desenvolvedores Java.

## Project Type
**DESKTOP / BACKEND**

## Success Criteria
1. Variáveis com nomes de uma única letra (ex: `d`, `dOpt`) ou pouco claras (ex: `sel`, `obs`) devem ser substituídas por nomes descritivos (ex: `dinossauro`, `dinossauroOpcional`, `dinossauroSelecionado`, `listaObservavel`).
2. Abreviações que são padrões de mercado (ex: sufixos `DAO`, `DTO`, e variáveis típicas de JDBC como `conn`, `pstm`, `rs`) **serão mantidas**.
3. O código deve compilar normalmente e não deve haver quebras nas referências da interface (FXML) ou do banco de dados.

## Task Breakdown

### Task 1: Renomear Variáveis Locais no `DinossauroDAO` e `DinossauroService` (P1)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `DinossauroDAO.java` e `DinossauroService.java`.
- **OUTPUT:**
    - Em `DinossauroDAO.java`, mudar `d` para `dinossauro` no método `listar`. (Manter `conn`, `pstm`, `rs`).
    - Em `DinossauroService.java`, verificar e ajustar variáveis no lambda de `buscarPorId` e em outros locais.
- **VERIFY:** Nenhuma variável de uma letra, mantendo a compatibilidade do código.

### Task 2: Renomear Variáveis no `MainWindowController` (P1)
- **Agent:** `@frontend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `MainWindowController.java`.
- **OUTPUT:** 
    - Mudar `obs` -> `listaDeDinossaurosDaTabela`. (Evitar nomes genéricos como `observableList` ou `lista`).
    - Mudar `sel` -> `dinossauroSelecionadoParaEdicao` / `dinossauroSelecionadoParaExclusao`.
    - Mudar `novo` -> `novoDinossauroCadastrado`.
    - Mudar `dOpt` -> `dinossauroBuscadoNoBanco`.
    - Mudar `d` -> `dinossauroEmEdicao` (em `handleBuscarEdicao` e `handleSalvarAlteracoes`).
- **VERIFY:** Legibilidade do arquivo Controller, compilação correta.

---

## ✅ PHASE X: VERIFICATION
- [ ] Compilação: O código compila sem erros.
- [ ] Linting & Qualidade: O código atende a regra de nomes intuitivos (sem single-letters), preservando as abreviações consagradas do mercado.
- [ ] Testes Manuais: Rodar a listagem, edição, deleção e adição pelo app.
