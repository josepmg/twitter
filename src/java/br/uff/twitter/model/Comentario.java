/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

public class Comentario {
    
    private int idComentario;
    private String texto;
    private long dataComentario;
    private Usuario autor;
//    private int publicacao;

    public Comentario() {
    }

    public Comentario(String texto, long dataComentario , Usuario autor /*, int publicacao */) {
        this.texto = texto;
        this.dataComentario = dataComentario;
        this.autor = autor;
//        this.publicacao = publicacao;
    }
    
    public Comentario(int idComentario, String texto, long dataComentario , Usuario autor /*, int publicacao*/) {
        this.idComentario = idComentario;
        this.texto = texto;
        this.dataComentario = dataComentario;
        this.autor = autor;
//        this.publicacao = publicacao;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(long dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

//    public int getPublicacao() {
//        return publicacao;
//    }
//
//    public void setPublicacao(int publicacao) {
//        this.publicacao = publicacao;
//    }
    
    
}
