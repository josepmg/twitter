<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<Usuario> listaPublicacoes = (ArrayList<Usuario>) request.getAttribute("publicacoes");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("listaPublicacoes", listaPublicacoes);
   
    // Recupera usuário que fez a publicação
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    // O usuário é colocado no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("usuarioEncontrado", usuario);
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
            <form>
                Texto: <br/>
                <textarea name="texto" maxlength="145" rows="8" cols="20"></textarea>
                <input type="hidden" name="idUsuario" value="${usuarioEncontrado.idUsuario}"/>
                <br/><br/>
                <input type="submit" value="Publicar">
            </form>
        </div>
        <div style="width: 70%; float: left">
            <c:if test="${listaUsuarios != null}" >
                <c:forEach var="p" items="${listaPublicacoes}">
                    ${p.texto}
                    <br/> <br/>
                    <form action="/twitter/PublicacaoServlet?operacao=3" method="post"> <input type="hidden" name="id" value="${p.idPublicacao}"/> <input type="submit" value="Deletar"> </form>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
