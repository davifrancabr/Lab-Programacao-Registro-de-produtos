package main.crud.produto.model;

public class Produto {
    private static int proximoId=1;
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    public Produto(String nome, String descricao, double preco, int quantidade){
        this.id=proximoId++;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setPreco(double preco){
        this.preco=preco;
    }

    public void setDescricao(String descricao){
        this.descricao=descricao;
    }

    public void setQuantidade(int quantidade){
        this.quantidade=quantidade;
    }

    @Override
    public String toString(){
        return String.format("ID: %d | %s - %s | R$ %.2f | Estoque: %d",
                id, nome,descricao,preco,quantidade);
    }
}
