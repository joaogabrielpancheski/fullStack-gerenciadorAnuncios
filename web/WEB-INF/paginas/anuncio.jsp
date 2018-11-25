<%-- 
    Document   : anuncio
    Created on : 12/11/2018, 12:47:08
    Author     : jgpancheski
--%>

<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Categoria"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Combustivel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Object mensagemErro = request.getAttribute("mensagem-erro");
    Anuncio anuncio = (Anuncio) request.getAttribute("anuncio");
    Combustivel tpComb = (Combustivel) request.getAttribute("tpComb");
    Categoria categoria = (Categoria) request.getAttribute("categoria");

    Locale locale = new Locale("pt", "BR");
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    
    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Contato</title>

    <link rel="stylesheet" type="text/css" href="CSS/fontawesome.all.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/style-default.css">
    <link rel="stylesheet" type="text/css" href="CSS/style-anuncio.css">
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
                    <li class="nav-item">
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
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home">Veículos</a></li>
                    <li class="breadcrumb-item"><a href="home?categoria=<%= categoria.getId()%>"><%= categoria.getNome()%></a></li>
                </ol>
            </nav>

            <h3><%= anuncio.getTitulo()%></h3>
            <div class="dropdown-divider"></div>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <img src="imagem?caminho=<%= anuncio.getImagem()%>" class="rounded float-left veiculo-imagem">
                </div>
                <div class="col-md-6 mb-3">
                    <div class="veiculo-valor rounded mb-2">
                        <span><%= nf.format(anuncio.getValor())%></span>
                    </div>
                    <div>
                        <strong>Ano:</strong>
                        <span><%= anuncio.getAnoFab()%>/<%= anuncio.getAnoMod()%></span>
                    </div>
                    <div>
                        <strong>Quilometragem:</strong>
                        <span><%= anuncio.getKm()%> Km</span>
                    </div>
                    <div>
                        <strong>Combustível:</strong>
                        <span><%= tpComb.getNome()%></span>
                    </div>
                </div>
            </div>

            <h4>Descrição</h4>

            <div class="row">
                <div class="col">
                    <%= anuncio.getDescricao()%>
                </div>
            </div>
    </main>

    <script src="JS/jquery-3.3.1.slim.min.js"></script>
    <script src="JS/popper.min.js"></script>
    <script src="JS/bootstrap.min.js"></script>
    <script src="JS/scripts-default.js"></script>
</body>

</html>
