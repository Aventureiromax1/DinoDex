package validator;

public interface ValidadorGenerico<T> {
    boolean validar(T t);
    String obterMensagemErro();
}
