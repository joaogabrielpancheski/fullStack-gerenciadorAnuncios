<%-- 
    Document   : anunciar
    Created on : 12/11/2018, 12:47:00
    Author     : jgpancheski
--%>

<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Categoria"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Combustivel"%>
<%@page import="java.util.List"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Anuncio anuncio = new Anuncio();
    if (request.getAttribute("anuncio") != null) {
        anuncio = (Anuncio) request.getAttribute("anuncio");
    } else {
        anuncio = new Anuncio();
    }
    
    List<Combustivel> combustivel = (List<Combustivel>) request.getAttribute("combustivel");
    List<Categoria> categoria = (List<Categoria>) request.getAttribute("categoria");

    Object mensagemErro = request.getAttribute("mensagem-erro");
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Anunciar Veículo</title>

        <link rel="stylesheet" type="text/css" href="CSS/fontawesome.all.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style-default.css">
        <link rel="stylesheet" type="text/css" href="CSS/style-anunciar.css">

        <script>
            function validarFormulario() {
                var inputTitulo = document['form-anunciar']['anuncio-titulo'];
                var inputAnoFabricacao = document['form-anunciar']['anuncio-ano-fabricacao'];
                var inputAnoModelo = document['form-anunciar']['anuncio-ano-modelo'];
                var inputQuilometragem = document['form-anunciar']['anuncio-quilometragem'];
                var inputValor = document['form-anunciar']['anuncio-valor'];
                var inputCombustivel = document['form-anunciar']['anuncio-combustivel'];
                var inputCategoria = document['form-anunciar']['anuncio-categoria'];
                var inputImagem = document['form-anunciar']['anuncio-imagem'];
                var inputDescricao = document['form-anunciar']['anuncio-descricao'];

                var formValido = true;

                if (!validaString(inputTitulo.value, 5, 50)) {
                    formValido = false;
                    inputTitulo.classList.add('is-invalid');
                    inputTitulo.classList.remove('is-valid');
                } else {
                    inputTitulo.classList.remove('is-invalid');
                    inputTitulo.classList.add('is-valid');
                }

                var now = new Date;
                var dataMaxima = now.getFullYear();

                if (!validaNumber(inputAnoFabricacao.value, 1886, dataMaxima)) {
                    formValido = false;
                    inputAnoFabricacao.classList.add('is-invalid');
                    inputAnoFabricacao.classList.remove('is-valid');
                } else {
                    inputAnoFabricacao.classList.remove('is-invalid');
                    inputAnoFabricacao.classList.add('is-valid');
                }

                if (!validaNumber(inputAnoModelo.value, 1886, dataMaxima + 1)) {
                    formValido = false;
                    inputAnoModelo.classList.add('is-invalid');
                    inputAnoModelo.classList.remove('is-valid');
                } else {
                    inputAnoModelo.classList.remove('is-invalid');
                    inputAnoModelo.classList.add('is-valid');
                }

                if (!validaNumber(inputQuilometragem.value, 1, Number.MAX_VALUE)) {
                    formValido = false;
                    inputQuilometragem.classList.add('is-invalid');
                    inputQuilometragem.classList.remove('is-valid');
                } else {
                    inputQuilometragem.classList.remove('is-invalid');
                    inputQuilometragem.classList.add('is-valid');
                }

                if (!validaNumber(inputValor.value, 1, Number.MAX_VALUE)) {
                    formValido = false;
                    inputValor.classList.add('is-invalid');
                    inputValor.classList.remove('is-valid');
                } else {
                    inputValor.classList.remove('is-invalid');
                    inputValor.classList.add('is-valid');
                }

                if (!validaString(inputCombustivel.value, 1, 100)) {
                    formValido = false;
                    inputCombustivel.classList.add('is-invalid');
                    inputCombustivel.classList.remove('is-valid');
                } else {
                    inputCombustivel.classList.remove('is-invalid');
                    inputCombustivel.classList.add('is-valid');
                }

                if (!validaString(inputCategoria.value, 1, 100)) {
                    formValido = false;
                    inputCategoria.classList.add('is-invalid');
                    inputCategoria.classList.remove('is-valid');
                } else {
                    inputCategoria.classList.remove('is-invalid');
                    inputCategoria.classList.add('is-valid');
                }

                if (!validaString(inputImagem.value, 1, 255)) {
                    formValido = false;
                    inputImagem.classList.add('is-invalid');
                    inputImagem.classList.remove('is-valid');
                } else {
                    inputImagem.classList.remove('is-invalid');
                    inputImagem.classList.add('is-valid');
                }

                if (!validaString(inputDescricao.value, 5, 200)) {
                    formValido = false;
                    inputDescricao.classList.add('is-invalid');
                    inputDescricao.classList.remove('is-valid');
                } else {
                    inputDescricao.classList.remove('is-invalid');
                    inputDescricao.classList.add('is-valid');
                }

                return formValido;
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
                <h3>Preencha os dados do anúncio</h3>
                <div class="dropdown-divider"></div>

                <%                    if (mensagemErro != null) {
                %>

                <div class="alert alert-danger" role="alert">
                    <%= mensagemErro%>
                </div>

                <%
                    }
                %>

                <form method="POST" name="form-anunciar" enctype="multipart/form-data" accept-charset="utf-8" onsubmit="return validarFormulario()">

                    <input type="hidden" name="anuncio-id" id="anuncio-id">

                    <div class="form-row">
                        <div class="col-md-8 col-lg-7 col-xl-6">
                            <div class="form-group">
                                <label for="anuncio-titulo">Título</label>
                                <input type="text" class="form-control" id="anuncio-titulo" name="anuncio-titulo" value="<%= anuncio.getTitulo()%>" placeholder="Passat TSI 211CV Revisado 3º Dono">
                                <div class="invalid-feedback">
                                    Informe o título do anúncio.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-6 col-md-4 col-lg-3 col-xl-2">
                            <div class="form-group">
                                <label for="anuncio-ano-fabricacao">Ano de fabricação</label>
                                <input type="number" step="1" class="form-control" id="anuncio-ano-fabricacao" name="anuncio-ano-fabricacao" value="<%= anuncio.getAnoFab()%>" placeholder="2016">
                                <div class="invalid-feedback">
                                    Informe o ano de fabricação do veículo.
                                </div>
                            </div>
                        </div>
                        <div class="col-6 col-md-4 col-lg-3 col-xl-2">
                            <div class="form-group">
                                <label for="anuncio-ano-modelo">Ano do modelo</label>
                                <input type="number" step="1" class="form-control" id="anuncio-ano-modelo" name="anuncio-ano-modelo" value="<%= anuncio.getAnoMod()%>" placeholder="2017">
                                <div class="invalid-feedback">
                                    Informe o ano do modelo do veículo.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <label for="anuncio-quilometragem">Quilometragem</label>
                                <div class="input-group">
                                    <input type="number" step="1" class="form-control" name="anuncio-quilometragem" value="<%= anuncio.getKm()%>" id="anuncio-quilometragem"
                                           placeholder="34000">
                                    <div class="input-group-append">
                                        <span class="input-group-text">Km</span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Informe a quilometragem do veículo
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6 col-md-4 col-lg-3">
                            <div class="form-group">
                                <label for="anuncio-valor">Valor</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">R$</span>
                                    </div>
                                    <input type="number" step="1" class="form-control" id="anuncio-valor" name="anuncio-valor" value="<%= anuncio.getValor()%>" placeholder="119990">
                                    <div class="input-group-append">
                                        <span class="input-group-text">,00</span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Informe o valor do veículo.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-8 col-lg-7 col-xl-6">
                            <div class="form-group">
                                <!-- Os tipos de combustíveis podem ser fixados no código -->
                                <label for="anuncio-combustivel">Tipo de combustível</label>
                                <select class="form-control" id="anuncio-combustivel" name="anuncio-combustivel" value="<%= anuncio.getTpCombustivel()%>">
                                    <%
                                        for (Combustivel comb : combustivel) {
                                    %>
                                    <option value="<%= comb.getId()%>"><%= comb.getNome()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <div class="invalid-feedback">
                                    Selecione o tipo de combustível do veículo.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-8 col-lg-7 col-xl-6">
                            <div class="form-group">
                                <!-- As categorias devem ser carregadas do banco de dados -->
                                <label for="anuncio-categoria">Categoria</label>
                                <select class="form-control" id="anuncio-categoria" name="anuncio-categoria" value="<%= anuncio.getCategoria()%>">
                                    <%
                                        for (Categoria cat : categoria) {
                                    %>
                                    <option value="<%= cat.getId()%>"><%= cat.getNome()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <div class="invalid-feedback">
                                    Selecione a categoria do veículo.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-lg-8 col-xl-7">
                            <div class="form-group">
                                <label for="anuncio-imagem">Imagem</label>
                                <div class="custom-file">
                                    <input type="file" accept="image/jpg,image/jpeg,image/png" class="custom-file-input" id="anuncio-imagem" name="anuncio-imagem" value="<%= anuncio.getImagem()%>">
                                    <label class="custom-file-label" for="anuncio-imagem">Selecionar arquivo</label>
                                    <div class="invalid-feedback">
                                        Selecione uma foto do veículo.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-lg-8 col-xl-7">
                            <div class="form-group">
                                <label for="anuncio-descricao">Descrição</label>
                                <textarea class="form-control" id="anuncio-descricao" name="anuncio-descricao" placeholder="Carro em ótimas condições..."><%= anuncio.getDescricao()%></textarea>
                                <div class="invalid-feedback">
                                    Informe a descrição do anúncio.
                                </div>
                            </div>
                        </div>
                    </div>

                    <a href="home" class="btn">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Anunciar</button>

                </form>

            </div>
        </main>

        <script src="JS/validations.js"></script>
        <script src="JS/jquery-3.3.1.slim.min.js"></script>
        <script src="JS/popper.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
        <script src="JS/scripts-default.js"></script>
    </body>

</html>
