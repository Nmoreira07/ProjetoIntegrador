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
public class Createcarservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prodDesc = req.getParameter("prod-desc");
        String prodMarca = req.getParameter("prod-marca");
        String prodValor = req.getParameter("prod-valor");

        System.out.println(prodDesc);
        System.out.println(prodMarca);
        System.out.println(prodValor);
        req.getRequestDispatcher("index.html").forward(req, resp);

        Produto prod = new Produto();
        NojosDao nojos = new NojosDao();

        prod.setDesc(prodDesc);
        prod.setMarca(prodMarca);
        prod.setValor(prodValor);

        nojos.createProduto(prod);
    }
}
