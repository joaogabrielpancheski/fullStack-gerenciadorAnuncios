package br.grupointegrado.ads.gerenciadorDeAnuncios.filtros;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author jgpancheski
 */
public class ConexaoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection abrirConexao () throws SQLException {
        Properties config = new Properties();
        config.setProperty("user", "root");
        config.setProperty("password", "adm386149*");
        config.setProperty("serverTimezone", "America/Sao_Paulo");
        
        String url = "jdbc:mysql://localhost:3306/veiculos";
        
        return DriverManager.getConnection(url, config);
    }
    
    private void fecharConexao (Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        Connection conexao = null;
        
        try {
            conexao = abrirConexao();
            request.setAttribute("conexao", conexao);
            chain.doFilter(request, response);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            fecharConexao(conexao);
        }
        
    }

    @Override
    public void destroy() {
        
    }
    
}