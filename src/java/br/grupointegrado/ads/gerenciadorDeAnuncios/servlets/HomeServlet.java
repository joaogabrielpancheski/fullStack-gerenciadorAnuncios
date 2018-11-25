package br.grupointegrado.ads.gerenciadorDeAnuncios.servlets;

import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.AnuncioDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author jgpancheski
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarAnuncios(req, resp);
    }

    private void listarAnuncios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexao = (Connection) req.getAttribute("conexao");

        AnuncioDao dao = new AnuncioDao(conexao);

        String buscarAnuncioPesquisa = req.getParameter("buscar-anuncio");
        String buscarAnuncioCategoria = req.getParameter("categoria");

        List<Anuncio> anuncios = new ArrayList<>();

        try {
            if (buscarAnuncioPesquisa != null && !"".equals(buscarAnuncioPesquisa)) {
                anuncios = dao.buscaTodosPesquisa(buscarAnuncioPesquisa);
            } else if (buscarAnuncioCategoria != null && !"".equals(buscarAnuncioCategoria)) {
                anuncios = dao.buscaTodosCategoria(buscarAnuncioCategoria);
            } else {
                anuncios = dao.buscaTodos();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem-erro", "Não foi possível conectar com o banco de dados.");
        }
        
        req.setAttribute("anuncios", anuncios);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/home.jsp");
        dispatcher.forward(req, resp);
    }
}
