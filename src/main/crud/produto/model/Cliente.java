package main.crud.produto.model;

public class Cliente {
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String nome, String telefone, String email){
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
    }

    public String getNome(){
        return nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setTelefone(String telefone){
        this.telefone=telefone;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public String toString(){
        return String.format("Cliente: %s | Tel: %s | Email: %s", nome, telefone, email);
    }
}
