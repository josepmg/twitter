<%@page import="br.uff.twitter.model.Publicacao"%>
<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    ArrayList<Publicacao> listaPublicacoes = (ArrayList<Publicacao>) request.getAttribute("publicacoes");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("listaPublicacoes", listaPublicacoes);
   
    // Recupera usuário que fez a publicação
    Usuario u = (Usuario) request.getAttribute("usuario");
    // O usuário é colocado no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("usuarioEncontrado", u);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${usuarioEncontrado != null}">
                <h2>${usuarioEncontrado.nomeCompleto}</h2>
            </c:if>
            
        </h1>
        <br/><br/>
        <div style="width: 30%; float: left">
            <form action="/twitter/PublicacaoServlet?operacao=0" method="post">
                Texto: <br/>
                <textarea name="texto" maxlength="150" rows="8" cols="20"></textarea>
                <input type="hidden" name="idUsuario" value="${usuarioEncontrado.idUsuario}"/>
                <br/><br/>
                <input type="submit" value="Publicar">
            </form>
        </div>
        <div style="width: 70%; float: left">
            <c:if test="${listaPublicacoes != null}" >
                <jsp:useBean id="dateObject" class="java.util.Date"/>
                <c:forEach var="p" items="${listaPublicacoes}">
                    <h2>${p.texto}</h2>
                    <br/> 
                    <!-- Lista de comentários -->
                    <c:if test="${p.listaComentarios != null}" >
                        <c:forEach var="c" items="${p.listaComentarios}">
                            <jsp:setProperty name="dateObject" property="time" value="${c.dataComentario}" />
                            ${c.texto} - ${c.autor.nomeCompleto} às 
                            <fmt:formatDate value="${dateObject }" pattern="dd/MM/yyyy kk:mm" />
                            <br/>
                        </c:forEach>
                    </c:if>
                    <br/><br/>
                    <!-- Adiciona um comentário -->
                    <form action="/twitter/PublicacaoServlet?operacao=3" method="post">
                        <input type="text" maxlength="150" name="textoComentario"/>
                        <input type="hidden" name="idPublicacao" value="${p.idPublicacao}"/> 
                        <input type="hidden" name="idUsuario" value="${usuarioEncontrado.idUsuario}"/>
                        <input type="submit" value="Comentar">
                    </form>
                    <form action="/twitter/PublicacaoServlet?operacao=2" method="post"> 
                        <input type="hidden" name="idPublicacao" value="${p.idPublicacao}"/> 
                        <input type="submit" value="Deletar">
                    </form>    
                    <hr>
                    <br/>    
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
