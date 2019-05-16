package br.uff.twitter.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioDAO {

    private Connection conn;

    public ComentarioDAO() {
        this.conn = new FabricaConexoes().getConnection();
    }
    
    public void fechaConexao() throws SQLException{
        conn.close();
    }
    
    public void adiciona(Comentario c, Publicacao p){     
        String sql = "INSERT INTO comentario "
                + "(texto, autor, publicacao, dataPublicacao) "
                + "values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, c.getTexto());
            stmt.setInt(2, (c.getAutor()).getIdUsuario());
            stmt.setInt(3, p.getIdPublicacao());
            stmt.setLong(4, c.getDataComentario());

            stmt.execute();
            stmt.close();
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }      
    }
    
    public List<Comentario> listaPorPublicacao(int idPublicacao){
        try {
            // Cria uma lista de publicações
            List<Comentario> comentarioList = new ArrayList<>();
            // Cria o statment que contém a Query de consulta
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM comentario "
                    + "WHERE publicacao = ? "
                    + "ORDER BY dataPublicacao DESC "
                    + "LIMIT 10");
            stmt.setInt(1, idPublicacao);
            // Cria uma varíavel para receber o resultado da Query
            ResultSet rs = stmt.executeQuery();
            
            // laço de repetição para percorrer todas as intâncias do ResultSet
            while (rs.next()) {
                comentarioList.add(new Comentario(
                        rs.getInt("idComentario"), 
                        rs.getString("texto"),  
                        rs.getLong("dataPublicacao"), 
                        (new UsuarioDAO()).busca(rs.getInt("autor"))
                ));  
            }
            // Encerra o ResultSet
            rs.close();
            // Encerra o Statment
            stmt.close();
            // Retorna a lista de Usuários do BD
            return comentarioList;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }
    
    public  void removePorAutor(int idUsuario){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM comentario WHERE autor = ?");
            stmt.setInt(1, idUsuario);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public  void removePorPublicacao(int idPublicacao){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM comentario WHERE publicacao = ?");
            stmt.setInt(1, idPublicacao);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
