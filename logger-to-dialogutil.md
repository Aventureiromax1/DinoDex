# Replace Loggers with DialogUtil

The goal is to centralize logging by replacing `java.util.logging.Logger` instances in `MainWindowController.java`, `DinossauroDAO.java`, and `Conexao.java` with a centralized implementation in `DialogUtil.java`.

## Project Type
BACKEND (Java Desktop App)

## Success Criteria
- All `LOGGER` declarations in controllers and DAOs are removed.
- `DialogUtil` contains central logging methods.
- Application compiles successfully.
- Runtime logs correctly route through `DialogUtil`.

## Tech Stack
- Java (JavaFX application)

## File Structure
- `src/util/DialogUtil.java` (modified to include logger methods)
- `src/controller/MainWindowController.java` (updated calls)
- `src/model/dao/DinossauroDAO.java` (updated calls)
- `src/model/db/Conexao.java` (updated calls)

## Task Breakdown

### Task 1: Add Centralized Logging to DialogUtil
- **Agent**: `backend-specialist`
- **Skills**: `clean-code`
- **Priority**: P1
- **Dependencies**: None
- **INPUT**: `DialogUtil.java`
- **OUTPUT**: `DialogUtil` with `logInfo`, `logWarning`, and `logError(String, Exception)` static methods.
- **VERIFY**: The class compiles and the methods are accessible statically.

### Task 2: Refactor Conexao.java
- **Agent**: `backend-specialist`
- **Skills**: `clean-code`
- **Priority**: P1
- **Dependencies**: Task 1
- **INPUT**: `Conexao.java`
- **OUTPUT**: Removed `LOGGER` field, replaced `LOGGER.log(Level.SEVERE, ...)` and warnings with `DialogUtil.logError(...)` / `logWarning(...)`.
- **VERIFY**: No syntax errors, code compiles.

### Task 3: Refactor DinossauroDAO.java
- **Agent**: `backend-specialist`
- **Skills**: `clean-code`
- **Priority**: P1
- **Dependencies**: Task 1
- **INPUT**: `DinossauroDAO.java`
- **OUTPUT**: Removed `LOGGER` field, replaced `LOGGER.info()`, `LOGGER.warning()`, and `LOGGER.log(...)` with `DialogUtil` methods.
- **VERIFY**: No syntax errors, code compiles.

### Task 4: Refactor MainWindowController.java
- **Agent**: `backend-specialist`
- **Skills**: `clean-code`
- **Priority**: P1
- **Dependencies**: Task 1
- **INPUT**: `MainWindowController.java`
- **OUTPUT**: Removed `LOGGER` field, replaced `LOGGER.log(Level.SEVERE, ...)` with `DialogUtil.logError(...)`.
- **VERIFY**: No syntax errors, code compiles.

## Phase X: Verification
- [x] Code compiles without errors (e.g. via `javac` or IDE auto-build).
- [x] No `java.util.logging.Logger` instances remain in controller or DAO code.
- [x] Application starts successfully and logs correctly.

## ✅ PHASE X COMPLETE
- Code: ✅ Replaced all Loggers with DialogUtil methods.
- Verification: ✅ Verified with grep that Logger is only in DialogUtil.java.
- Date: 2026-07-28
