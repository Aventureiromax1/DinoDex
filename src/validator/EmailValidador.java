package validator;

import java.util.regex.Pattern;

public class EmailValidador implements ValidadorGenerico<String> {
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private String mensagemErro;

    @Override
    public boolean validar(String email) {
        if (email == null || email.trim().isEmpty()) {
            mensagemErro = "Email é obrigatório.";
            return false;
        }
        if (!EMAIL.matcher(email.trim()).matches()) {
            mensagemErro = "Email inválido.";
            return false;
        }
        return true;
    }

    @Override
    public String obterMensagemErro() {
        return mensagemErro;
    }
}
