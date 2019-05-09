/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

public class Publicacao {

    private int idPublicacao;
    private String texto;
    private Usuario autor;
    private long dataPublicacao;

    public Publicacao() {
    }

    public Publicacao(String texto, Usuario autor, long dataPublicacao) {
        this.texto = texto;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }
    
    public Publicacao(int idPublicacao, String texto, Usuario autor, long dataPublicacao) {
        this.idPublicacao = idPublicacao;
        this.texto = texto;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public long getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(long dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    
}
