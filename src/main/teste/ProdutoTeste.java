package main.teste;

import main.crud.produto.controller.ProdutoController;
import main.crud.produto.model.Produto;

public class ProdutoTeste {
    public static void main(String[] args){
        ProdutoController controller = new ProdutoController();
        Produto produto1 = new Produto("Hamburguer", "Hamburguer com cheddar", 13.50,50);
        Produto produto2 = new Produto("Filé Mignom", "Filé Mignom com Batata Frita", 45.50,50);
        System.out.println(produto1.toString());
        System.out.println(produto2.toString());
    }
}
