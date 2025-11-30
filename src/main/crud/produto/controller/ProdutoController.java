package main.crud.produto.controller;

import main.crud.produto.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private List<Produto> produtos;

    public ProdutoController(){
        this.produtos = new ArrayList<>();
        carregarCardapioInicial();
    }

    private void carregarCardapioInicial(){
        produtos.add(new Produto("X-Burger", "Hambúrguer clássico com queijo", 25.00, 50));
        produtos.add(new Produto("X-Bacon", "Hambúrguer com bacon crocante", 28.00, 40));
        produtos.add(new Produto("X-Salada", "Hambúrguer com salada completa", 27.00, 45));
        produtos.add(new Produto("Pizza Margherita", "Pizza tradicional italiana", 45.00, 30));
        produtos.add(new Produto("Pizza Calabresa", "Pizza com calabresa e cebola", 48.00, 35));
        produtos.add(new Produto("Refrigerante", "Lata 350ml", 5.00, 100));
        produtos.add(new Produto("Suco Natural", "Laranja, limão ou maracujá", 8.00, 60));
        produtos.add(new Produto("Batata Frita", "Porção 300g", 15.00, 50));
    }
    public void adicionar(Produto produto){
        produtos.add(produto);
    }

    public List<Produto> listar(){
        return new ArrayList<>(produtos);
    }

    public boolean atualizar(int id, Produto produtoAtualizado){
        for (int i = 0;i<produtos.size();i++){
            if (produtos.get(i).getId()==id){
                produtoAtualizado.setQuantidade(produtos.get(i).getQuantidade());
                produtos.set(i, produtoAtualizado);
                return true;
            }

        }
        return false;
    }

    public boolean remover(int id){
        return produtos.removeIf(p->p.getId()==id);
    }
    public Produto buscarPorId(int id){
        for (Produto p: produtos){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }
}