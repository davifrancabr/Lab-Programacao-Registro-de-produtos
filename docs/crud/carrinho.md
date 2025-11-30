# CarrinhoControle - Operações específicas

#### **iniciarNovoCarrinho(Cliente cliente)**

- Cria uma nova instância de Carrinho vinculada ao cliente
- Inicializa lista vazia de itens e total zerado

#### **adicionarAoCarrinho(Produto produto)**

- Adiciona um produto ao carrinho atual
- Recalcula automaticamente o total através do método privado calcularTotal()

#### **removerDoCarrinho(int index)**

- Remove item do carrinho pela posição na lista
- Atualiza o total automaticamente

#### **finalizarPedido()**

- Imprime resumo completo do pedido no console
- Simula envio de email de confirmação
- Limpa o carrinho após confirmação
