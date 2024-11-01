package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-produto")
public class DeleteProdutoServlet extends HttpServlet {

    private NojosDao nojosDao;

    @Override
    public void init() throws ServletException {
        nojosDao = new NojosDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Long id = Long.valueOf(req.getParameter("id"));


            nojosDao.deleteProduto(id);


            resp.sendRedirect("produtos.jsp");

        } catch (NumberFormatException e) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (Exception e) {

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao deletar produto: " + e.getMessage());
        }
    }
}
