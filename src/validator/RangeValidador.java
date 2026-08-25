package validator;

public class RangeValidador implements ValidadorGenerico<Number> {
    private final double min;
    private final double max;
    private String mensagemErro;

    public RangeValidador(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validar(Number n) {
        if (n == null) {
            mensagemErro = "Valor é obrigatório.";
            return false;
        }
        double v = n.doubleValue();
        if (v < min) {
            mensagemErro = "Deve ser maior ou igual a " + min + ".";
            return false;
        }
        if (v > max) {
            mensagemErro = "Não pode exceder " + max + ".";
            return false;
        }
        return true;
    }

    @Override
    public String obterMensagemErro() {
        return mensagemErro;
    }
}
