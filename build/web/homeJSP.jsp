<%-- 
    Document   : homeJSP
    Created on : 17/04/2019, 10:33:49
    Author     : JP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Menu Principal</h1>
        <a href="./novoUsuarioForm.jsp">Cadastrar novo usuário</a>
        <a href="/twitter/UsuarioServlet?operacao=1">Listar usuários cadastrados</a>
    </body>
</html>
