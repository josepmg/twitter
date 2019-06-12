package br.uff.twitter.controler;

import br.uff.twitter.model.Comentario;
import br.uff.twitter.model.ComentarioDAO;
import br.uff.twitter.model.Publicacao;
import br.uff.twitter.model.PublicacaoDAO;
import br.uff.twitter.model.Usuario;
import br.uff.twitter.model.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PublicacaoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch(request.getParameter("acao")){
            case "criaPublicacao":
                criaPublicacao(request, response);
                break;
            case "listaPublicacaoUsuario":
                listaPublicacaoUsuario(request,response);
                break;
            case "removePublicacao":
                removePublicacao(request, response);
                break;
            case "criaComentario":
                criaComentario(request, response);
                break;
            case "listaTodasPublicacoes":
                listaTodasPublicacoes(request, response);
                break;
            default:
                break;
        }
    }
    
    private void listaPublicacaoUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("/twitter/index.jsp");
           return;
       } else{
            // Adiciona o usuário
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            List<Publicacao> publicacaoList = new ArrayList<>();
            // Recupera publicações de um usuario
            publicacaoList = (new PublicacaoDAO()).listaPorAutor(usuario.getIdUsuario());
            // Para cada publicação, recuperará seus comentários
            for (Publicacao p : publicacaoList){
                p.setListaComentarios((new ComentarioDAO()).listaPorPublicacao(p.getIdPublicacao()));
            }
            // Adiciona a lista de publicações 
            request.setAttribute("publicacoes", publicacaoList);
            // Troca de tela pelo Dispatcher
            getServletConfig().getServletContext().getRequestDispatcher("/perfil.jsp").forward(request, response);
       }
    }
    
    private void listaTodasPublicacoes(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
         if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("/twitter/index.jsp");
            return;
        } else{
            ArrayList<Publicacao> listaPublicacoes = (ArrayList<Publicacao>)(new PublicacaoDAO()).listaTodos();
            // Para cada publicação, recuperará seus comentários
            for (Publicacao p : listaPublicacoes){
                p.setListaComentarios((new ComentarioDAO()).listaPorPublicacao(p.getIdPublicacao()));
            }
            // Adiciona a lista de publicações 
            request.setAttribute("publicacoes", listaPublicacoes);
            // Troca de tela pelo Dispatcher
            getServletConfig().getServletContext().getRequestDispatcher("/feed.jsp").forward(request, response);
        }
    }

    private void removePublicacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("/twitter/index.jsp");
            return;
        } else{
            // Chama método para remover publicação pelo seu id
            (new PublicacaoDAO()).remove(Integer.valueOf(request.getParameter("idPublicacao")));
            listaPublicacaoUsuario(request,response);
        }
    }

    private void criaPublicacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {   
        if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("/twitter/index.jsp");
            return;
        } else{
            // Busca o usuário da sessão Http
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
            // Cria uma nova publicação com os dados dos Form
            Publicacao publicacao = new Publicacao(
                    request.getParameter("texto"), 
                    usuarioLogado,
                    ((new Date()).getTime()));
            // Chama método para cadastrar publicação
            (new PublicacaoDAO()).adiciona(publicacao);
            // Atualiza o usuário na sessão Http (agora com todos seus dados, inclusive id)
            request.getSession().setAttribute("usuarioLogado", usuarioLogado);
            listaTodasPublicacoes(request,response);
        }
    }
    
    private void criaComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("/twitter/index.jsp");
            return;
        }else{
            // Instancia um novo comentario
            Comentario comentario = new Comentario(
                    request.getParameter("textoComentario"), 
                    (new Date()).getTime(), 
                    (new UsuarioDAO()).busca(Integer.valueOf(request.getParameter("idUsuario")))
            );
            // Recupera publicação
            Publicacao publicacao = (new PublicacaoDAO()).busca(Integer.valueOf(request.getParameter("idPublicacao")));
            publicacao.adicionaComentario(comentario);
            // Cria o comentário no BD
            (new ComentarioDAO()).adiciona(comentario, publicacao);
            listaPublicacaoUsuario(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
