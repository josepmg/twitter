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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JP
 */
@WebServlet(name = "ListaUsuariosServlet", urlPatterns = {"/ListaUsuarios"})
public class ListaUsuariosServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Usuario> usuariosList = new ArrayList<Usuario>();
        usuariosList = (new UsuarioDAO()).listaTodos();
        for (Usuario u : usuariosList){
            System.out.println(u.getIdUsuario() + ": " + u.getNomeCompleto());
        }
        // Adiciona a lista de usuário 
        request.setAttribute("usarios", usuariosList);
        // Troca de tela pelo Dispatcher
        getServletConfig().getServletContext().getRequestDispatcher("/listaUsuariosJSP.jsp").forward(request, response);
    }

}
