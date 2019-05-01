/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

import br.uff.twitter.controler.NovoUsuarioServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JP
 */
public class UsuarioDAO {
    
    private Connection conn;

    public UsuarioDAO() {
        this.conn = new FabricaConexoes().getConnection();
    }
    
    public void fechaConexao() throws SQLException{
        conn.close();
    }
    
    public void adiciona(Usuario u){     
        String sql = "INSERT INTO usuario "
                + "(nomeCompleto, dataNascimento, apelido, email, senha) "
                + "values (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getNomeCompleto());
            stmt.setString(2, String.valueOf(u.getDataNascimento()));
            stmt.setString(3, u.getApelido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());

            stmt.execute();
            stmt.close();
            System.out.println("Gravado!");
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }      
    }
}
