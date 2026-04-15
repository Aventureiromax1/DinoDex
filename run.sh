#!/bin/bash
cd "$(dirname "$0")"
mkdir -p build/classes
javac -d build/classes -cp postgresql-42.7.10.jar \
  src/model/Conexao.java \
  src/model/dto/DinosaurDTO.java \
  src/model/dao/DinosaurDAO.java \
  src/dinossauro/Dinossauro.java 2>/dev/null
java -cp "build/classes:postgresql-42.7.10.jar" dinossauro.Dinossauro
