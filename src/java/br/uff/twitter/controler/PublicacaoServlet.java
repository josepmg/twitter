/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.controler;

import br.uff.twitter.model.Comentario;
import br.uff.twitter.model.ComentarioDAO;
import br.uff.twitter.model.Publicacao;
import br.uff.twitter.model.PublicacaoDAO;
import br.uff.twitter.model.Usuario;
import br.uff.twitter.model.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
public class PublicacaoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch(Integer.valueOf(request.getParameter("operacao"))){
            case 0:
                criaPublicacao(request, response);
                break;
            case 1:
                listaPublicacaoUsuario(request,response);
                break;
            case 2:
                removePublicacao(request, response);
                listaPublicacaoUsuario(request, response);
                break;
            case 3:
                criaComentario(request, response);
                break;
            case 4:
                listaTodasPublicacoes(request, response);
                break;
            default:
                break;
        }
    }
    
    private void listaPublicacaoUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("index.jsp");
           return;
       } else{
            // Adiciona o usuário
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

            List<Publicacao> publicacaoList = new ArrayList<>();
            // Recupera publicações de um usuario
            publicacaoList = (new PublicacaoDAO()).listaPorAutor(usuario.getIdUsuario());

            // Para cada publicação, recuperará seus comentários
            for (Publicacao p : publicacaoList){
                System.out.println("p.id: " + p.getIdPublicacao());
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
            response.sendRedirect("index.jsp");
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
            response.sendRedirect("index.jsp");
            return;
        } else{
            // Cria um objeto de acesso ao BD
            PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
            // Chama método para cadastrar usuário
            publicacaoDAO.remove(Integer.valueOf(request.getParameter("idPublicacao")));

            listaPublicacaoUsuario(request,response);
//            try {
//                publicacaoDAO.fechaConexao();
//                listaPublicacao(request,response);
//            } catch (SQLException ex) {
//                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    private void criaPublicacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            
        if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("index.jsp");
            return;
        } else{
            Usuario usuarioLogado = (new UsuarioDAO()).buscaPorEmail(((Usuario) request.getSession().getAttribute("usuarioLogado")).getEmail());
            // Cria um novo usuário com os dados dos Form
            Publicacao publicacao = new Publicacao(
                    request.getParameter("texto"), 
                    (new UsuarioDAO()).buscaPorEmail(usuarioLogado.getEmail()),
                    ((new Date()).getTime()));

            // Cria um objeto de acesso ao BD
            PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
            // Chama método para cadastrar usuário
            publicacaoDAO.adiciona(publicacao);
            
            request.getSession().setAttribute("usuarioLogado", usuarioLogado);
            listaPublicacaoUsuario(request,response);
//            try {
//                publicacaoDAO.fechaConexao();
//    //            request.setAttribute("usuario", String.valueOf((publicacao.getAutor()).getIdUsuario()));
//                request.getSession().setAttribute("usuarioLogado", usuarioLogado);
//                listaPublicacao(request,response);
//            } catch (SQLException ex) {
//                Logger.getLogger(PublicacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
    private void criaComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        if (request.getSession().getAttribute("usuarioLogado") == null){
            response.sendRedirect("index.jsp");
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
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.adiciona(comentario, publicacao);
            
            listaPublicacaoUsuario(request,response);
//            try {
//                comentarioDAO.fechaConexao();
//                listaPublicacao(request,response);
//            } catch (SQLException ex) {
//                Logger.getLogger(PublicacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
