package br.com.nojos.dao;

import br.com.nojos.model.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NojosDao {

    public void createProduto(Produto prod) {
        String SQL = "INSERT INTO PRODUTO (DESCRICAO, VALOR, MARCA) VALUES (?, ?, ?)";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, prod.getDesc());
            preparedStatement.setDouble(2, prod.getValor());
            preparedStatement.setString(3, prod.getMarca());
            preparedStatement.execute();

            System.out.println("success in insert produto");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

        }

    }

}
