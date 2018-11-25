package br.grupointegrado.ads.gerenciadorDeAnuncios.servlets;

import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Arquivo;
import br.grupointegrado.ads.gerenciadorDeAnuncios.utils.ServletUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author jgpancheski
 */
public class ImagemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String caminho = req.getParameter("caminho");
        Arquivo arquivo = ServletUtil.lerArquivo(caminho);
        resp.setContentType("image/*");
        resp.setHeader("Content-disposition", "filename=" + arquivo.getNome());
        ServletOutputStream out = resp.getOutputStream();
        out.write(arquivo.getBytes());
    }
    
}
