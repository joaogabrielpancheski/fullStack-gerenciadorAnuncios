<%-- 
    Document   : home
    Created on : 12/11/2018, 12:47:26
    Author     : jgpancheski
--%>

<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Categoria"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Combustivel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Object mensagemErro = request.getAttribute("mensagem-erro");
    List<Anuncio> anuncios = (List<Anuncio>) request.getAttribute("anuncios");

    String buscarProduto = request.getParameter("buscar-anuncio");
    if (buscarProduto == null) {
        buscarProduto = "";
    }

    Locale locale = new Locale("pt", "BR");
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

    DecimalFormat dfKm = new DecimalFormat("#,##0");

    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
    java.util.Date dataAnuncio = new java.util.Date();
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Anúncios de Veículos</title>

        <link rel="stylesheet" type="text/css" href="CSS/fontawesome.all.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style-default.css">
        <link rel="stylesheet" type="text/css" href="CSS/style-index.css">

        <script>
            function abrirAnuncio(anuncioId) {
                window.location = "anuncio?anuncio=" + anuncioId;
            }
        </script>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
                <a class="navbar-brand" href="home">VEÍCULOS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home">Início</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="anunciar">Anunciar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contato">Contato</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <main class="container-fluid">
            <div class="conteudo col-sm-12 col-md-11 col-lg-10">

                <div class="row">
                    <div class="menu-esquerdo col-md-auto">
                        <nav>
                            <form>
                                <div class="input-group input-group-sm mb-3">
                                    <input type="text" name="buscar-anuncio" class="form-control" placeholder="Busca por palavras chave"
                                           aria-label="Busca por palavras chave" aria-describedby="button-search">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" title="Buscar" type="submit" id="button-search">
                                            <i class="fas fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>

                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <a href="?categoria=1">Carros, vans e utilitários</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="?categoria=2">Motos</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="?categoria=3">Caminhões</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="?categoria=4">Barcos e aeronaves</a>
                                </li>
                                <li class="list-group-item">
                                    <a href="?categoria=5">Ônibus</a>
                                </li>
                            </ul>
                        </nav>
                    </div>

                    <div class="col">
                        <h3>Veículos anunciados</h3>
                        <table class="table table-hover">

                            <%                    if (mensagemErro != null) {
                            %>

                            <div class="alert alert-danger" role="alert">
                                <%= mensagemErro%>
                            </div>

                            <%
                                }
                            %>

                            <%
                                for (Anuncio a : anuncios) {
                                    dataAnuncio = a.getData();
                            %>
                            <tr class="tabela-veiculos-linha" onclick="abrirAnuncio(<%= a.getId()%>)">
                                <td class="tv-col-imagem">
                                    <img src="imagem?caminho=<%= a.getImagem()%>" class="produto-imagem" />
                                </td>
                                <td class="informacoes-anuncio">
                                    <div class="row">
                                        <div class="col descricao-anuncio">
                                            <p><%= a.getTitulo()%> | <%= a.getAnoFab()%>/<%= a.getAnoMod()%> | <%= dfKm.format(a.getKm())%> Km</p>
                                        </div>
                                        <div class="col-sm-auto valor-anuncio">
                                            <p><%= nf.format(a.getValor())%></p>
                                        </div>
                                        <div class="col-sm-auto data-hora-anuncio">
                                            <p><%= data.format(dataAnuncio)%></br><%= hora.format(dataAnuncio)%></p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>

                    </div>
                </div>

            </div>
        </main>

        <script src="JS/jquery-3.3.1.slim.min.js"></script>
        <script src="JS/popper.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
        <script src="JS/scripts-default.js"></script>
    </body>

</html>