package model.service;

import model.dao.DinossauroDAO;
import model.dto.DinossauroDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DinossauroService {

    private final DinossauroDAO dao;

    public DinossauroService() {
        this.dao = new DinossauroDAO();
    }

    public void salvar(DinossauroDTO dinossauro) throws SQLException {
        if (dinossauro == null) {
            throw new IllegalArgumentException("Dinossauro não pode ser nulo");
        }
        dao.inserir(dinossauro);
    }

    public List<DinossauroDTO> listarTodos() throws SQLException {
        return dao.listar();
    }

    public void atualizar(DinossauroDTO dinossauro) throws SQLException {
        if (dinossauro == null || dinossauro.getId() <= 0) {
            throw new IllegalArgumentException("Dinossauro inválido para atualização");
        }
        dao.alterar(dinossauro);
    }

    public void excluir(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        dao.excluir(id);
    }

    public void reorganizarIds() throws SQLException {
        dao.reorganizarIds();
    }

    public Optional<DinossauroDTO> buscarPorId(int id) throws SQLException {
        return dao.listar().stream()
                .filter(d -> d.getId() == id)
                .findFirst();
    }
}
