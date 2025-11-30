package main.crud.produto.controller;

import main.crud.produto.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;
    private int proximoId;

    public ClienteController(){
        clientes = new ArrayList<>();
        proximoId = 1;
    }

    public void adicionar(Cliente cliente){
        cliente.setId(proximoId++);
        clientes.add(cliente);
    }

    public List<Cliente> listar(){
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorId(int id){
        return clientes.stream()
                .filter(c->c.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(int id, Cliente clienteAtualizado){
        Cliente cliente = buscarPorId(id);
        if (cliente!=null){
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEmail(clienteAtualizado.getEmail());
            return true;
        }
        return false;
    }

    public boolean remover(int id){
        return clientes.removeIf(c->c.getId()==id);
    }
}
