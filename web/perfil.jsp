<%@page import="br.uff.twitter.model.PublicacaoDAO"%>
<%@page import="br.uff.twitter.model.Publicacao"%>
<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogado");
    ArrayList<Publicacao> listaPublicacoes = (ArrayList<Publicacao>) request.getAttribute("publicacoes");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("listaPublicacoes", listaPublicacoes);
%>
<html lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Perfil - Twitter</title>
    <link href="css/style2.css" rel="stylesheet"/>
    <link href="css/responsive.css" rel="stylesheet"/>
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
        <div class="linha"></div>
        <nav style="clear:both">
            <ul>
                <a href="/twitter/PublicacaoServlet?operacao=4"><li id="not-selected"><i class="fas fa-home"></i>FEED</li></a>
                <a href="/twitter/PublicacaoServlet?operacao=1"><li id="perfil"><i class="fas fa-user"></i>PERFIL</li></a>
                <a href="/twitter/UsuarioServlet?operacao=3"><li id="not-selected"><i class="fas fa-cog"></i>CONTA</li></a>
                <a href="/twitter/UsuarioServlet?operacao=6"><li id="not-selected"><i class="fas fa-sign-out-alt"></i>SAIR</li></a>
            </ul>
        </nav>
    </div>
    <div class="feed">
        <div class="perfil">
            <c:if test="${usuarioLogado != null}">
                    <h2>@${usuarioLogado.apelido}</h2>
                    <p>${usuarioLogado.nomeCompleto}</p>
                    
                    <div class="publicacoes">
                        <center>
                        <k>
                            <c:if test="${listaPublicacoes != null}" >
                                <b>${fn:length(listaPublicacoes)}</b>
                            </c:if>
                        
                        Publicações</k>
                        </center>
                    </div>
                </c:if>
                              
        </div>
          <!-- Lista de postagens ordenadas de forma decrescente -->
        <c:if test="${listaPublicacoes != null}" >
            <jsp:useBean id="dateObject" class="java.util.Date"/>
            <c:forEach var="p" items="${listaPublicacoes}">      
                <div class="tweet"  style="clear:both">
                    <div class="tweetPt1"  style="clear:both">
                        <div class="clearfix"></div>
                        <div class="tweetFoto"  style="clear:both"><img src="images/user02.png"/></div>
                        <div class="tweetMsg"  style="clear:both">
                            <h2>${p.autor.apelido}</h2>
                            <p> ${p.texto}</p>
                        </div>
                    </div>
                      <!--  <div class="divisoriaCinza"></div>-->
                        <div class="comentar">
                            <form action="/twitter/PublicacaoServlet?operacao=3" method="post">
                                <input type="text" maxlength="150" name="textoComentario"/></textarea>
                                <input type="hidden" name="idPublicacao" value="${p.idPublicacao}"/> 
                                <input type="hidden" name="idUsuario" value="${usuarioLogado.idUsuario}"/>
                                <input type="submit" value="Comentar" class="coment">
                            </form>
                        </div>
                        <div class="clearfix"></div> 
                        <div class="divisoriaVerde"></div>
                        <div class="tweetpt2">
                            <div class="comentarios">
                                <h2> Comentários </h2>
                                <c:if test="${p.listaComentarios != null}" >
                                    <div class="comentario-section">
                                        <c:forEach var="c" items="${p.listaComentarios}">
                                            <p>
                                                    <jsp:setProperty name="dateObject" property="time" value="${c.dataComentario}" />
                                                    ${c.texto} - ${c.autor.nomeCompleto} às 
                                                    <fmt:formatDate value="${dateObject }" pattern="dd/MM/yyyy kk:mm" />
                                                    <br/>
                                            </p>
                                         </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>