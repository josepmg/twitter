<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8"/>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Cadastro - Twitter</title>
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/responsive.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   
</head>
<body>

    <!-- inÃ­cio do conteÃºdo da pÃ¡gina - login -->
    <div id="principal">
        <!-- imagem do logo-->
        <div class="image-logoCad">
                <img src="images/monitor-window.png"/>
            </div>
            <!-- formulÃ¡rio -->
            <div class="formCad">
                <h1>CADASTRE-SE</h1>
                <p>Digite seus dados para se cadastrar no (nomedoSite)</p>
                <br>
                    <form action="/twitter/UsuarioServlet?operacao=0" method="post">
                        <div class="pt1">
                            <p>Nome completo:</p>
                            <input id="nome" name="nomeCompleto" type="text" required>
                            <p>Data de Nascimento:</p>
                            <input id="data" name="dataNascimento" type="date" required>
                            <p>Email:</p>
                            <input id="email" name="email" type="email" required>
                        </div>
                        <div class="pt2">
                            <p>Usuário:</p>
                            <input id="senha" name="apelido" type="text" required>
                            <p>Senha:</p>
                            <input id="date" name="senha" type="password" required>
                            <p>Confirmação de senha:</p>
                            <input id="date"  name="confSenha" type="password"  required>
                        </div>
                          <a href="index.jsp"><input id="cancelar" type="button" name="cancelar" value="Cancelar"></a>
                          <input id="cadastrar" type="submit" name="enviar" value="Cadastrar">
                </form>

            </div>

    </div>
 
</body>
</html>