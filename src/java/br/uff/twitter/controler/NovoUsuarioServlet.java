/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.controler;

import br.uff.twitter.model.FabricaConexoes;
import br.uff.twitter.model.FakeBD;
import br.uff.twitter.model.Usuario;
import br.uff.twitter.model.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class NovoUsuarioServlet extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Usuario usuario = new Usuario(request.getParameter("nomeCompleto"),
                Long.parseLong(request.getParameter("dataNascimento")),
                request.getParameter("apelido"), 
                request.getParameter("email"), 
                request.getParameter("senha"));
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.adiciona(usuario);
        try {
            usuarioDAO.fechaConexao();
            
            
//        request.setAttribute("usuarios", fakeBD.getListaUsuarios());
//        request.getRequestDispatcher("/listaUsuariosJSP.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NovoUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
