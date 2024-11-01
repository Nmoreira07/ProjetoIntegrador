package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import br.com.nojos.model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-prod")
public class Createnojosservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodDesc = req.getParameter("prod-desc");
        String prodMarca = req.getParameter("prod-marca");
        String prodValorStr = req.getParameter("prod-valor");

        Double prodValor = null;

        if (prodValorStr != null && !prodValorStr.isEmpty()) {
            try {
                prodValor = Double.parseDouble(prodValorStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (prodValor != null) {
            Produto prod = new Produto(null, prodDesc, prodMarca, prodValor); // O ID será gerado pelo banco
            NojosDao nojos = new NojosDao();

            nojos.createProduto(prod);
        } else {
            System.out.println("Valor inválido, produto não será criado.");
        }

        resp.sendRedirect("/findAllprodutos");
    }
}
