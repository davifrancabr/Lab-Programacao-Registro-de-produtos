package main.crud.produto.model;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private static int proximoId=1;

    public Cliente(String nome, String telefone, String email){
        this.id=proximoId++;
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
    }

    public Cliente(int id, String nome, String telefone, String email){
        this.id=id;
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
    }

    public int getId(){
        return id;
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

    public void setId(int id){
        this.id=id;
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
        return String.format("ID: %d | %s - Tel: %s", id, nome, email);
    }
}
