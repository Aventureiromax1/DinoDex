package util;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean estaVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static boolean naoEstaVazio(String valor) {
        return !estaVazio(valor);
    }

    public static String saneizar(String valor) {
        if (estaVazio(valor)) {
            return "";
        }
        return valor.trim();
    }

    public static String normalizarEspacos(String valor) {
        if (estaVazio(valor)) {
            return "";
        }
        return valor.trim().replaceAll("\\s+", " ");
    }
}
