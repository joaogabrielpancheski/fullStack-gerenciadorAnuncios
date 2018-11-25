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
public class CombustivelDao {

    private Connection conexao;

    public CombustivelDao(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Combustivel> buscaTodos() throws SQLException {
        String sql = "SELECT * FROM combustivel";

        PreparedStatement statement = conexao.prepareStatement(sql);

        List<Combustivel> combustivel = new ArrayList<>();

        ResultSet result = statement.executeQuery();
        if (result.first()) {
            do {
                Combustivel comb = new Combustivel();

                comb.setId(result.getLong("id"));
                comb.setNome(result.getString("nome"));

                combustivel.add(comb);
            } while (result.next());
        }

        return combustivel;
    }

    public Combustivel buscaPorId(long id) throws SQLException {
        String sql = "SELECT * FROM combustivel WHERE id = ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();

        if (result.first()) {
            Combustivel comb = new Combustivel();

            comb.setId(result.getLong("id"));
            comb.setNome(result.getString("nome"));

            return comb;
        }
        return null;
    }

}
