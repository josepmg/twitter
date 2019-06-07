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
            stmt.setLong(2, u.getDataNascimento());
            stmt.setString(3, u.getApelido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());

            stmt.execute();
            stmt.close();
            fechaConexao();
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }      
    }
    
    public List<Usuario> listaTodos(){        
        try {
            // Cria uma lista de usuários
            List<Usuario> usuariosList = new ArrayList<>();
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
            // Fecha conexão
            fechaConexao();
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
            stmt.setInt(1, id);
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
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            
            fechaConexao();
            // Retorna a lista de Usuários do BD
            return usuario;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } 
    }
    
    public Usuario buscaPorEmail(String email){
        try {
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            stmt.setString(1, email);
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
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            
            fechaConexao();
            // Retorna a lista de Usuários do BD
            return usuario;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } 
    }
    
    public Usuario buscaLogin(String email, String senha){
        try {
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM usuario WHERE "
                    + "	email = ? and "
                    + "senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
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
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            
            fechaConexao();
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
            stmt.setLong(2, u.getDataNascimento());
            stmt.setString(3, u.getApelido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());
            stmt.setString(6, String.valueOf(u.getIdUsuario()));
            System.out.println(stmt.toString());
            stmt.execute();
            stmt.close();
            fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alteraSenha(Usuario u){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE usuario SET "
                    + "senha = ? "
                    + "WHERE idUsuario = ?");
            stmt.setString(1, u.getSenha());
            stmt.setInt(2, (u.getIdUsuario()));
            stmt.execute();
            stmt.close();
            fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(int id){
        try {
            
            (new ComentarioDAO()).removePorAutor(id);
            (new PublicacaoDAO()).removePorAutor(id);
            
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            fechaConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
