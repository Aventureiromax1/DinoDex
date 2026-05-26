#!/usr/bin/env bash
cd "$(dirname "$0")"
mkdir -p build/classes

# Collect Java sources
JAVA_SOURCES=$(find src -name "*.java")
echo "Fontes a compilar:"
echo "$JAVA_SOURCES"

# Require PATH_TO_FX to run JavaFX apps. If not set, show instructions and exit.
if [ -z "$PATH_TO_FX" ]; then
  cat <<'EOF'
Erro: variável de ambiente PATH_TO_FX não definida. Este projeto usa JavaFX e precisa do SDK JavaFX
no module-path para compilar/executar.

Como instalar/rodar (macOS):
1) Instale o JavaFX via Homebrew:
   brew install openjfx
2) Exporte a variável PATH_TO_FX (um exemplo):
   export PATH_TO_FX="$(brew --prefix openjfx)/libexec/openjfx/lib"
3) Rode este script novamente:
   ./run.sh

Ou compile/execute manualmente usando o module-path:
   javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -d build/classes -cp postgresql-42.7.10.jar $JAVA_SOURCES
   java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp "build/classes:postgresql-42.7.10.jar" dinossauro.Dinossauro

Se você já tem o JavaFX no classpath de outra forma, exporte PATH_TO_FX apontando para o diretório lib do JavaFX.
EOF
  exit 1
fi

echo "Compilando com JavaFX (module-path: $PATH_TO_FX)"
javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -d build/classes -cp postgresql-42.7.10.jar $JAVA_SOURCES

echo "Executando aplicação..."
java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp "build/classes:postgresql-42.7.10.jar" dinossauro.Dinossauro
