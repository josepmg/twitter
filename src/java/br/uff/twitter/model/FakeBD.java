package br.uff.twitter.model;

import java.util.ArrayList;
import java.util.List;


public class FakeBD {
    
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<Publicacao> listaPublicacoes = new ArrayList<>();
    private static List<Comentario> listaComentarios = new ArrayList<>();
    private static List<Curtida> listaCurtidas = new ArrayList<>();
    
    
    public void insereUsuario(Usuario u){
        this.listaUsuarios.add(u);
    }
    
    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void inserePublicacao(Publicacao p){
        this.listaPublicacoes.add(p);
    }

    public static List<Publicacao> getListaPublicacoes() {
        return listaPublicacoes;
    }
    
    public void insereComentario(Comentario c){
        this.listaComentarios.add(c);
    }

    public static List<Comentario> getListaComentarios() {
        return listaComentarios;
    }
    
    public void insereCurtida(Curtida c){
        this.listaCurtidas.add(c);
    }

    public static List<Curtida> getListaCurtidas() {
        return listaCurtidas;
    }
    
}
