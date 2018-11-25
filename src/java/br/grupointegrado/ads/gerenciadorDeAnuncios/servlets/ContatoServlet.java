package br.grupointegrado.ads.gerenciadorDeAnuncios.servlets;

import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Contato;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.ContatoDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.JavaMailApp;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.Validations;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author jgpancheski
 */
public class ContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/contato.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Contato contato = ContatoDao.getContatoByRequest(req);
        String mensagemErro = validaContato(contato);

        if (mensagemErro == null) {
            JavaMailApp javaMailApp = new JavaMailApp();
            javaMailApp.enviarEmail(contato);
        } else {
            req.setAttribute("mensagem-erro", mensagemErro);

            req.setAttribute("contato", contato);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/contato.jsp");
        dispatcher.forward(req, resp);
    }

    private String validaContato(Contato contato) {
        if (!Validations.validaString(contato.getNome(), 5, 50)) {
            return "Favor preencher o nome completo.";
        }

        if (!Validations.validaEmail(contato.getEmail())) {
            return "Favor preencher um e-mail válido.";
        }

        if (!Validations.validaString(contato.getMensagem(), 10, 200)) {
            return "A mensagem é obrigatório e deve possuir no máximo 200 caracteres.";
        }

        return null;
    }
}
