package com.teste.produto;

import com.crud.produto.controller.ProductController;

public class ProdutoTeste {
    public static void main(String[] args) {

        ProductController p = new ProductController();
        p.adicionarProduto(1,"Davi", 2.5,2);
    }
}
