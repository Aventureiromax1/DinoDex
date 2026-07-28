package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.Conexao;
import model.dto.DinossauroDTO;
import util.DialogUtil;

public class DinossauroDAO {

    public void inserir(DinossauroDTO dinosaur) throws SQLException {
        String sql = "INSERT INTO public.dinossauro (nome, especie, peso, altura, comprimento, comportamento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, dinosaur.getNome());
            pstm.setString(2, dinosaur.getEspecie());
            pstm.setInt(3, dinosaur.getPeso());
            pstm.setDouble(4, dinosaur.getAltura());
            pstm.setDouble(5, dinosaur.getComprimento());
            pstm.setString(6, dinosaur.getComportamento());

            pstm.executeUpdate();
            DialogUtil.logInfo("Sucesso: dinosaur inserido.");
        } catch (SQLException e) {
            DialogUtil.logError("Erro ao inserir dinosaur.", e);
            throw e;
        }
    }

    public List<DinossauroDTO> listar() throws SQLException {
        String sql = "SELECT * FROM public.dinossauro ORDER BY id";
        List<DinossauroDTO> listaDinossauros = new ArrayList<>();

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DinossauroDTO d = new DinossauroDTO();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                d.setEspecie(rs.getString("especie"));
                d.setPeso(rs.getInt("peso"));
                d.setAltura(rs.getDouble("altura"));
                d.setComprimento(rs.getDouble("comprimento"));
                d.setComportamento(rs.getString("comportamento"));
                d.setDataCriacao(rs.getTimestamp("data_criacao"));

                listaDinossauros.add(d);
            }
        } catch (SQLException e) {
            DialogUtil.logError("Erro ao listar dinossauros.", e);
            throw e;
        }
        return listaDinossauros;
    }

    public void alterar(DinossauroDTO dinossauro) throws SQLException {
        String sql = "UPDATE public.dinossauro SET nome=?, especie=?, peso=?, altura=?, comprimento=?, comportamento=? WHERE id=?";

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
                DialogUtil.logInfo("Sucesso: dinossauro alterado.");
            } else {
                DialogUtil.logWarning("Atenção: nenhum registro foi alterado (ID não encontrado).");
            }
        } catch (SQLException e) {
            DialogUtil.logError("Erro ao alterar dinossauro.", e);
            throw e;
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM public.dinossauro WHERE id=?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            int deleted = pstm.executeUpdate();
            if (deleted > 0) {
                DialogUtil.logInfo("Sucesso: dinossauro excluído.");
            } else {
                DialogUtil.logWarning("Atenção: nenhum registro foi excluído (ID não encontrado).");
            }
        } catch (SQLException e) {
            DialogUtil.logError("Erro ao excluir dinossauro.", e);
            throw e;
        }
    }
    //cria uma 2° tabela, importa os dados, exclui a antiga e renomeia a nova para o nome da antiga
    public void reorganizarIds() throws SQLException {
        String createNewTable = "CREATE TABLE public.dinossauro_new (" +
                                "id SERIAL PRIMARY KEY, " +
                                "nome VARCHAR(255) NOT NULL, " +
                                "especie VARCHAR(255), " +
                                "peso INTEGER, " +
                                "altura DOUBLE PRECISION, " +
                                "comprimento DOUBLE PRECISION, " +
                                "comportamento TEXT, " +
                                "data_criacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP" +
                                ");";
        String copyData = "INSERT INTO public.dinossauro_new (nome, especie, peso, altura, comprimento, comportamento, data_criacao) " +
                          "SELECT nome, especie, peso, altura, comprimento, comportamento, data_criacao FROM public.dinossauro ORDER BY id;";
        String dropOldTable = "DROP TABLE public.dinossauro;";
        String renameNewTable = "ALTER TABLE public.dinossauro_new RENAME TO dinossauro;";
        String renameSequence = "ALTER SEQUENCE public.dinossauro_new_id_seq RENAME TO dinossauro_id_seq;";

        Connection conn = null;
        try {
            conn = new Conexao().conectar();
            Statement stmt = conn.createStatement();
            
            conn.setAutoCommit(false);

            stmt.execute(createNewTable);
            stmt.execute(copyData);
            stmt.execute(dropOldTable);
            stmt.execute(renameNewTable);
            stmt.execute(renameSequence);

            conn.commit();
            DialogUtil.logInfo("Sucesso: IDs da tabela dinossauro reorganizados.");
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    DialogUtil.logWarning("Transação de reorganização de IDs foi revertida.", e);
                } catch (SQLException ex) {
                    DialogUtil.logError("Erro ao reverter transação.", ex);
                }
            }
            DialogUtil.logError("Erro ao reorganizar IDs.", e);
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    DialogUtil.logError("Erro ao fechar conexão.", e);
                }
            }
        }
    }
}