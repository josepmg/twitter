/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.twitter.model;

public class Publicacao {

    private int idPublicacao;
    private String titulo;
    private String subtitulo;
    private String texto;
    private int autor;
    private long dataPublicacao;

    public Publicacao() {
    }

    public Publicacao(String titulo, String subtitulo, String texto, int autor, long dataPublicacao) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.texto = texto;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }
    
    public Publicacao(int idPublicacao, String titulo, String subtitulo, String texto, int autor, long dataPublicacao) {
        this.idPublicacao = idPublicacao;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public long getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(long dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    
}
