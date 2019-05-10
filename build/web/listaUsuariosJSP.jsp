<%@page import="br.uff.twitter.model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) request.getAttribute("usarios");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("listaUsuarios", listaUsuarios);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuários</title>
    </head>
    <body>
        <ul>
            <c:if test="${listaUsuarios != null}" >
                <c:forEach var="u" items="${listaUsuarios}">
                    <li> <h3>${u.nomeCompleto}</h3> 
                        <form action="/twitter/UsuarioServlet?operacao=3" method="post"> <input type="hidden" name="id" value="${u.idUsuario}"/> <input type="submit" value="Deletar"> </form>
                        <form action="/twitter/UsuarioServlet?operacao=4" method="post"> <input type="hidden" name="id" value="${u.idUsuario}"/> <input type="submit" value="Alterar dados"> </form>
                        <form action="/twitter/PublicacaoServlet?operacao=1" method="post"> <input type="hidden" name="usuario" value="${u.idUsuario}"/> <input type="submit" value="Listar comentários"> </form>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
            
    </body>
</html>
