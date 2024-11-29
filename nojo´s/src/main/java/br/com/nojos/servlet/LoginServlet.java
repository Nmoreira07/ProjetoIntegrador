package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("password");

        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email e senha são obrigatórios.");
            return;
        }

        boolean isAuthenticated = nojosDao.validateLogin(email, senha);

        if (isAuthenticated) {
            resp.sendRedirect("/"); // Redireciona para a página inicial ou dashboard
        } else {
            req.setAttribute("error", "Credenciais inválidas.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
