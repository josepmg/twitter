/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.controler;

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
                System.out.println("Operação Criar");
                criaPublicacao(request, response);
                break;
            case 1:
                System.out.println("Operação Recuperar");
                listaPublicacao(request,response);
                break;
            case 2:
                System.out.println("Operação Deletar");
                removePublicacao(request, response);
                listaPublicacao(request, response);
                break;
            case 3:
                System.out.println("Operação Carregar");
                buscaUmaPublicacao(request, response);
            default:
                break;
        }
    }
    
    private void listaPublicacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Publicacao> publicacaoList = new ArrayList<>();
        publicacaoList = (new PublicacaoDAO()).listaTodos();
        
        // Adiciona a lista de publicações 
        request.setAttribute("publicacoes", publicacaoList);
        // Adiciona o usuário
        
        // Troca de tela pelo Dispatcher
        getServletConfig().getServletContext().getRequestDispatcher("/listaPublicacao.jsp").forward(request, response);
    }

    private void removePublicacao(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buscaUmaPublicacao(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void criaPublicacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            
        // Cria um novo usuário com os dados dos Form
        Publicacao publicacao = new Publicacao(
                request.getParameter("texto"), 
                (new UsuarioDAO()).busca(Integer.valueOf(request.getParameter("idUsuario"))),
                ((new Date()).getTime()));

        // Cria um objeto de acesso ao BD
        PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
        // Chama método para cadastrar usuário
        publicacaoDAO.adiciona(publicacao);

        try {
            publicacaoDAO.fechaConexao();
            listaPublicacao(request,response);
//            request.getRequestDispatcher("/twitter/UsuarioServlet?operacao=1").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PublicacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
