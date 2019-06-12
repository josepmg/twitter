package br.uff.twitter.controler;

import br.uff.twitter.model.ComentarioDAO;
import br.uff.twitter.model.Publicacao;
import br.uff.twitter.model.PublicacaoDAO;
import br.uff.twitter.model.Usuario;
import br.uff.twitter.model.UsuarioDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        switch(request.getParameter("acao")){
            case "criaUsuario":
                criaUsuario(request, response);
                break;
            case "atualizaUsuario":
                atualizaUsuario(request, response);
                break;
            case "trocaTela":
                trocaTela(request, response);
                break;
            case "fazLogin":
                fazLogin(request, response);
                break;
            case "fazLogout":
                fazLogout(request, response);
                break;
            case "alteraSenha":
                alteraSenha(request, response);
                break;
            case "deletaUsuario":
                deletaUsuario(request, response);
                break;
            default:
                break;
        }
    }
    
    private void criaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, ParseException{
        // Recupera a string da data de nascimento, e cria uma nova Data utilizando o padrão descrito abaixo
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));
        // Cria um novo usuário com os dados dos form
        Usuario usuario = new Usuario(request.getParameter("nomeCompleto"),
                date.getTime(),
                request.getParameter("apelido"), 
                request.getParameter("email"), 
                request.getParameter("senha"),
                request.getParameter("imagePath"));
        // Chama método para cadastrar usuário
        (new UsuarioDAO()).adiciona(usuario);
        // Insere o usuário recém cadastrado na sessão Http
        request.getSession().setAttribute("usuarioLogado", (new UsuarioDAO()).buscaPorEmail(usuario.getEmail()));
        // Troca de tela pelo Dispatcher
        getServletConfig().getServletContext().getRequestDispatcher("/publicacaoServlet?acao=listaTodasPublicacoes").forward(request, response);
    }
    
    private void atualizaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, ParseException{
        if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("/twitter/index.jsp");
           return;
       } else{  
            // Recupera a string da data de nascimento, e cria uma nova Data utilizando o padrão descrito abaixo
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));
            // Recupera o usuario da sessão Http
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
            // Atualiza os dados do usuário
            usuarioLogado.setNomeCompleto(request.getParameter("nomeCompleto"));
            usuarioLogado.setDataNascimento(date.getTime());
            usuarioLogado.setApelido(request.getParameter("apelido"));
            usuarioLogado.setEmail(request.getParameter("email"));
            usuarioLogado.setSenha(request.getParameter("senha"));
            usuarioLogado.setImagePath(request.getParameter("imagePath"));
            // Altera os valores no BD
            (new UsuarioDAO()).altera(usuarioLogado);
            // Atualiza o usuário da sessão Http
            request.getSession().setAttribute("usuarioLogado", usuarioLogado);
            response.sendRedirect("/twitter/publicacaoServlet?acao=listaTodasPublicacoes");
        }
    }   
    
    private void alteraSenha(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Recupera o usuario
        Usuario usuario = (new UsuarioDAO()).buscaPorEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        // Verifica se senhas são iguais
        if(request.getParameter("senha").equals(request.getParameter("confirmasenha"))){
            // Chama método para cadastrar usuário
            (new UsuarioDAO()).alteraSenha(usuario);
            // Redireciona para página de logini
            response.sendRedirect("/twitter/index.jsp");
        } else{
            response.sendRedirect("/twitter/recuperarsenha.jsp");
        }
    }
    
    private void fazLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Busca usuário pelas credênciais de acesso
        Usuario usuario = (new UsuarioDAO()).buscaLogin(
                request.getParameter("email"), 
                request.getParameter("senha"));
        // Caso haja retorno, ou seja, exista um usuário com este e-mail e senha
        if (usuario !=  null){
            // Recupera sessão Http
            HttpSession httpSession = request.getSession();
            // Salva na session
            httpSession.setAttribute("usuarioLogado", usuario);
            // Redireciona
            getServletConfig().getServletContext().getRequestDispatcher("/publicacaoServlet?acao=listaTodasPublicacoes").forward(request, response);
        } else{
            response.sendRedirect("/twitter/index.jsp");
        }
    }
    
    private void fazLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // Invalida sessão Http
        request.getSession().invalidate();
        // Redireciona para página de login
        response.sendRedirect("/twitter/index.jsp");
    }
    
    private void trocaTela(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        // Verifica se há um usuário logado, antes de mostrar os dados da conta
        if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("/twitter/index.jsp");
           return;
       } else{
            response.sendRedirect("/twitter/conta.jsp");
        }
    }
    
    private void deletaUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Recupera o usuario da sessão Http
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        // Remove os comentários do usuário em TODAS ublicações
        (new ComentarioDAO()).removePorAutor(usuarioLogado.getIdUsuario());
        // Remove comentários das publicações DO usuários
        for(Publicacao p : (new PublicacaoDAO()).listaPorAutor(usuarioLogado.getIdUsuario()))
            (new ComentarioDAO()).removePorPublicacao(p.getIdPublicacao());
        // Remove publicações do usuário
        (new PublicacaoDAO()).removePorAutor(usuarioLogado.getIdUsuario());
        // Remove usuário
        (new UsuarioDAO()).remove(usuarioLogado.getIdUsuario());
        // Invalida sessão Http
        request.getSession().invalidate();
        // Redireciona para página de login
        response.sendRedirect("/twitter/index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>  
}
