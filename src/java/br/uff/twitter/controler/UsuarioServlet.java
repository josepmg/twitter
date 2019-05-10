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
import java.util.ArrayList;
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
public class UsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch(Integer.valueOf(request.getParameter("operacao"))){
            case 0:
                criaUsuario(request, response);
                break;
            case 1:
                listaUsuarios(request,response);
                break;
            case 2:
                atualizaUsuario(request, response);
                listaUsuarios(request, response);
                break;
            case 3:
                removeUsuario(request, response);
                listaUsuarios(request, response);
                break;
            case 4:
                buscaUmUsuario(request, response);
            default:
                break;
        }
    }
    
    private void criaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        // Cria um novo usuário com os dados dos Form
        Usuario usuario = new Usuario(request.getParameter("nomeCompleto"),
                Long.parseLong(request.getParameter("dataNascimento")),
                request.getParameter("apelido"), 
                request.getParameter("email"), 
                request.getParameter("senha"));

        // Cria um objeto de acesso ao BD
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // Chama método para cadastrar usuário
        usuarioDAO.adiciona(usuario);

        try {
            usuarioDAO.fechaConexao();
            listaUsuarios(request,response);
//            request.getRequestDispatcher("/twitter/UsuarioServlet?operacao=1").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        List<Usuario> usuariosList = new ArrayList<Usuario>();
        usuariosList = (new UsuarioDAO()).listaTodos();
        
        // Adiciona a lista de usuário 
        request.setAttribute("usarios", usuariosList);
        // Troca de tela pelo Dispatcher
        getServletConfig().getServletContext().getRequestDispatcher("/listaUsuariosJSP.jsp").forward(request, response);
    }
    
    private void removeUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        // Cria um objeto de acesso ao BD
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // Chama método para cadastrar usuário
        usuarioDAO.remove(Integer.valueOf(request.getParameter("id")));

        try {
            usuarioDAO.fechaConexao();
            listaUsuarios(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void atualizaUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usuario = new Usuario(
                request.getParameter("nomeCompleto"),
                Long.parseLong(request.getParameter("dataNascimento")),
                request.getParameter("apelido"), 
                request.getParameter("email"), 
                request.getParameter("senha"));
        usuario.setIdUsuario(Integer.valueOf(request.getParameter("idUsuario")));
        
        // Chama método para cadastrar usuário
        usuarioDAO.altera(usuario);

        try {
            usuarioDAO.fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private void buscaUmUsuario(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
        Usuario usuario = new Usuario();
        usuario = (new UsuarioDAO()).busca(Integer.valueOf(request.getParameter("id")));
        
        request.setAttribute("usarioEncontrado", usuario);
        // Troca de tela pelo Dispatcher
        getServletConfig().getServletContext().getRequestDispatcher("/editaUsuarioJSP.jsp").forward(request, response);
        
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
