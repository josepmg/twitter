<%@page import="br.uff.twitter.model.FakeBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uff.twitter.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Usuario> listaUsuarios = FakeBD.getListaUsuarios();
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
                    <li> ${u.nomeCompleto} </li>
                </c:forEach>
            </c:if>
        </ul>
            
    </body>
</html>
