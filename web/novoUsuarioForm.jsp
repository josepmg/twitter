<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/twitter/novoUsuario" method="post">
            Nome completo: <input type="text" name="nomeCompleto" placeholder="Nome Completo"/> <br/>
            Data de nascimento: <input type="number" name="dataNascimento"/> <br/>
            Apelido: <input type="text" name="apelido" placeholder="apelido"/> <br/>
            E-mail: <input type="text" name="email" placeholder="exemplo@mail.com"/> <br/>
            Senha: <input type="password" name="senha"/> <br/> <br/>
            <input type="submit" value="Enviar"> <input type="reset" value="Limpar">
        </form>
    </body>
</html>
