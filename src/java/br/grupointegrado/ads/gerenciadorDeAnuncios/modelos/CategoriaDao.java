package br.grupointegrado.ads.gerenciadorDeAnuncios.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author jgpancheski
 */
public class CategoriaDao {

    private Connection conexao;

    public CategoriaDao(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Categoria> buscaTodos() throws SQLException {
        String sql = "SELECT * FROM categoria";

        PreparedStatement statement = conexao.prepareStatement(sql);

        List<Categoria> categoria = new ArrayList<>();

        ResultSet result = statement.executeQuery();
        if (result.first()) {
            do {
                Categoria cat = new Categoria();

                cat.setId(result.getLong("id"));
                cat.setNome(result.getString("nome"));

                categoria.add(cat);
            } while (result.next());
        }

        return categoria;
    }

    public Categoria buscaPorId(long id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();

        if (result.first()) {
            Categoria cat = new Categoria();

            cat.setId(result.getLong("id"));
            cat.setNome(result.getString("nome"));

            return cat;
        }
        return null;
    }
}
