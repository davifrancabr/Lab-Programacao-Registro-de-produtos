package main.crud.produto.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private Cliente cliente;
    private List<Produto> itens;
    private double total;

    private Produto produto;
    private int quantidade;
    private static int proximoId=1;

    public Carrinho(Cliente cliente){
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.total=0.0;
    }

    public void adicionarItem(Produto produto){
        itens.add(produto);
        calcularTotal();
    }

    public void removerItem(int index){
        if (index>=0 && index<itens.size()){
            itens.remove(index);
            calcularTotal();
        }
    }

    public void limpar(){
        itens.clear();
        total=0.0;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<Produto> getItens(){
        return itens;
    }

    public double getTotal(){
        return total;
    }

    public Produto getProduto(){
        return produto;
    }

    public double getSubtotal(){
        return produto.getPreco() * quantidade;
    }

    private void calcularTotal(){
        total = 0.0;
        for (Produto p : itens){
            total += p.getPreco();

        }
    }

    public String getResumo(){
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESUMO DO PEDIDO ===\n");
        sb.append(cliente.toString()).append("\n\n");
        sb.append("Itens:\n");
        for (int i=0;i<itens.size();i++){
            sb.append(i +1)
                    .append(". ")
                    .append(itens.get(i).getNome())
                    .append(" - R$ ")
                    .append(String.format("%.2f", itens.get(i).getPreco()))
                    .append("\n");
        }
        sb.append("\nTOTAL: R$ ").append(String.format("%.2f", total));
        return sb.toString();
    }

    @Override
    public String toString(){
        return String.format("%dx %s - R$ %.2f",
                quantidade,produto.getNome(), getSubtotal());
    }
}
