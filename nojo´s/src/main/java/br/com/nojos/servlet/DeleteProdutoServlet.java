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
        nojosDao = new NojosDao(); // Inicializa o DAO ao iniciar a servlet
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtém o ID do produto a ser deletado a partir da requisição
            Long id = Long.valueOf(req.getParameter("id"));

            // Chama o método deleteProduto
            nojosDao.deleteProduto(id);

            // Redireciona para a página de produtos após a deleção
            resp.sendRedirect("produtos.jsp"); // Atualize para o caminho correto de sua página

        } catch (NumberFormatException e) {
            // Tratamento de erro para ID inválido
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (Exception e) {
            // Tratamento de erro genérico
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao deletar produto: " + e.getMessage());
        }
    }
}
