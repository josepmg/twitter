<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="iso-8859-1"/>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Recupera��o de senha - Twitter</title>
    <link href="css/style.css" rel="stylesheet"/>
     <link href="css/responsive.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   
</head>
<body>

    <!-- início do conteúdo da página - login -->
    <div id="principal">
        <!-- imagem do logo-->
        <div class="image-logoCad">
                <img src="images/monitor-window.png"/>
            </div>
            <!-- formulário -->
            <div class="formCad">
                <h1>RECUPERAR SENHA</h1>
                <p>Digite os dados solicitados abaixo para recuperar sua senha!</p>
                <br>
                <form action="/twitter/usuarioServlet?acao=alteraSenha" method="POST">
                        <div class="pt1Rec">
                            <p>E-mail:</p>
                            <input type="email" name="email" required>
                            <p>Insira a nova senha:</p>
                            <input type="password" name="senha" required>
                            <p>Confirme a senha:</p>
                            <input type="password" name="confirmasenha" required>
                                <a href="index.jsp"><input id="cancelarRec" type="button" name="cancelar" value="Cancelar"></a>
                                <input id="cadastrarRec" type="submit" name="enviar" value="Recuperar">
                        </div>
                </form>

            </div>

    </div>
 
</body>
</html>