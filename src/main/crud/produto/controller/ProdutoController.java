package main.crud.produto.controller;

import main.crud.produto.model.Produto;

import java.util.ArrayList;
import java.util.List;

// TODO: Implementar com a classe model quando estiver pronto
public class ProdutoController {
    private List<Produto> produtos;

    public ProdutoController() {

        produtos = new ArrayList<>();
    }

    public void adicionar(Produto produto) {
        if (produto != null) {
            produtos.add(produto);
        }
    }

    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(int id, Produto produtoAtualizado) {
        for (int i=0; i<produtos.size();i++){
            if (produtos.get(i).getId() == id){
                produtoAtualizado.setId(id);
                produtos.set(i, produtoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean remover(int id) {
        return produtos.removeIf(p->p.getId()==id);
    }

    public int totalProdutos(){

        return produtos.size();
    }

    public boolean existeProduto(int id){
        return buscarPorId(id) != null;

    }
}