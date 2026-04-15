package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Conexao;
import model.dto.DinossauroDTO;

/**
 * DAO que gerencia operações CRUD para dinossauros.
 */
public class DinossauroDAO {

    private static final Logger LOGGER = Logger.getLogger(DinossauroDAO.class.getName());

    // CREATE
    public void inserir(DinossauroDTO dinossauro) {
        String sql = "INSERT INTO personagens (nome, raca, peso, altura, comprimento, comportamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, dinossauro.getNome());
            pstm.setString(2, dinossauro.getEspecie());
            pstm.setInt(3, dinossauro.getPeso());
            pstm.setDouble(4, dinossauro.getAltura());
            pstm.setDouble(5, dinossauro.getComprimento());
            pstm.setString(6, dinossauro.getComportamento());

            pstm.executeUpdate();
            System.out.println("Sucesso: dinossauro inserido.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir dinossauro.", e);
            System.err.println("Erro ao inserir dinossauro: " + e.getMessage());
        }
    }

    // READ
    public List<DinossauroDTO> listar() {
        String sql = "SELECT * FROM personagens ORDER BY id";
        List<DinossauroDTO> listaDinossauros = new ArrayList<>();

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DinossauroDTO d = new DinossauroDTO();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                d.setEspecie(rs.getString("raca"));
                d.setPeso(rs.getInt("peso"));
                d.setAltura(rs.getDouble("altura"));
                d.setComprimento(rs.getDouble("comprimento"));
                d.setComportamento(rs.getString("comportamento"));
                d.setDataCriacao(rs.getTimestamp("data_criacao"));

                listaDinossauros.add(d);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar dinossauros.", e);
            System.err.println("Erro ao listar dinossauros: " + e.getMessage());
        }
        return listaDinossauros;
    }

    // UPDATE
    public void alterar(DinossauroDTO dinossauro) {
        String sql = "UPDATE personagens SET nome=?, raca=?, peso=?, altura=?, comprimento=?, comportamento=? WHERE id=?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, dinossauro.getNome());
            pstm.setString(2, dinossauro.getEspecie());
            pstm.setInt(3, dinossauro.getPeso());
            pstm.setDouble(4, dinossauro.getAltura());
            pstm.setDouble(5, dinossauro.getComprimento());
            pstm.setString(6, dinossauro.getComportamento());
            pstm.setInt(7, dinossauro.getId());

            int updated = pstm.executeUpdate();
            if (updated > 0) {
                System.out.println("Sucesso: dinossauro alterado.");
            } else {
                System.out.println("Atenção: nenhum registro foi alterado (ID não encontrado).");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao alterar dinossauro.", e);
            System.err.println("Erro ao alterar dinossauro: " + e.getMessage());
        }
    }

    // DELETE
    public void excluir(int id) {
        String sql = "DELETE FROM personagens WHERE id=?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            int deleted = pstm.executeUpdate();
            if (deleted > 0) {
                System.out.println("Sucesso: dinossauro excluído.");
            } else {
                System.out.println("Atenção: nenhum registro foi excluído (ID não encontrado).");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir dinossauro.", e);
            System.err.println("Erro ao excluir dinossauro: " + e.getMessage());
        }
    }
}
