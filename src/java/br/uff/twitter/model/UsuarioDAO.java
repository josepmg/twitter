/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, u.getNomeCompleto());
            stmt.setString(2, String.valueOf(u.getDataNascimento()));
            stmt.setString(3, u.getApelido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());

            stmt.execute();
            stmt.close();
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }      
    }
    
    public List<Usuario> listaTodos(){        
        try {
            // Cria uma lista de usuários
            List<Usuario> usuariosList = new ArrayList<Usuario>();
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM usuario");
            // Cria uma varíavel para receber o resultado da Query
            ResultSet rs = stmt.executeQuery();
            
            // laço de repetição para percorrer todas as intâncias do ResultSet
            while (rs.next()) {
                usuariosList.add(new Usuario(
                        rs.getInt("idUsuario"), 
                        rs.getString("nomeCompleto"), 
                        rs.getLong("dataNascimento"), 
                        rs.getString("apelido"), 
                        rs.getString("email"), 
                        rs.getString("senha")));                
            }
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            // Retorna a lista de Usuários do BD
            return usuariosList;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }  
    }
    
    public Usuario busca(int id){
        try {
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?");
            stmt.setString(1, String.valueOf(id));
            // Cria uma varíavel para receber o resultado da Query
            ResultSet rs = stmt.executeQuery();
            
            // Cria usuário encontrado
            Usuario usuario = null;
            // Verifica se houve algum retorno
            if (rs.next()){
                usuario = new Usuario(
                        rs.getInt("idUsuario"), 
                        rs.getString("nomeCompleto"), 
                        rs.getLong("dataNascimento"), 
                        rs.getString("apelido"), 
                        rs.getString("email"), 
                        rs.getString("senha"));
            }
            else{
                System.out.println("Não há registros para id " + id);
            }
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            // Retorna a lista de Usuários do BD
            return usuario;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void altera(Usuario u){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE usuario "
                    + "set nomeCompleto = ?, "
                    + "dataNascimento = ?, "
                    + "apelido = ?, "
                    + "email = ?, "
                    + "senha = ? "
                    + "WHERE idUsuario = ?");
            stmt.setString(1, u.getNomeCompleto());
            stmt.setString(2, String.valueOf(u.getDataNascimento()));
            stmt.setString(3, u.getApelido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());
            stmt.setString(6, String.valueOf(u.getIdUsuario()));
            System.out.println(stmt.toString());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(int id){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
