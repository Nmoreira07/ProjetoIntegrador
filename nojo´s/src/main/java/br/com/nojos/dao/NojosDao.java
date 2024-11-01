package br.com.nojos.dao;

import br.com.nojos.model.Produto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NojosDao {
    private final DataSource dataSource;

    public NojosDao(DataSource dataSource) {
        this.dataSource = dataSource;
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
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> findAllProdutos() {
        String SQL = "SELECT * FROM PRODUTO";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String prodDesc = resultSet.getString("DESCRICAO");
                String prodMarca = resultSet.getString("MARCA");
                Double prodValor = resultSet.getDouble("VALOR");

                Produto prod = new Produto(id, prodDesc, prodMarca, prodValor);
                produtos.add(prod);
            }

            System.out.println("Produtos selecionados com sucesso!");
            return produtos;

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar produtos: " + e.getMessage());
            return Collections.emptyList();
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
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
