package br.grupointegrado.ads.gerenciadorDeAnuncios.servlets;

import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.AnuncioDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Categoria;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.CategoriaDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Combustivel;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.CombustivelDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.Formatter;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author jgpancheski
 */
public class AnuncioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexao = (Connection) req.getAttribute("conexao");

        AnuncioDao anuncdao = new AnuncioDao(conexao);
        CombustivelDao combDao = new CombustivelDao(conexao);
        CategoriaDao catDao = new CategoriaDao(conexao);

        Long buscarAnuncio = Formatter.stringParaLong(req.getParameter("anuncio"));

        Anuncio anuncio = new Anuncio();
        Combustivel comb = new Combustivel();
        Categoria cat = new Categoria();

        try {
            anuncio = anuncdao.buscaPorId(buscarAnuncio);
            comb = combDao.buscaPorId(anuncio.getTpCombustivel());
            cat = catDao.buscaPorId(anuncio.getCategoria());
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem-erro", "Não foi possível conectar com o banco de dados.");
        }

        req.setAttribute("anuncio", anuncio);
        req.setAttribute("tpComb", comb);
        req.setAttribute("categoria", cat);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/anuncio.jsp");
        dispatcher.forward(req, resp);
    }

}