package aula.introducaodao.Controle;

import aula.introducaodao.DAO.ErroDAO;
import aula.introducaodao.DAO.PessoaDAOInterface;
import aula.introducaodao.DAO.PessoaDaoClasse;
import aula.introducaodao.Modelo.Pessoa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/editar")
public class Editar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String nome=request.getParameter("nome");
        String tidade=request.getParameter("idade");
        if(nome!=null && !nome.isBlank() && tidade!=null && !tidade.isBlank()) {
            Pessoa p = new Pessoa(id, nome, Integer.parseInt(tidade));
            try (PessoaDAOInterface dao = new PessoaDaoClasse()) {
                dao.editar(p);
                //response.getWriter().println(p);
                out.println("Editado com sucesso.");
            } catch (ErroDAO e) {
                Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, "Erro ao editar", e);
                out.println("Erro ao tentar editar");
            }
        }
        else
            out.println("Informe o nome e a idade");
    }
}
