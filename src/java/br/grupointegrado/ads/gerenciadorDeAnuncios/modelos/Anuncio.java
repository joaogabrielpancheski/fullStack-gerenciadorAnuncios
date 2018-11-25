package br.grupointegrado.ads.gerenciadorDeAnuncios.modelos;

import java.sql.Timestamp;

/*
 * @author jgpancheski
 */
public class Anuncio {

    private long id;
    private String titulo;
    private int anoFab;
    private int anoMod;
    private int km;
    private int valor;
    private int tpCombustivel;
    private int categoria;
    private String imagem;
    private String descricao;
    private Timestamp data;

    public Anuncio() {
        titulo = "";
        descricao = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(int anoFab) {
        this.anoFab = anoFab;
    }

    public int getAnoMod() {
        return anoMod;
    }

    public void setAnoMod(int anoMod) {
        this.anoMod = anoMod;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTpCombustivel() {
        return tpCombustivel;
    }

    public void setTpCombustivel(int tpCombustivel) {
        this.tpCombustivel = tpCombustivel;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anuncio other = (Anuncio) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
