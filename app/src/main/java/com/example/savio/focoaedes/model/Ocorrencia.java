package com.example.savio.focoaedes.model;


@SuppressWarnings("ALL")
public class Ocorrencia {


    String id, caminho_foto, titulo, data, bairro, endereco, telefone, email, descricao;


    //--------------Construtores------------------------------------------------------------------------//


    public Ocorrencia(){

    }

    //construtor para update
    public Ocorrencia(String id, String foto, String titulo, String data, String bairro, String rua, String telefone, String email, String descricao){
        this.id = id; this.caminho_foto = foto; this.titulo = titulo; this.data = data; this.bairro = bairro;
        this.endereco = rua; this.telefone = telefone; this.email = email; this.descricao = descricao;
    }

    //construtor para insert
    public Ocorrencia(String foto, String titulo, String data, String bairro, String rua, String telefone, String email, String descricao){
        this.caminho_foto = foto; this.titulo = titulo; this.data = data; this.bairro = bairro;
        this.endereco = rua; this.telefone = telefone; this.email = email; this.descricao = descricao;
    }


//--------------Getters n Setters-------------------------------------------------------------------//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return caminho_foto;
    }

    public void setFoto(String foto) {
        this.caminho_foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return endereco;
    }

    public void setRua(String rua) {
        this.endereco = rua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
