/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JP
 */
public class PublicacaoDAO {
    
    private Connection conn;

    public PublicacaoDAO() {
        this.conn = new FabricaConexoes().getConnection();
    }
    
    public void fechaConexao() throws SQLException{
        conn.close();
    }
    
    public void adiciona(Publicacao p){     
        String sql = "INSERT INTO publicacao "
                + "(texto, autor, dataPublicaco) "
                + "values (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getTexto());
            stmt.setString(2, String.valueOf((p.getAutor()).getIdUsuario()));
            // Cria um formato para data
            stmt.setString(3, String.valueOf(p.getDataPublicacao()));

            stmt.execute();
            stmt.close();
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }      
    }
    
    public List<Publicacao> listaTodos(){        
        try {
            // Cria uma lista de publicações
            List<Publicacao> publicacaoList = new ArrayList<>();
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM publicacao "
                    + "ORDER BY dataPublicaco DESC "
                    + "LIMIT 10");
            // Cria uma varíavel para receber o resultado da Query
            ResultSet rs = stmt.executeQuery();
            
            // laço de repetição para percorrer todas as intâncias do ResultSet
            while (rs.next()) {
                publicacaoList.add(new Publicacao(
                        rs.getInt("idPublicacao"), 
                        rs.getString("texto"), 
                        (new UsuarioDAO()).busca(rs.getInt("autor")), 
                        rs.getLong("dataPublicaco")
                ));
               
            }
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            // Retorna a lista de Usuários do BD
            return publicacaoList;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }  
    }
    
    public Publicacao busca(int id){
        try {
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM publicacao WHERE idPublicacao = ?");
            stmt.setString(1, String.valueOf(id));
            // Cria uma varíavel para receber o resultado da Query
            ResultSet rs = stmt.executeQuery();
            
            // Cria usuário encontrado
            Publicacao publicacao = null;
            // Verifica se houve algum retorno
            if (rs.next()){
                publicacao = new Publicacao(
                        rs.getInt("idPublicacao"), 
                        rs.getString("texto"), 
                        (new UsuarioDAO()).busca(rs.getInt("idUsuario")), 
                        rs.getLong("dataPublicaco"));
            }
            else{
                System.out.println("Não há registros para id " + id);
            }
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            // Retorna a lista de Usuários do BD
            return publicacao;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void remove(int id){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM publicacao WHERE idPublicacao = ?");
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
