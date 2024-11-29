package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import br.com.nojos.model.Produto;
import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/carrinho")
public class CarrinhoServlet extends HttpServlet {

    private NojosDao nojosDao;

    @Override
    public void init() throws ServletException {
        try {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:~/test;DB_CLOSE_DELAY=-1");
            dataSource.setUser("sa");
            dataSource.setPassword("sa");
            nojosDao = new NojosDao(dataSource);
            nojosDao.initDatabase();
        } catch (Exception e) {
            throw new ServletException("Erro ao configurar o DataSource", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtém todos os produtos no carrinho
            List<Produto> carrinho = nojosDao.getCarrinho();
            req.setAttribute("carrinho", carrinho);

            req.getRequestDispatcher("carrinho.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar o carrinho: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String produtoIdParam = req.getParameter("produtoId");
            if (produtoIdParam == null || produtoIdParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do produto é obrigatório.");
                return;
            }

            Long produtoId = Long.parseLong(produtoIdParam);
            nojosDao.addToCarrinho(produtoId);

            resp.sendRedirect("/carrinho");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }
}
