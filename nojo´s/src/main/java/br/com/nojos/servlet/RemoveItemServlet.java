package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove-item")
public class RemoveItemServlet extends HttpServlet {

    private NojosDao nojosDao;

    @Override
    public void init() throws ServletException {
        try {
            // Configuração do DataSource
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:~/test;DB_CLOSE_DELAY=-1");
            dataSource.setUser("sa");
            dataSource.setPassword("sa");
            nojosDao = new NojosDao(dataSource);
        } catch (Exception e) {
            throw new ServletException("Erro ao configurar o DataSource", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtém o ID do produto a ser removido
            String produtoIdParam = req.getParameter("produtoId");
            if (produtoIdParam == null || produtoIdParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do produto é obrigatório.");
                return;
            }

            Long produtoId = Long.parseLong(produtoIdParam);

            // Remove o produto do carrinho usando o DAO
            nojosDao.removeFromCarrinho(produtoId);

            // Redireciona de volta para o carrinho
            resp.sendRedirect("/carrinho");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do produto inválido.");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao remover produto do carrinho: " + e.getMessage());
        }
    }
}
