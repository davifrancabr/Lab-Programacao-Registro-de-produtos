# SISTEMA CARDÁPIO DIGITAL

## 1. ESTRUTURA DO PROJETO

O projeto foi desenvolvido seguindo o padrão arquitetural **MVC (Model-View-Controller)**, organizado em três pacotes principais:

### 1.1 Pacote Model (`main.crud.produto.model`)
Contém as classes de domínio que representam as entidades do sistema:

- **Cliente**: Representa o usuário do sistema, armazenando nome, email e telefone
- **Produto**: Representa os itens do cardápio com id autoincremental, nome, descrição, preço e quantidade em estoque
- **Carrinho**: Agrega os produtos selecionados pelo cliente, calcula o total automaticamente e gera resumo do pedido

### 1.2 Pacote Controller (`main.crud.produto.controller`)
Contém a lógica de negócio e manipulação de dados:

- **ProdutoController**: Gerencia todas as operações CRUD sobre produtos e fornece cardápio pré-carregado
- **CarrinhoController**: Controla o fluxo do carrinho de compras, desde a inicialização até a finalização do pedido

### 1.3 Pacote View (`main.crud.produto.view`)
Contém as interfaces gráficas desenvolvidas com Swing:

- **CadastroCliente**: Tela inicial para cadastro do cliente com validação de campos obrigatórios
- **LojaView**: Interface principal que exibe o cardápio em uma tabela, permite adicionar/remover itens do carrinho e finalizar o pedido

---

## 2. DESCRIÇÃO DOS MÉTODOS CRUD

### 2.1 ProdutoController - Operações CRUD Completas

#### **CREATE - adicionar(Produto produto)**
- **Descrição**: Adiciona um novo produto à lista de produtos disponíveis
- **Parâmetro**: Objeto Produto completamente preenchido
- **Retorno**: void
- **Uso**: Expansão do cardápio com novos itens

#### **READ - listar()**
- **Descrição**: Retorna uma cópia da lista completa de produtos cadastrados
- **Parâmetro**: Nenhum
- **Retorno**: List\<Produto\> (cópia defensiva para evitar modificações externas)
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

### 2.2 CarrinhoController - Operações Específicas

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

---

## 3. DECISÕES DE DESIGN

### 3.1 Arquitetura MVC
**Decisão**: Implementar separação clara entre Model, View e Controller

**Justificativa**:
- Facilita manutenção e expansão do código
- Permite mudanças na interface sem afetar a lógica de negócio
- Possibilita testes unitários isolados de cada camada
- Segue boas práticas de desenvolvimento orientado a objetos

### 3.2 ID Autoincremental em Produto
**Decisão**: Utilizar contador estático para gerar IDs únicos automaticamente

**Justificativa**:
- Elimina necessidade de banco de dados nesta fase
- Garante unicidade dos identificadores
- Simplifica operações de busca e atualização
- Facilita referência aos produtos no sistema

### 3.3 Cardápio Pré-carregado
**Decisão**: Criar método `carregarProdutosIniciais()` no ProdutoController

**Justificativa**:
- Permite demonstração imediata do sistema
- Elimina necessidade de cadastro manual inicial
- Fornece dados realistas para testes
- Melhora experiência do usuário ao iniciar aplicação

### 3.4 Cálculo Automático do Total
**Decisão**: Método privado `calcularTotal()` chamado automaticamente em adição/remoção

**Justificativa**:
- Garante consistência dos dados
- Evita erros de cálculo manual
- Reduz responsabilidade das classes View
- Implementa encapsulamento adequado

### 3.5 Uso de JTable para Exibição
**Decisão**: Utilizar JTable com DefaultTableModel para cardápio e carrinho

**Justificativa**:
- Apresentação organizada e profissional dos dados
- Permite seleção fácil de itens pelo usuário
- Suporta múltiplas colunas de informação
- Facilita atualização dinâmica do conteúdo

### 3.6 Confirmação de Pedido
**Decisão**: Exibir diálogo de confirmação com resumo antes de finalizar

**Justificativa**:
- Evita finalizações acidentais
- Permite revisão final do pedido
- Melhora confiabilidade do sistema
- Segue padrões de UX para operações críticas

### 3.7 Armazenamento em Memória
**Decisão**: Utilizar ArrayList para persistência temporária dos dados

**Justificativa**:
- Simplicidade de implementação
- Adequado para protótipo e demonstração
- Elimina complexidade de conexão com banco de dados
- Facilita compreensão do código para fins educacionais

### 3.8 Imutabilidade das Tabelas
**Decisão**: Sobrescrever `isCellEditable()` retornando false

**Justificativa**:
- Previne edição acidental de dados nas tabelas
- Mantém integridade das informações exibidas
- Força uso dos botões específicos para operações CRUD
- Melhora controle do fluxo de dados

### 3.9 Fluxo Linear de Navegação
**Decisão**: CadastroCliente → LojaView com dispose() após transição

**Justificativa**:
- Cria experiência guiada para o usuário
- Libera recursos da janela anterior
- Evita confusão com múltiplas janelas abertas
- Simula fluxo real de aplicação de pedidos

---

## 4. CONCLUSÃO

O sistema implementa com sucesso todos os requisitos de um CRUD completo aplicado ao contexto de um cardápio digital. A arquitetura MVC garante organização, manutenibilidade e escalabilidade. As decisões de design priorizaram simplicidade, experiência do usuário e boas práticas de programação orientada a objetos.

O projeto demonstra domínio dos conceitos de POO (encapsulamento, herança de JFrame, polimorfismo nos models) e desenvolvimento de interfaces gráficas com Swing. A estrutura modular permite fácil expansão futura, como implementação de persistência em banco de dados, autenticação de usuários, controle de estoque em tempo real e integração com sistemas de pagamento.
