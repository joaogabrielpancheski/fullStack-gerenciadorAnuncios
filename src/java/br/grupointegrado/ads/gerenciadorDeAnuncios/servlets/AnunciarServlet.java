package br.grupointegrado.ads.gerenciadorDeAnuncios.servlets;

import br.grupointegrado.ads.gerenciadorDeAnuncios.filtros.ConexaoFilter;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.AnuncioDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Categoria;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.CategoriaDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Combustivel;
import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.CombustivelDao;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.Validations;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/*
 * @author jgpancheski
 */
public class AnunciarServlet extends HttpServlet {

    ConexaoFilter conexaoFilter = new ConexaoFilter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregamento(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Anuncio anuncio = AnuncioDao.getAnuncioByRequest(req);
            String mensagemErro = validaAnuncio(anuncio);

            if (mensagemErro == null) {

                Connection conexao = (Connection) req.getAttribute("conexao");
                AnuncioDao dao = new AnuncioDao(conexao);
                try {
                    dao.inserir(anuncio);
                    resp.sendRedirect("/veiculos/home");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    req.setAttribute("mensagem-erro", "Não foi possível salvar o anuncio.");
                    req.setAttribute("anuncio", anuncio);

                    carregamento(req, resp);
                }

            } else {
                req.setAttribute("mensagem-erro", mensagemErro);
                req.setAttribute("anuncio", anuncio);

                carregamento(req, resp);
            }
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem-erro", "Não foi possível salvar a imagem do anuncio.");

            carregamento(req, resp);
        }
    }

    private String validaAnuncio(Anuncio anuncio) {
        if (!Validations.validaString(anuncio.getTitulo(), 5, 50)) {
            return "Preencha o título com no mínimo 10 e no máximo 50 caracteres.";
        }

        Calendar calendar = GregorianCalendar.getInstance();
        int dataMaxima = calendar.get(Calendar.YEAR);

        if (!Validations.validaLong(anuncio.getAnoFab(), 1886, dataMaxima)) {
            return "Preencha um ano de fabricação válido.";
        }

        if (!Validations.validaLong(anuncio.getAnoMod(), 1886, dataMaxima + 1)) {
            return "Preencha um ano de modelo válido.";
        }

        if (!Validations.validaLong(anuncio.getKm(), 1, Integer.MAX_VALUE)) {
            return "Preencha uma quilometragem válida.";
        }

        if (!Validations.validaLong(anuncio.getValor(), 1, Integer.MAX_VALUE)) {
            return "Preencha um valor válido.";
        }

        if (!Validations.validaLong(anuncio.getTpCombustivel(), 1, 100)) {
            return "Preencha o tipo de combustível.";
        }

        if (!Validations.validaLong(anuncio.getCategoria(), 1, 100)) {
            return "Preencha a categoria.";
        }

        if (!Validations.validaString(anuncio.getImagem(), 1, 255)) {
            return "Selecione uma imagem válida.";
        }

        if (!Validations.validaString(anuncio.getDescricao(), 5, 200)) {
            return "Preencha a descrição com no mínimo 10 e no máximo 200 caracteres.";
        }

        return null;
    }

    private void carregamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexao = (Connection) req.getAttribute("conexao");
        CombustivelDao combDao = new CombustivelDao(conexao);
        List<Combustivel> combustivel = new ArrayList<>();

        try {
            combustivel = combDao.buscaTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem-erro", "Não foi possível conectar com o banco de dados.");
        }

        CategoriaDao catDao = new CategoriaDao(conexao);
        List<Categoria> categoria = new ArrayList<>();

        try {
            categoria = catDao.buscaTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("mensagem-erro", "Não foi possível conectar com o banco de dados.");
        }

        req.setAttribute("combustivel", combustivel);
        req.setAttribute("categoria", categoria);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/anunciar.jsp");
        dispatcher.forward(req, resp);
    }
}
