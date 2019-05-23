<%@page import="br.uff.twitter.model.PublicacaoDAO"%>
<%@page import="br.uff.twitter.model.Publicacao"%>
<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    // Recupera usuário que fez a publicação
//    Usuario u = (Usuario) pageContext.getSession().getAttribute("usuarioLogado");
    // O usuário é colocado no contexto da página. Assim o JSTL terá acesso a ela
//    pageContext.setAttribute("usuarioEncontrado", u);
    
    ArrayList<Publicacao> listaPublicacoes = (ArrayList<Publicacao>)(new PublicacaoDAO()).listaTodos();
//    ArrayList<Publicacao> listaPublicacoes = (ArrayList<Publicacao>) request.getAttribute("publicacoes");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("listaPublicacoes", listaPublicacoes);
%>
<html lang="pt-br">
<head>
	<meta charset="utf-8"/>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Feed - Twitter</title>
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
                <a><li id="feed"><i class="fas fa-home"></i>FEED</li></a>
                <a href="perfil.jsp"><li><i class="fas fa-user"></i>PERFIL</li></a>
                <a href="conta.jsp"><li><i class="fas fa-cog"></i>CONTA</li></a>
                <a href="/twitter/UsuarioServlet?operacao=6"><li><i class="fas fa-sign-out-alt"></i>SAIR</li></a>
            </ul>
        </nav>
        <div class="logo"><img src="images/monitor-window.png"/></div>
    </div>
    <div class="feed">
        <!-- Parte para usuario logado escrever postagem -->
        <div class="texting">
            <div class="edit"></div>
            <div class="text">
                <form action="/twitter/PublicacaoServlet?operacao=0" method="post">
                    <textarea placeholder="Type your text" name="texto" maxlength="150"></textarea>
                    <input type="hidden" name="idUsuario" value="${usuarioLogado}"/>
                    <button class="botaoSend">
                        Enviar <i class="fa fa-paper-plane" aria-hidden="true"></i>
                    </button>
                </form>
            </div>
        </div>
        
        <!-- Lista de postagens ordenadas de forma decrescente -->
        <c:if test="${listaPublicacoes != null}" >
            <jsp:useBean id="dateObject" class="java.util.Date"/>
            <c:forEach var="p" items="${listaPublicacoes}">      
                <div class="tweet">
                    <div class="tweetPt1">
                        <h2>${p.autor.apelido}</h2>
                        <div class="tweetFoto"><img src="images/user.png"/></div>
                        <div class="tweetMsg">
                            ${p.texto}
                        </div>
                    </div>
                    <div class="tweetpt2">
                        <form action="/twitter/PublicacaoServlet?operacao=3" method="post">
                            <input type="text" maxlength="150" name="textoComentario"/>
                            <input type="hidden" name="idPublicacao" value="${p.idPublicacao}"/> 
                            <input type="hidden" name="idUsuario" value="${usuarioLogado.idUsuario}"/>
                            <input type="submit" value="Comentar">
                        </form>
                        <c:if test="${p.listaComentarios != null}" >
                            <c:forEach var="c" items="${p.listaComentarios}">
                                <jsp:setProperty name="dateObject" property="time" value="${c.dataComentario}" />
                                ${c.texto} - ${c.autor.nomeCompleto} às 
                                <fmt:formatDate value="${dateObject }" pattern="dd/MM/yyyy kk:mm" />
                                <br/>
                            </c:forEach>
                        </c:if>
                        <img id="reply"src="images/reply.png"/> 12
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>