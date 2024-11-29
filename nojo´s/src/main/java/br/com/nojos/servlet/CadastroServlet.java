package br.com.nojos.servlet;

import br.com.nojos.dao.NojosDao;
import br.com.nojos.model.Login;
import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registro")
public class CadastroServlet extends HttpServlet {

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
        req.getRequestDispatcher("registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("password");

        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            req.setAttribute("error", "Email e senha são obrigatórias.");
            req.getRequestDispatcher("registro.jsp").forward(req, resp);
            return;
        }

        try {
            Login novoUsuario = new Login(email, senha);
            nojosDao.createUser(novoUsuario);
            resp.sendRedirect("/login?success=true");
        } catch (Exception e) {
            req.setAttribute("error", "Erro ao cadastrar usuário: " + e.getMessage());
            req.getRequestDispatcher("registro.jsp").forward(req, resp);
        }
    }
}
