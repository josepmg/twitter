/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

public class Curtida {
    
    private int idCurtida;
    private int autor;
    private int publicacao;

    public Curtida() {
    }

    public Curtida(int autor, int publicacao) {
        this.autor = autor;
        this.publicacao = publicacao;
    }

    public Curtida(int idCurtida, int autor, int publicacao) {
        this.idCurtida = idCurtida;
        this.autor = autor;
        this.publicacao = publicacao;
    }

    public int getIdCurtida() {
        return idCurtida;
    }

    public void setIdCurtida(int idCurtida) {
        this.idCurtida = idCurtida;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(int publicacao) {
        this.publicacao = publicacao;
    }
    
    
    
}
