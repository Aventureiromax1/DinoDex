package service;

import model.dao.DinossauroDAO;
import model.dto.DinossauroDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DinossauroService {

    private final DinossauroDAO dinossauroDAO;
    private final DinossauroValidacaoService validacaoService;

    public DinossauroService() {
        this.dinossauroDAO = new DinossauroDAO();
        this.validacaoService = new DinossauroValidacaoService();
    }

    public DinossauroService(DinossauroDAO dao, DinossauroValidacaoService validacao) {
        this.dinossauroDAO = dao;
        this.validacaoService = validacao;
    }

    public boolean cadastrar(DinossauroDTO dino) throws IllegalArgumentException, SQLException {
        if (!validacaoService.validar(dino)) {
            throw new IllegalArgumentException(validacaoService.obterMensagemErro(dino));
        }
        dinossauroDAO.inserir(dino);
        return true;
    }

    public boolean atualizar(DinossauroDTO dino) throws IllegalArgumentException {
        if (!validacaoService.validar(dino)) {
            throw new IllegalArgumentException(validacaoService.obterMensagemErro(dino));
        }
        dinossauroDAO.alterar(dino);
        return true;
    }

    public boolean excluir(int id) {
        dinossauroDAO.excluir(id);
        return true;
    }

    public List<DinossauroDTO> listar() {
        return dinossauroDAO.listar();
    }

    public Optional<DinossauroDTO> buscarPorId(int id) {
        List<DinossauroDTO> lista = dinossauroDAO.listar();
        return lista.stream().filter(d -> d.getId() == id).findFirst();
    }

    public void reorganizarIds() throws SQLException {
        dinossauroDAO.reorganizarIds();
    }
}
