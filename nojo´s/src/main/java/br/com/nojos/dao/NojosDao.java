package br.com.nojos.dao;

import br.com.nojos.model.Produto;
import br.com.nojos.model.Login;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NojosDao {
    private final DataSource dataSource;

    public NojosDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initDatabase() {
        String createProdutoTable = """
        CREATE TABLE IF NOT EXISTS PRODUTO (
            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
            DESCRICAO VARCHAR(255) NOT NULL,
            MARCA VARCHAR(100) NOT NULL,
            VALOR DOUBLE NOT NULL
        )
        """;

        String createUsuarioTable = """
        CREATE TABLE IF NOT EXISTS USUARIO (
            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
            EMAIL VARCHAR(255) UNIQUE NOT NULL,
            SENHA VARCHAR(255) NOT NULL
        )
        """;

        String createFavoritosTable = """
        CREATE TABLE IF NOT EXISTS FAVORITOS (
            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
            USER_ID BIGINT NOT NULL,
            PRODUTO_ID BIGINT NOT NULL,
            FOREIGN KEY (USER_ID) REFERENCES USUARIO(ID),
            FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID)
        )
        """;

        String createCarrinhoTable = """
        CREATE TABLE IF NOT EXISTS CARRINHO (
            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
            PRODUTO_ID BIGINT NOT NULL,
            FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID)
        )
        """;

        String insertDefaultUser = """
        INSERT INTO USUARIO (ID, EMAIL, SENHA)
        SELECT 1, 'teste@teste.com', '123456'
        WHERE NOT EXISTS (SELECT 1 FROM USUARIO WHERE ID = 1)
        """;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement psProduto = connection.prepareStatement(createProdutoTable)) {
                psProduto.execute();
            }

            try (PreparedStatement psUsuario = connection.prepareStatement(createUsuarioTable)) {
                psUsuario.execute();
            }

            try (PreparedStatement psFavoritos = connection.prepareStatement(createFavoritosTable)) {
                psFavoritos.execute();
            }

            try (PreparedStatement psCarrinho = connection.prepareStatement(createCarrinhoTable)) {
                psCarrinho.execute();
            }

            try (PreparedStatement psInsertUser = connection.prepareStatement(insertDefaultUser)) {
                psInsertUser.execute();
            }

            System.out.println("Banco de dados inicializado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public void createProduto(Produto prod) {
        String SQL = "INSERT INTO PRODUTO (DESCRICAO, VALOR, MARCA) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, prod.getDesc());
            preparedStatement.setDouble(2, prod.getValor());
            preparedStatement.setString(3, prod.getMarca());
            preparedStatement.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public Produto findProdutoById(Long id) {
        String SQL = "SELECT * FROM PRODUTO WHERE ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                            rs.getLong("ID"),
                            rs.getString("DESCRICAO"),
                            rs.getString("MARCA"),
                            rs.getDouble("VALOR")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID: " + e.getMessage());
        }
        return null;
    }

    public List<Produto> findAllProdutos() {
        String SQL = "SELECT * FROM PRODUTO";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Produto> produtos = new ArrayList<>();
            while (resultSet.next()) {
                Produto prod = new Produto(
                        resultSet.getLong("ID"),
                        resultSet.getString("DESCRICAO"),
                        resultSet.getString("MARCA"),
                        resultSet.getDouble("VALOR")
                );
                produtos.add(prod);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        }
    }

    public void deleteProduto(Long id) {
        String SQL = "DELETE FROM PRODUTO WHERE ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage(), e);
        }
    }

    public void addToCarrinho(Long produtoId) {
        String SQL = "INSERT INTO CARRINHO (PRODUTO_ID) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setLong(1, produtoId);
            ps.executeUpdate();
            System.out.println("Produto adicionado ao carrinho com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }

    public List<Produto> getCarrinho() {
        String SQL = """
        SELECT p.* FROM CARRINHO c
        JOIN PRODUTO p ON c.PRODUTO_ID = p.ID
    """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            List<Produto> carrinho = new ArrayList<>();
            while (rs.next()) {
                carrinho.add(new Produto(
                        rs.getLong("ID"),
                        rs.getString("DESCRICAO"),
                        rs.getString("MARCA"),
                        rs.getDouble("VALOR")
                ));
            }
            return carrinho;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos no carrinho: " + e.getMessage());
        }
    }


    public void createUser(Login user) {
        String SQL = "INSERT INTO USUARIO (EMAIL, SENHA) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public void removeFromCarrinho(Long produtoId) {
        String SQL = "DELETE FROM CARRINHO WHERE PRODUTO_ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setLong(1, produtoId);
            ps.executeUpdate();
            System.out.println("Produto removido do carrinho com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover produto do carrinho: " + e.getMessage());
        }
    }


    public boolean validateLogin(String email, String senha) {
        String SQL = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login: " + e.getMessage());
        }
    }
}
