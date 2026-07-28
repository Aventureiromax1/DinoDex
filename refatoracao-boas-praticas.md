# Refatoração com Boas Práticas de Programação

## Overview
O objetivo deste projeto é refatorar a aplicação JavaFX **DinoDex** para aderir a boas práticas de programação, incluindo os princípios SOLID, Clean Code, padrão MVC e convenções de nomenclatura. O sistema atual possui classes com alta responsabilidade (como o `MainWindowController`), além de organização de pacotes que pode ser melhorada para refletir um bom encapsulamento e coesão.

## Project Type
**DESKTOP / BACKEND**
*(Como é uma aplicação JavaFX, trataremos a organização como um projeto com lógica de backend acoplada à UI, sendo responsabilidade principal de um backend-specialist trabalhar em conjunto com as ferramentas Java)*

## Success Criteria
1. **SOLID/SRP:** `MainWindowController` não deve conter lógica de validação complexa, conversão de dados ou instanciar alertas diretamente.
2. **MVC:** A aplicação deve estar claramente dividida em Model (DTO, DAO, Connection), View (FXML, CSS) e Controller. Adição de uma camada de **Service** para regras de negócio.
3. **Clean Code & DRY:** Remoção de código duplicado, criação de métodos utilitários (`DialogUtil`, conversores) e nomenclaturas padronizadas.
4. **Organização:** Classes em pacotes coesos (`app`, `controller`, `util`, `model.dao`, `model.dto`, `model.service`).
5. **Tratamento de Erros:** Exceções tratadas adequadamente e apresentadas ao usuário via alertas customizados e centralizados.

## Tech Stack
- **Java 17+** (Linguagem Principal)
- **JavaFX** (UI Framework)
- **PostgreSQL** (Banco de dados)
- **JUnit 5** (Para testes, caso sejam adicionados posteriormente)

## File Structure
```text
src/
├── app/
│   └── Main.java (antigo Dinossauro.java)
├── controller/
│   └── MainWindowController.java
├── model/
│   ├── dao/
│   │   └── DinossauroDAO.java
│   ├── dto/
│   │   └── DinossauroDTO.java
│   ├── service/
│   │   └── DinossauroService.java
│   └── db/
│       └── Conexao.java
├── util/
│   ├── DialogUtil.java
│   └── ValidationUtil.java
└── view/
    ├── MainWindow.fxml
    └── styles.css
```

## Task Breakdown

### Task 1: Reestruturação de Pacotes e Nomenclatura (P0)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** Código fonte atual em `src/dinossauro` e `src/model`.
- **OUTPUT:** Nova estrutura de diretórios criada (`app`, `controller`, `util`, `view`, `model.service`, `model.db`). Classes movidas e referências atualizadas (imports, FXML controller path).
- **VERIFY:** O projeto compila sem erros de importação e a interface gráfica abre com o novo caminho do FXML.

### Task 2: Criação de Classes Utilitárias para SRP e DRY (P1)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `MainWindowController.java` atual.
- **OUTPUT:** Criação de `util/DialogUtil.java` para gerenciar os alertas (`showAlert`) e `util/ValidationUtil.java` para conversões (como `parseIntSafe`, `parseDoubleSafe`).
- **VERIFY:** Classes criadas. `MainWindowController.java` refatorado para chamar `DialogUtil` e `ValidationUtil` no lugar dos métodos privados repetitivos.

### Task 3: Implementação da Camada de Service (DIP e Coesão) (P1)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `MainWindowController.java` e `DinossauroDAO.java`.
- **OUTPUT:** Criação de `model.service.DinossauroService.java`. Esta classe intermediará chamadas do Controller para o DAO, centralizando possíveis regras de negócio.
- **VERIFY:** O controller instancia (ou recebe) `DinossauroService` em vez de usar `DinossauroDAO` diretamente.

### Task 4: Refatoração do Controller (Clean Code) (P2)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `MainWindowController.java`
- **OUTPUT:** Remoção de lógicas de negócio do Controller. Métodos devem ser enxutos (apenas capturar da UI, delegar para o Service e notificar a UI). Adição de comentários apenas onde for estritamente necessário (documentação de classe).
- **VERIFY:** Revisão visual do arquivo. O Controller deve ter reduzido significativamente de tamanho e complexidade.

### Task 5: Tratamento de Exceções Aprimorado (Qualidade de Software) (P2)
- **Agent:** `@backend-specialist`
- **Skill:** `clean-code`
- **INPUT:** `DinossauroDAO.java` e `Conexao.java`
- **OUTPUT:** Garantir que exceções de SQL (SQL Exceptions) não vazem sem log apropriado. Substituição de print stack traces no console por `java.util.logging.Logger` ou logs encapsulados.
- **VERIFY:** Loggers configurados em todas as camadas necessárias, capturando exceções e retornando mensagens amigáveis ao invés de stack traces crús na tela do usuário.

---

## ✅ PHASE X: VERIFICATION
- [ ] Compilação: O código compila sem erros.
- [ ] Linting & Qualidade: O código segue os padrões do `clean-code` (DRY, KISS, sem getters inúteis sem uso, baixo acoplamento).
- [ ] MVC e SRP: Validar que `MainWindowController` não tem lógica de negócio ou query de banco de dados.
- [ ] Execução: Iniciar `app.Main` e realizar um fluxo completo (Listar, Adicionar, Editar, Deletar). Tudo deve funcionar como antes (Refatoração concluída).
