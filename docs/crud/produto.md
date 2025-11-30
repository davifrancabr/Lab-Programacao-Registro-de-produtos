# ProdutoController - CRUD Completo

#### **CREATE - adicionar(Produto produto)**

- **Descrição**: Adiciona um novo produto à lista de produtos disponíveis
- **Parâmetro**: Objeto Produto completamente preenchido
- **Retorno**: void
- **Uso**: Expansão do cardápio com novos itens

#### **READ - listar()**

- **Descrição**: Retorna uma cópia da lista completa de produtos cadastrados
- **Parâmetro**: Nenhum
- **Retorno**: List\<Produto\> (cópia protegida para evitar modificações externas)
- **Uso**: Carregamento do cardápio na interface gráfica

#### **READ - buscarPorId(int id)**

- **Descrição**: Busca e retorna um produto específico pelo identificador único
- **Parâmetro**: id do produto (int)
- **Retorno**: Produto encontrado ou null
- **Uso**: Consultas específicas e operações de atualização

#### **UPDATE - atualizar(int id, Produto produtoAtualizado)**

- **Descrição**: Substitui os dados de um produto existente, mantendo a quantidade em estoque
- **Parâmetros**: id do produto a atualizar e novo objeto Produto
- **Retorno**: boolean (true se atualizado, false se não encontrado)
- **Uso**: Alteração de preços, descrições e nomes de produtos

#### **DELETE - remover(int id)**

- **Descrição**: Remove um produto da lista pelo identificador
- **Parâmetro**: id do produto (int)
- **Retorno**: boolean (true se removido, false se não encontrado)
- **Uso**: Exclusão de itens descontinuados do cardápio
