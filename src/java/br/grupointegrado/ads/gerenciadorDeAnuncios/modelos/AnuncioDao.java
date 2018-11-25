package br.grupointegrado.ads.gerenciadorDeAnuncios.modelos;

import br.grupointegrado.ads.gerenciadorDeAnuncios.filtros.ConexaoFilter;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.Formatter;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.ServletUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/*
 * @author jgpancheski
 */
public class AnuncioDao {

    private Connection conexao;
    ConexaoFilter conexaoFilter = new ConexaoFilter();

    public AnuncioDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Anuncio anuncio) throws SQLException {
        // SQL: INSERT INTO PRODUTOS ...

        String sql = "INSERT INTO anuncios (titulo, anoFab, anoMod, km, valor, tpComb, categoria, imagem, descricao, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, anuncio.getTitulo());
        statement.setInt(2, anuncio.getAnoFab());
        statement.setInt(3, anuncio.getAnoMod());
        statement.setInt(4, anuncio.getKm());
        statement.setInt(5, anuncio.getValor());
        statement.setInt(6, anuncio.getTpCombustivel());
        statement.setInt(7, anuncio.getCategoria());
        statement.setString(8, anuncio.getImagem());
        statement.setString(9, anuncio.getDescricao());
        statement.setTimestamp(10, anuncio.getData());

        statement.execute();
    }

    public static Anuncio getAnuncioByRequest(HttpServletRequest req) throws IOException, FileUploadException {
        Map<String, Object> parametters = ServletUtil.recuperaParametrosMultipart(req);

        String id = (String) parametters.get("anuncio-id");
        String titulo = (String) parametters.get("anuncio-titulo");
        String anoFab = (String) parametters.get("anuncio-ano-fabricacao");
        String anoMod = (String) parametters.get("anuncio-ano-modelo");
        String km = (String) parametters.get("anuncio-quilometragem");
        String valor = (String) parametters.get("anuncio-valor");
        String tpCombustivel = (String) parametters.get("anuncio-combustivel");
        String categoria = (String) parametters.get("anuncio-categoria");
        String descricao = (String) parametters.get("anuncio-descricao");
        Arquivo arquivoImagem = (Arquivo) parametters.get("anuncio-imagem");

        String imagemCaminho = null;
        if (arquivoImagem != null && arquivoImagem.temConteudo()) {
            imagemCaminho = ServletUtil.gravarArquivo(arquivoImagem);
        }

        Anuncio anuncio = new Anuncio();

        anuncio.setId(Formatter.stringParaLong(id));
        anuncio.setTitulo(titulo);
        anuncio.setAnoFab(Formatter.stringParaInt(anoFab));
        anuncio.setAnoMod(Formatter.stringParaInt(anoMod));
        anuncio.setKm(Formatter.stringParaInt(km));
        anuncio.setValor(Formatter.stringParaInt(valor));
        anuncio.setTpCombustivel(Formatter.stringParaInt(tpCombustivel));
        anuncio.setCategoria(Formatter.stringParaInt(categoria));
        anuncio.setDescricao(descricao);
        anuncio.setImagem(imagemCaminho);

        java.util.Date data = new java.util.Date();
        Timestamp timestamp = new Timestamp(data.getTime());
        anuncio.setData(timestamp);

        return anuncio;
    }

    public Anuncio buscaPorId(long id) throws SQLException {
        String sql = "SELECT * FROM anuncios WHERE id = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();

        if (result.first()) {
            Anuncio anunc = new Anuncio();

            anunc.setId(result.getLong("id"));
            anunc.setTitulo(result.getString("titulo"));
            anunc.setAnoFab(result.getInt("anoFab"));
            anunc.setAnoMod(result.getInt("anoMod"));
            anunc.setKm(result.getInt("km"));
            anunc.setValor(result.getInt("valor"));
            anunc.setTpCombustivel(result.getInt("tpComb"));
            anunc.setCategoria(result.getInt("categoria"));
            anunc.setImagem(result.getString("imagem"));
            anunc.setDescricao(result.getString("descricao"));
            anunc.setData(result.getTimestamp("data"));

            return anunc;
        }
        return null;
    }

    public List<Anuncio> buscaTodos() throws SQLException {
        String sql = "SELECT * FROM anuncios ORDER BY data DESC";

        PreparedStatement statement = conexao.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        return getTodosResult(result);
    }

    public List<Anuncio> buscaTodosPesquisa(String termoBusca) throws SQLException {
        String parametroBusca = "%%";
        if (termoBusca != null) {
            parametroBusca = "%" + termoBusca + "%";
        }

        String sql = "SELECT * FROM anuncios WHERE titulo LIKE ? OR descricao LIKE ? ORDER BY data DESC";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, parametroBusca);
        statement.setString(2, parametroBusca);

        ResultSet result = statement.executeQuery();

        return getTodosResult(result);
    }

    public List<Anuncio> buscaTodosCategoria(String categoria) throws SQLException {
        String sql = "SELECT * FROM anuncios WHERE categoria = ? ORDER BY data DESC";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, categoria);

        ResultSet result = statement.executeQuery();

        return getTodosResult(result);
    }

    private List<Anuncio> getTodosResult(ResultSet result) throws SQLException {
        List<Anuncio> anuncios = new ArrayList<>();

        if (result.first()) {
            do {
                Anuncio anunc = new Anuncio();

                anunc.setId(result.getLong("id"));
                anunc.setTitulo(result.getString("titulo"));
                anunc.setAnoFab(result.getInt("anoFab"));
                anunc.setAnoMod(result.getInt("anoMod"));
                anunc.setKm(result.getInt("km"));
                anunc.setValor(result.getInt("valor"));
                anunc.setTpCombustivel(result.getInt("tpComb"));
                anunc.setCategoria(result.getInt("categoria"));
                anunc.setImagem(result.getString("imagem"));
                anunc.setDescricao(result.getString("descricao"));
                anunc.setData(result.getTimestamp("data"));

                anuncios.add(anunc);
            } while (result.next());
        }

        return anuncios;
    }

}
