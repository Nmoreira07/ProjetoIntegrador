package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/delete-prod")
public class DeleteProdutoServlet extends HttpServlet {

    private DataSource dataSource;
    private NojosDao nojosDao;

    @Override
    public void init() throws ServletException {
        try {
            // Configuração do DataSource H2
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:~/test;DB_CLOSE_DELAY=-1");
            ds.setUser("sa");
            ds.setPassword("sa");
            dataSource = ds;

            // Inicializa o DAO com o DataSource
            nojosDao = new NojosDao(dataSource);
            nojosDao.initDatabase();

        } catch (Exception e) {
            throw new ServletException("Erro ao configurar o DataSource", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String idParam = req.getParameter("id");

            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do produto é obrigatório.");
                return;
            }

            Long id = Long.parseLong(idParam);

            // Chama o DAO para excluir o produto
            nojosDao.deleteProduto(id);

            // Redireciona para a página de listagem de produtos após a exclusão
            resp.sendRedirect("/findAllprodutos");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido: " + e.getMessage());
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao deletar produto: " + e.getMessage());
        }
    }
}
