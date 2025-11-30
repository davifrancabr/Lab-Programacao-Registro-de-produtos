package main.crud.produto.controller;

import main.crud.produto.model.Carrinho;
import main.crud.produto.model.Cliente;
import main.crud.produto.model.Produto;

public class CarrinhoController {
    private Carrinho carrinhoAtual;

    public void iniciarNovoCarrinho(Cliente cliente){
        this.carrinhoAtual = new Carrinho(cliente);
    }

    public void adicionarAoCarrinho(Produto produto){
        if (carrinhoAtual !=null){
            carrinhoAtual.adicionarItem(produto);
        }
    }

    public void removerDoCarrinho(int index){
        if (carrinhoAtual!= null){
            carrinhoAtual.removerItem(index);
        }
    }

    public Carrinho getCarrinhoAtual(){
        return carrinhoAtual;
    }

    public void finalizarPedido(){
        if (carrinhoAtual != null){
            System.out.println("\n" + carrinhoAtual.getResumo());
            System.out.println("\n📧 E-mail enviado para: " +
                    carrinhoAtual.getCliente().getEmail());
        }
    }
}
