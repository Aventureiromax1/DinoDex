package validator;

import model.dto.DinossauroDTO;

public class Validador {

    private static final int NOME_MINIMO = 3;
    private static final int NOME_MAXIMO = 100;
    private static final int ESPECIE_MINIMO = 3;
    private static final int ESPECIE_MAXIMO = 100;
    private static final int PESO_MINIMO = 1;
    private static final int PESO_MAXIMO = 10000000;
    private static final double ALTURA_MINIMA = 0.1;
    private static final double ALTURA_MAXIMA = 1000.0;
    private static final double COMPRIMENTO_MINIMO = 0.1;
    private static final double COMPRIMENTO_MAXIMO = 150.0;
    private static final int COMPORTAMENTO_MINIMO = 3;
    private static final int COMPORTAMENTO_MAXIMO = 100;

    private String mensagemErro;

    public boolean validar(DinossauroDTO dino) {
        if (dino == null) {
            mensagemErro = "Dinossauro não pode ser nulo.";
            return false;
        }

        if (!validarNome(dino.getNome())) {
            return false;
        }

        if (!validarEspecie(dino.getEspecie())) {
            return false;
        }

        if (!validarPeso(dino.getPeso())) {
            return false;
        }

        if (!validarAltura(dino.getAltura())) {
            return false;
        }

        if (!validarComprimento(dino.getComprimento())) {
            return false;
        }

        if (!validarComportamento(dino.getComportamento())) {
            return false;
        }

        return true;
    }

    private boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            mensagemErro = "Nome do dinossauro é obrigatório.";
            return false;
        }
        if (nome.trim().length() < NOME_MINIMO) {
            mensagemErro = "Nome deve ter pelo menos " + NOME_MINIMO + " caracteres.";
            return false;
        }
        if (nome.trim().length() > NOME_MAXIMO) {
            mensagemErro = "Nome não pode exceder " + NOME_MAXIMO + " caracteres.";
            return false;
        }
        return true;
    }

    private boolean validarEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            mensagemErro = "Espécie do dinossauro é obrigatória.";
            return false;
        }
        if (especie.trim().length() < ESPECIE_MINIMO) {
            mensagemErro = "Espécie deve ter pelo menos " + ESPECIE_MINIMO + " caracteres.";
            return false;
        }
        if (especie.trim().length() > ESPECIE_MAXIMO) {
            mensagemErro = "Espécie não pode exceder " + ESPECIE_MAXIMO + " caracteres.";
            return false;
        }
        return true;
    }

    private boolean validarPeso(int peso) {
        if (peso < PESO_MINIMO) {
            mensagemErro = "Peso deve ser maior ou igual a " + PESO_MINIMO + ".";
            return false;
        }
        if (peso > PESO_MAXIMO) {
            mensagemErro = "Peso não pode exceder " + PESO_MAXIMO + ".";
            return false;
        }
        return true;
    }

    private boolean validarAltura(double altura) {
        if (altura < ALTURA_MINIMA) {
            mensagemErro = "Altura deve ser maior ou igual a " + ALTURA_MINIMA + ".";
            return false;
        }
        if (altura > ALTURA_MAXIMA) {
            mensagemErro = "Altura não pode exceder " + ALTURA_MAXIMA + ".";
            return false;
        }
        return true;
    }

    private boolean validarComprimento(double comprimento) {
        if (comprimento < COMPRIMENTO_MINIMO) {
            mensagemErro = "Comprimento deve ser maior ou igual a " + COMPRIMENTO_MINIMO + ".";
            return false;
        }
        if (comprimento > COMPRIMENTO_MAXIMO) {
            mensagemErro = "Comprimento não pode exceder " + COMPRIMENTO_MAXIMO + ".";
            return false;
        }
        return true;
    }

    private boolean validarComportamento(String comportamento) {
        if (comportamento == null || comportamento.trim().isEmpty()) {
            mensagemErro = "Comportamento do dinossauro é obrigatório.";
            return false;
        }
        if (comportamento.trim().length() < COMPORTAMENTO_MINIMO) {
            mensagemErro = "Comportamento deve ter pelo menos " + COMPORTAMENTO_MINIMO + " caracteres.";
            return false;
        }
        if (comportamento.trim().length() > COMPORTAMENTO_MAXIMO) {
            mensagemErro = "Comportamento não pode exceder " + COMPORTAMENTO_MAXIMO + " caracteres.";
            return false;
        }
        return true;
    }

    public String obterMensagemErro() {
        return mensagemErro;
    }
}
