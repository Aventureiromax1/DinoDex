package validator;

import java.util.List;

public class CompostoValidador<T> implements ValidadorGenerico<T> {
    private final List<ValidadorGenerico<T>> validadores;
    private String mensagemErro;

    public CompostoValidador(List<ValidadorGenerico<T>> validadores) {
        this.validadores = validadores;
    }

    @Override
    public boolean validar(T t) {
        for (ValidadorGenerico<T> v : validadores) {
            if (!v.validar(t)) {
                mensagemErro = v.obterMensagemErro();
                return false;
            }
        }
        return true;
    }

    @Override
    public String obterMensagemErro() {
        return mensagemErro;
    }
}
