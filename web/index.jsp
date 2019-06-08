<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<html lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Início - Twitter</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/responsive.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   
</head>
<body>

    <!-- inÃ­cio do conteÃºdo da pÃ¡gina - login -->
    <div id="principal">
        <!-- imagem do logo-->
        <div class="image-logo">
                <img src="images/monitor-window.png"/>
            </div>
            <!-- formulário -->
            <div class="form">
                <form action="/twitter/usuarioServlet?acao=fazLogin" method="POST">
                    <input name="email" type="email" required placeholder="E-mail" id="user">
                    <input name="senha" type="password" required placeholder="Senha" id="password">
                    <a href="recuperarsenha.jsp" class="forgotPass">Esqueci minha senha</a>
                    <button input type="submit">Entrar</button>
                    <div class="register">
                        <p>Não possui conta? <a href="cadastro.jsp">Cadastre-se</a></p>
                    </div>
                </form>

            </div>

    </div>
 
</body>
</html>