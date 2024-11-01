package br.com.nojos.dao;

import br.com.nojos.model.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NojosDao {

    public void createProduto(Produto prod) {
        String SQL = "INSERT INTO PRODUTO (DESCRICAO, VALOR, MARCA) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, prod.getDesc());
            preparedStatement.setDouble(2, prod.getValor());
            preparedStatement.setString(3, prod.getMarca());
            preparedStatement.executeUpdate();

            System.out.println("Produto inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> findAllProdutos() {
        String SQL = "SELECT * FROM PRODUTO";

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("ID"); // Certifique-se de que a coluna ID existe na tabela
                String prodDesc = resultSet.getString("DESCRICAO");
                String prodMarca = resultSet.getString("MARCA");
                Double prodValor = resultSet.getDouble("VALOR");

                Produto prod = new Produto(id, prodDesc, prodMarca, prodValor);
                produtos.add(prod);
            }

            System.out.println("Produtos selecionados com sucesso!");
            return produtos;

        } catch (Exception e) {
            System.out.println("Erro ao selecionar produtos: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
