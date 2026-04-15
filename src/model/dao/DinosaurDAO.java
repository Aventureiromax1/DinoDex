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
import model.dto.DinosaurDTO;

public class DinosaurDAO {
    private static final Logger LOGGER = Logger.getLogger(DinosaurDAO.class.getName());

    // 1. CREATE - Inserir um novo dinossauro
    public void inserir(DinosaurDTO personagem) {
        String sql = "INSERT INTO personagens (Nome, Raca, Peso, Altura, Comprimento, Comportamento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, personagem.getNome());
            pstm.setString(2, personagem.getRaca());
            pstm.setInt(3, personagem.getPeso());
            pstm.setDouble(4, personagem.getAltura());
            pstm.setDouble(5, personagem.getComprimento());
            pstm.setString(6, personagem.getAlinhamento());

            pstm.execute();
            System.out.println("Sucesso! Dinossauro inserido.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir", e);
        }
    }

    // 2. READ - Listar todos os dinossauros
    public List<DinosaurDTO> listar() {
        String sql = "SELECT * FROM personagens ORDER BY id";
        List<DinosaurDTO> listapersonagens = new ArrayList<>();

        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DinosaurDTO personagem = new DinosaurDTO();
                personagem.setId(rs.getInt("id"));
                personagem.setNome(rs.getString("nome"));
                personagem.setRaca(rs.getString("raca"));
                personagem.setPeso(rs.getInt("peso"));
                personagem.setAltura(rs.getDouble("altura"));
                personagem.setComprimento(rs.getDouble("comprimento"));
                personagem.setAlinhamento(rs.getString("comportamento"));
                personagem.setDataCriacao(rs.getTimestamp("data_criacao"));

                listapersonagens.add(personagem);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar", e);
        }
        return listapersonagens;
    }

    // 3. UPDATE - Alterar um dinossauro existente
    public void alterar(DinosaurDTO personagem) {
        String sql = "UPDATE personagens SET Nome=?, Raca=?, Peso=?, Altura=?, Comprimento=?, Comportamento=? WHERE Id=?";
        
        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, personagem.getNome());
            pstm.setString(2, personagem.getRaca());
            pstm.setInt(3, personagem.getPeso());
            pstm.setDouble(4, personagem.getAltura());
            pstm.setDouble(5, personagem.getComprimento());
            pstm.setString(6, personagem.getAlinhamento());
            pstm.setInt(7, personagem.getId());

            pstm.execute();
            System.out.println("Sucesso! Dinossauro alterado.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao alterar", e);
        }
    }

    // 4. DELETE - Excluir um dinossauro pelo ID
    public void excluir(int id) {
        String sql = "DELETE FROM personagens WHERE Id=?";
        
        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            
            pstm.execute();
            System.out.println("Sucesso! Dinossauro excluído.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir", e);
        }
    }
}
