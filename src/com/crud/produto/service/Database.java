package com.crud.produto.service;



import java.util.ArrayList;
import java.util.List;

public class Database  {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    List<Database> databases = new ArrayList<Database>();
    public Database(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    private void addDatabase(int id, String nome, double preco, int quantidade){
        databases.add(new Database(this.id,this.nome, this.preco,this.quantidade));
    }


    public void adicionarDatabase(int id, String nome, double preco, int quantidade) {
        this.addDatabase(id, nome, preco, quantidade);
    }
}
