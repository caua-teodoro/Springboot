package br.edu.fundamentos.alto_aclopamento;

public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = false;

    public Cliente(String nome, String email, String telefone){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public boolean isAtivo(){
        return this.ativo;
    }

    public void ativar(){
        this.ativo = true;
    }

}