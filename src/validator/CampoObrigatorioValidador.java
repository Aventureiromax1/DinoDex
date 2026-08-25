package validator;

public class CampoObrigatorioValidador implements ValidadorGenerico<String> {
    private String mensagemErro;

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            mensagemErro = "Campo é obrigatório.";
            return false;
        }
        return true;
    }

    @Override
    public String obterMensagemErro() {
        return mensagemErro;
    }
}
