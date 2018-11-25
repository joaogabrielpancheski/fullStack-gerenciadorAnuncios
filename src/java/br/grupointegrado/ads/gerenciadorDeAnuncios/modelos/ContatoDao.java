package br.grupointegrado.ads.gerenciadorDeAnuncios.modelos;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/*
 * @author jgpancheski
 */
public class ContatoDao {
    public static Contato getContatoByRequest(HttpServletRequest req) throws IOException {

        Contato contato = new Contato();
        
        contato.setNome(req.getParameter("contato-nome"));
        contato.setEmail(req.getParameter("contato-email"));
        contato.setMensagem(req.getParameter("contato-mensagem"));

        return contato;
    }
}
