package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por criar conexões com o banco de dados.
 */
public class Conexao {

    private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());

    private static final String URL = "jdbc:postgresql://localhost:5432/dinossauros";
    private static final String USUARIO = "leo";
    private static final String SENHA = "leo260908";

    /**
     * Abre e retorna uma nova conexão com o banco de dados.
     * @return Connection ativa
     */
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver PostgreSQL não encontrado.", e);
            throw new RuntimeException("Driver PostgreSQL não encontrado.", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco de dados.", e);
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }
}
