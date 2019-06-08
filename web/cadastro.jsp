<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<title>Cadastro - Twitter</title>
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/responsive.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   
    <!-- Importação dos scripts js--->
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.mask.min.js"></script>
    
    <!-- Script da mascara de data-->
    <script type="text/javascript">
        $(document).ready(function(){
                $('.data').mask('00/00/0000');
            });
        </script>
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
                <p>Digite seus dados para cadastrar-se</p>
                <br>
                    <form action="/twitter/usuarioServlet?acao=criaUsuario" method="post">
                        <div class="pt1">
                            <p>Nome completo:</p>
                            <input id="nome" name="nomeCompleto" type="text" required>
                            <p>Data de Nascimento:</p>
                            <input class="data" name="dataNascimento" type="text" required>
                            <p>Email:</p>
                            <input id="email" name="email" type="email" required>
                        </div>
                        
                        <div class="pt1">
                            <p>Usuário:</p>
                            <input id="usuario" name="apelido" type="text" required>
                            <p>Senha:</p>
                            <input id="senha" name="senha" type="password" required>
                            <p>Confirmação de senha:</p>
                            <input id="senha-conf"  name="confSenha" type="password"  required>
                        </div>
                          <a href="index.jsp"><input id="cancelar" type="button" name="cancelar" value="Cancelar"></a>
                          <input id="cadastrar" type="submit" name="enviar" value="Cadastrar">
                </form>

            </div>

    </div>
 
</body>
</html>