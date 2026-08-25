package validator;

import model.dto.DinossauroDTO;
import java.util.List;

/**
 * Facade validator for DinossauroDTO kept for backward compatibility.
 * Internally composes smaller, single-responsibility validators.
 */
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
        CompostoValidador<String> composto = new CompostoValidador<>(List.of(
                new CampoObrigatorioValidador(),
                new TamanhoValidador(NOME_MINIMO, NOME_MAXIMO)
        ));
        boolean ok = composto.validar(nome);
        if (!ok) mensagemErro = composto.obterMensagemErro().replace("Campo", "Nome do dinossauro");
        return ok;
    }

    private boolean validarEspecie(String especie) {
        CompostoValidador<String> composto = new CompostoValidador<>(List.of(
                new CampoObrigatorioValidador(),
                new TamanhoValidador(ESPECIE_MINIMO, ESPECIE_MAXIMO)
        ));
        boolean ok = composto.validar(especie);
        if (!ok) mensagemErro = composto.obterMensagemErro().replace("Campo", "Espécie do dinossauro");
        return ok;
    }

    private boolean validarPeso(int peso) {
        RangeValidador r = new RangeValidador(PESO_MINIMO, PESO_MAXIMO);
        boolean ok = r.validar(peso);
        if (!ok) mensagemErro = r.obterMensagemErro().replace("Valor", "Peso");
        return ok;
    }

    private boolean validarAltura(double altura) {
        RangeValidador r = new RangeValidador(ALTURA_MINIMA, ALTURA_MAXIMA);
        boolean ok = r.validar(altura);
        if (!ok) mensagemErro = r.obterMensagemErro().replace("Valor", "Altura do dinossauro");
        return ok;
    }

    private boolean validarComprimento(double comprimento) {
        RangeValidador r = new RangeValidador(COMPRIMENTO_MINIMO, COMPRIMENTO_MAXIMO);
        boolean ok = r.validar(comprimento);
        if (!ok) mensagemErro = r.obterMensagemErro().replace("Valor", "Comprimento do dinossauro");
        return ok;
    }

    private boolean validarComportamento(String comportamento) {
        CompostoValidador<String> composto = new CompostoValidador<>(List.of(
                new CampoObrigatorioValidador(),
                new TamanhoValidador(COMPORTAMENTO_MINIMO, COMPORTAMENTO_MAXIMO)
        ));
        boolean ok = composto.validar(comportamento);
        if (!ok) mensagemErro = composto.obterMensagemErro().replace("Campo", "Comportamento do dinossauro");
        return ok;
    }

    public String obterMensagemErro() {
        return mensagemErro;
    }
}
