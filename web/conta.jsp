<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0" name="viewport">
        <title>Conta - Twitter</title>
        <link href="css/style2.css" rel="stylesheet"/>
        <link href="css/responsive.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

        <!-- Importação dos scripts js--->
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="js/jquery.mask.min.js"></script>

        <!-- Script da mascara de data-->
        <script type="text/javascript">
            $(document).ready(function () {
                $('.data').mask('00/00/0000');
            });
        </script>


    </head>

    <body>

        <!-- menu principal feed  -->
        <div class="menu">
            <div class="menuFoto"><img src="${usuarioLogado.imagePath}"/></div>
            <div class="menuName"> 
                <p>Olá,</p>
                <h3>
                    <c:if test="${usuarioLogado != null}">
                        <h2>${usuarioLogado.apelido}</h2>
                    </c:if>
                </h3>
            </div>
            <div class="linha"></div>
            <nav style="clear:both">
                <ul>
                    <a href="/twitter/publicacaoServlet?acao=listaTodasPublicacoes"><li id="not-selected"><i class="fas fa-home"></i>FEED</li></a>
                    <a href="/twitter/publicacaoServlet?acao=listaPublicacaoUsuario"><li id="not-selected"><i class="fas fa-user"></i>PERFIL</li></a>
                    <a href="/twitter/usuarioServlet?acao=trocaTela"><li id="conta"><i class="fas fa-cog"></i>CONTA</li></a>
                    <a href="/twitter/usuarioServlet?acao=fazLogout"><li id="not-selected"><i class="fas fa-sign-out-alt"></i>SAIR</li></a>
                </ul>
            </nav>
        </div>
        <div class="conta">

            <div id="alteraCadastro" class="formCad">
                <h2>Configurações de conta:</h2>
                <form action="/twitter/usuarioServlet?acao=atualizaUsuario" method="post">
                    <input type="hidden" name="idUsuario" value="${usuarioLogado.idUsuario}"/>
                    <div class="pt1">
                        <p>Nome:</p>
                        <input id="nome"type="text" name="nomeCompleto" required value="${usuarioLogado.nomeCompleto}">
                        <p>Data de Nascimento:</p>
                        <jsp:useBean id="dateObject" class="java.util.Date"/>
                        <jsp:setProperty name="dateObject" property="time" value="${usuarioLogado.dataNascimento}" />
                        <input class="data" type="text" name="dataNascimento" required value="<fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy"/>">
                        <p>Email:</p>
                        <input type="email" name="email" id="email" readonly value="${usuarioLogado.email}">

                    </div>
                    <div class="pt1">
                        <p>Usuário:</p>
                        <input type="text" name="apelido" id="senha"required value="${usuarioLogado.apelido}">
                        <p>Senha:</p>
                        <input id="date" type="password" name="senha" required value="${usuarioLogado.senha}">
                        <p>Confirmação de senha:</p>
                        <input id="date" type="password" name="confSenha" required value="${usuarioLogado.senha}">
                    </div>
                    
                        <div class="escolheImagem">
                            <p>Escolha sua imagem de perfil:</p>
                            <table>
                                    <tr>
                                        <input type="radio" name="imagePath" value="images/user01.png" required><img src="images/user01.png" class="imagemUsuario"/>
                                        <input type="radio" name="imagePath" value="images/user02.png" required><img src="images/user02.png" class="imagemUsuario"/>
                                    </tr>
                                    <tr>
                                        <input type="radio" name="imagePath" value="images/user03.png" required><img src="images/user03.png"  class="imagemUsuario"/>
                                        <input type="radio" name="imagePath" value="images/user04.png" required><img src="images/user04.png"  class="imagemUsuario"/>
                                    </tr>
                                </table>
                        </div>
                    <a href="feed.jsp"><input id="cancelar" type="button" name="cancelar" value="Cancelar"></a>
                    <input id="cadastrar" type="submit" name="enviar" value="Alterar">
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>