package validator;

public class TamanhoValidador implements ValidadorGenerico<String> {
    private final int min;
    private final int max;
    private String mensagemErro;

    public TamanhoValidador(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validar(String s) {
        if (s == null) {
            mensagemErro = "Valor é obrigatório.";
            return false;
        }
        int len = s.trim().length();
        if (len < min) {
            mensagemErro = "Deve ter pelo menos " + min + " caracteres.";
            return false;
        }
        if (len > max) {
            mensagemErro = "Não pode exceder " + max + " caracteres.";
            return false;
        }
        return true;
    }

    @Override
    public String obterMensagemErro() {
        return mensagemErro;
    }
}
