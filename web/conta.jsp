<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="pt-br">
<head>
	<meta charset="utf-8"/>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
    <title>Conta - Twitter</title>
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/style2.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">


   
</head>

<body>

    <!-- menu principal feed  -->
    <div class="menu">
        <div class="menuFoto"><img src="images/user.png"/></div>
        <div class="menuName"> 
            <p>Olá,</p>
            <h3>
                <c:if test="${usuarioLogado != null}">
                    <h2>${usuarioLogado.apelido}</h2>
                </c:if>
            </h3>
        </div>
        <nav style="clear:both">
            <ul>
                <a href="/twitter/PublicacaoServlet?operacao=4"><li><i class="fas fa-home"></i>FEED</li></a>
                <a href="/twitter/PublicacaoServlet?operacao=1"><li><i class="fas fa-user"></i>PERFIL</li></a>
                <a href="/twitter/UsuarioServlet?operacao=3"><li id="conta"><i class="fas fa-cog"></i>CONTA</li></a>
                <a href="/twitter/UsuarioServlet?operacao=6"><li><i class="fas fa-sign-out-alt"></i>SAIR</li></a>
            </ul>
        </nav>
        <div class="logo"><img src="images/monitor-window.png"/></div>
    </div>
    <div class="conta">
        
        <div id="alteraCadastro" class="formCad">
                <h2>Configurações de conta:</h2>
                <form action="/twitter/UsuarioServlet?operacao=2" method="post">
                    <input type="hidden" name="idUsuario" value="${usuarioLogado.idUsuario}"/>
                    <div class="pt1">
                        <p>Nome:</p>
                        <input id="nome"type="text" name="nomeCompleto" required value="${usuarioLogado.nomeCompleto}">
                        <p>Data de Nascimento:</p>
                        <input id="data" type="date" name="dataNascimento" required value="${usuarioLogado.dataNascimento}">
                    </div>
                    <div class="pt2">
                        <p>Email:</p>
                        <input type="email" name="email" id="email" readonly value="${usuarioLogado.email}">
                        <p>Usuário:</p>
                        <input type="text" name="apelido" id="senha"required value="${usuarioLogado.apelido}">
                        <p>Senha:</p>
                        <input id="date" type="password" name="senha" required value="${usuarioLogado.senha}">
                        <p>Confirmação de senha:</p>
                        <input id="date" type="password" name="confSenha" required value="${usuarioLogado.senha}">
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