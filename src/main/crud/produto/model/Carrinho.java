package main.crud.produto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrinho {
    private Cliente cliente;
    private Map<Integer, ItemCarrinho> itens;
    private double total;

    public Carrinho(Cliente cliente){
        this.cliente = cliente;
        this.itens = new HashMap<>();
        this.total=0.0;
    }


    public void adicionarItem(Produto produto, int quantidade){
        int productId = produto.getId();
        
        if (itens.containsKey(productId)){
            ItemCarrinho item = itens.get(productId);
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            itens.put(productId, new ItemCarrinho(produto, quantidade));
        }

        calcularTotal();
    }

    public void atualizarQuantidade(int produtoId, int novaQuantidade){
        if (itens.containsKey(produtoId)){
            if (novaQuantidade<=0){
                removerItem(produtoId);
            }else {
                itens.get(produtoId).setQuantidade(novaQuantidade);
                calcularTotal();
            }
        }

    }

    public void removerItem(int produtoId){
        itens.remove(produtoId);
        calcularTotal();
    }

    public void limpar(){
        itens.clear();
        total=0.0;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<ItemCarrinho> getItens(){
        return new ArrayList<>(itens.values());
    }


    public int getQuantidadeProduto(int produtoId){
        if (itens.containsKey(produtoId)){
            return itens.get(produtoId).getQuantidade();
        }
        return 0;
    }


    private void calcularTotal(){
        total = 0.0;
        for (ItemCarrinho item : itens.values()){
            total += item.getSubtotal();
        }
    }

    public double getTotal(){
        return total;
    }

    public boolean isEmpty(){
        return itens.isEmpty();
    }

    public String getResumo(){
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESUMO DO PEDIDO ===\n");
        sb.append(cliente.toString()).append("\n\n");
        sb.append("Itens:\n");

        int contador = 1;
        for (ItemCarrinho item : itens.values()){
            sb.append(contador++)
                    .append(". ")
                    .append(item.getProduto().getNome())
                    .append(" x").append(item.getQuantidade())
                    .append(" - R$ ")
                    .append(String.format("%.2f", item.getSubtotal()))
                    .append("\n");
        }
        sb.append("\nTOTAL: R$ ").append(String.format("%.2f", total));
        return sb.toString();
    }
}
