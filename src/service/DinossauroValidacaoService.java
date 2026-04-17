package service;

import model.dto.DinossauroDTO;
import validator.Validador;

public class DinossauroValidacaoService {

    private final Validador validador;

    public DinossauroValidacaoService() {
        this.validador = new Validador();
    }

    public DinossauroValidacaoService(Validador validador) {
        this.validador = validador;
    }

    public boolean validar(DinossauroDTO dino) {
        return validador.validar(dino);
    }

    public String obterMensagemErro(DinossauroDTO dino) {
        if (validador.validar(dino)) {
            return null;
        }
        return validador.obterMensagemErro();
    }
}
