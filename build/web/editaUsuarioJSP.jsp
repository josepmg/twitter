<%@page import="br.uff.twitter.model.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usarioEncontrado");
    // A lista de usuários é colocada no contexto da página. Assim o JSTL terá acesso a ela
    pageContext.setAttribute("usarioEncontrado", usuario);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/twitter/UsuarioServlet?operacao=2" method="post">
            <input type="hidden" name="idUsuario" value="${usarioEncontrado.idUsuario}"/>
            Nome completo: <input type="text" name="nomeCompleto" placeholder="Nome Completo" value="${usarioEncontrado.nomeCompleto}"/> <br/>
            Data de nascimento: <input type="number" name="dataNascimento" value="${usarioEncontrado.dataNascimento}"/> <br/>
            Apelido: <input type="text" name="apelido" value="${usarioEncontrado.apelido}"/> <br/>
            E-mail: <input type="text" name="email" value="${usarioEncontrado.email}"/> <br/>
            Senha: <input type="password" name="senha" value="${usarioEncontrado.senha}"/><br/> <br/>
            <input type="submit" value="Enviar"> <input type="reset" value="Limpar">
        </form>
    </body>
</html>
