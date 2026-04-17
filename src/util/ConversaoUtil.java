package util;

public class ConversaoUtil {

    private ConversaoUtil() {
    }

    public static int converterParaInteiro(String valor) throws NumberFormatException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NumberFormatException("Valor não pode ser vazio.");
        }
        return Integer.parseInt(valor.trim());
    }

    public static double converterParaDouble(String valor) throws NumberFormatException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NumberFormatException("Valor não pode ser vazio.");
        }
        return Double.parseDouble(valor.trim());
    }

    public static int converterParaInteiroSeguro(String valor, int padrao) {
        try {
            return converterParaInteiro(valor);
        } catch (NumberFormatException e) {
            return padrao;
        }
    }

    public static double converterParaDoubleSeguro(String valor, double padrao) {
        try {
            return converterParaDouble(valor);
        } catch (NumberFormatException e) {
            return padrao;
        }
    }
}
