# Gerenciamento de produtos

### Estrutura do CRUD:
1. CREATE - Adicionar Produto<br/><strong>Método: <code>adicionar(Produto produto)</code></strong>
   <ul>
        <li>Recebe um objeto Produto como parâmetro</li>
        <li>Adiciona o produto à lista em memória (ArrayList)</li>
        <li>Validações: Nome obrigatório, valores numéricos positivos</li>
   </ul>
   <br/>
2. READ - Listar Produtos<br/><strong>Método: <code>listar()</code></strong>
   <ul>
        <li>Retorna uma cópia da lista de produtos</li>
        <li>Não permite modificação direta da lista interna (encapsulamento)</li>
        <li>Os dados são exibidos em uma JTable na interface gráfica.</li>
   </ul>
    <strong>Método Auxiliar: <code>buscarPorId(int id)</code></strong>
   <ul>
        <li>Busca e retorna um produto específico pelo ID</li>
        <li>Utilizado internamente para operações de atualização e remoção</li>
   </ul>
   <br/>
3. UPDATE - Atualizar Produto<br/><strong>Método:<code>atualizar(int id, Produto produtoAtualizado)</code></strong>
   <ul>
        <li>Localiza o produto pelo ID</li>
        <li>Substitui os dados antigos pelos novos</li>
        <li>Mantém o ID original</li>
        <li>Retorna <code>true</code> se bem-sucedido e retorna <code>false</code> se o produto não foi encontrado</li>
   </ul>
   <br/>
4. DELETE - Remover Produto<br/><strong>Método: <code>remover(int id)</code></strong>
   <ul>
        <li>Remove o produto da lista através do ID</li>
        <li>Utiliza o <code>removeIf()</code> para remover o produto de acordo com a condição</li>
        <li>Exige confirmação do usuário na interface</li>
        <li>Retorna <code>true</code> se removido, <code>false</code> caso contrário</li>
   </ul>   
## Segundo Relatorio

