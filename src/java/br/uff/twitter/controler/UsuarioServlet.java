/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.controler;

import br.uff.twitter.model.Usuario;
import br.uff.twitter.model.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JP
 */
public class UsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        switch(request.getParameter("acao")){
//            case 0:
            case "criaUsuario":
                criaUsuario(request, response);
                break;
//            case 2:
            case "atualizaUsuario":
                atualizaUsuario(request, response);
//                listaUsuarios(request, response);
                break;
//            case 3:
            case "trocaTela":
                trocaTela(request, response);
                break;
//            case 5:
            case "fazLogin":
                fazLogin(request, response);
                break;
//            case 6:
            case "fazLogout":
                fazLogout(request, response);
                break;
//            case 7:
            case "alteraSenha":
                alteraSenha(request, response);
                break;
            default:
                break;
        }
    }
    
    private void criaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, ParseException{
        
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));
        // Cria um novo usuário com os dados dos Form
        Usuario usuario = new Usuario(request.getParameter("nomeCompleto"),
                date.getTime(),
                request.getParameter("apelido"), 
                request.getParameter("email"), 
                request.getParameter("senha"));

        // Cria um objeto de acesso ao BD
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // Chama método para cadastrar usuário
        usuarioDAO.adiciona(usuario);

        request.getSession().setAttribute("usuarioLogado", (new UsuarioDAO()).buscaPorEmail(usuario.getEmail()));
        getServletConfig().getServletContext().getRequestDispatcher("/publicacaoServlet?acao=listaTodasPublicacoes").forward(request, response);
    }
    
    private void atualizaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, ParseException{
        if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("/twitter/index.jsp");
           return;
       } else{      
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));

            Usuario usuario = new Usuario(
                    request.getParameter("nomeCompleto"),
                    date.getTime(),
                    request.getParameter("apelido"), 
                    request.getParameter("email"), 
                    request.getParameter("senha"));
            usuario.setIdUsuario(Integer.valueOf(request.getParameter("idUsuario")));            

            // Chama método para cadastrar usuário
            usuarioDAO.altera(usuario);

            request.getSession().setAttribute("usuarioLogado", usuario);
            response.sendRedirect("/twitter/publicacaoServlet?acao=listaTodasPublicacoes");
        }
    }   
    
    private void alteraSenha(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        // Recupera o usuario da session http
        Usuario usuario = (new UsuarioDAO()).buscaPorEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        
        if(request.getParameter("senha").equals(request.getParameter("confirmasenha"))){
            // Chama método para cadastrar usuário
            (new UsuarioDAO()).alteraSenha(usuario);

            response.sendRedirect("/twitter/index.jsp");
        } else{
            response.sendRedirect("/twitter/recuperarsenha.jsp");
        }
    }
    
    private void fazLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (new UsuarioDAO()).buscaLogin(
                request.getParameter("email"), 
                request.getParameter("senha")
        );
        
        if (usuario !=  null){
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
        request.getSession().invalidate();
        response.sendRedirect("/twitter/index.jsp");
    }
    
    private void trocaTela(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        if (request.getSession().getAttribute("usuarioLogado") == null){
           response.sendRedirect("/twitter/index.jsp");
           return;
       } else{
            response.sendRedirect("/twitter/conta.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
