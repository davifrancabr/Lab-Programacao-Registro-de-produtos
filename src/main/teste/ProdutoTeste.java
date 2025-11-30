package main.teste;

import main.crud.produto.controller.ProdutoController;
import main.crud.produto.model.Produto;
import java.util.List;

public class ProdutoTeste {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   TESTE BÁSICO DO CRUD DE PRODUTOS");
        System.out.println("========================================\n");

        ProdutoController controller = new ProdutoController();

        int testesPassados = 0;
        int testesFalhados = 0;

        System.out.println("📋 TESTE 1: Listar produtos iniciais");
        System.out.println("-------------------------------------");
        List<Produto> produtos = controller.listar();
        System.out.println("Total de produtos no cardápio: " + produtos.size());

        if (produtos.size() == 8) {
            System.out.println("✅ PASSOU - 8 produtos carregados corretamente\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Esperado 8 produtos, encontrado " + produtos.size() + "\n");
            testesFalhados++;
        }

        System.out.println("Produtos cadastrados:");
        for (Produto p : produtos) {
            System.out.println("  - " + p.getNome() + " | R$ " + p.getPreco());
        }
        System.out.println();

        System.out.println("➕ TESTE 2: Adicionar novo produto");
        System.out.println("-------------------------------------");
        Produto novoProduto = new Produto("Sorvete", "Casquinha 2 bolas", 10.00, 25);
        controller.adicionar(novoProduto);

        List<Produto> produtosAposAdicao = controller.listar();
        System.out.println("Total de produtos após adição: " + produtosAposAdicao.size());

        boolean produtoEncontrado = false;
        for (Produto p : produtosAposAdicao) {
            if (p.getNome().equals("Sorvete")) {
                produtoEncontrado = true;
                System.out.println("Produto adicionado: " + p.getNome() + " (ID: " + p.getId() + ")");
                break;
            }
        }

        if (produtosAposAdicao.size() == 9 && produtoEncontrado) {
            System.out.println("✅ PASSOU - Produto adicionado com sucesso\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Produto não foi adicionado corretamente\n");
            testesFalhados++;
        }

        System.out.println("🔍 TESTE 3: Buscar produto por ID");
        System.out.println("-------------------------------------");
        Produto produtoBuscado = controller.buscarPorId(1);

        if (produtoBuscado != null) {
            System.out.println("Produto encontrado (ID 1): " + produtoBuscado.getNome());
            System.out.println("✅ PASSOU - Busca por ID funcionou\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Produto com ID 1 não foi encontrado\n");
            testesFalhados++;
        }

        System.out.println("✏️ TESTE 4: Atualizar produto existente");
        System.out.println("-------------------------------------");
        Produto produtoAtualizado = new Produto("X-Burger Premium", "Hambúrguer gourmet com queijo especial", 30.00, 50);
        boolean atualizou = controller.atualizar(1, produtoAtualizado);

        if (atualizou) {
            Produto produtoVerificado = controller.buscarPorId(1);

            if (produtoVerificado != null) {
                System.out.println("Produto após atualização:");
                System.out.println("  Nome: " + produtoVerificado.getNome());
                System.out.println("  Descrição: " + produtoVerificado.getDescricao());
                System.out.println("  Preço: R$ " + produtoVerificado.getPreco());

                if (produtoVerificado.getNome().equals("X-Burger Premium") &&
                        produtoVerificado.getPreco() == 30.00) {
                    System.out.println("✅ PASSOU - Produto atualizado corretamente\n");
                    testesPassados++;
                } else {
                    System.out.println("❌ FALHOU - Dados não foram atualizados corretamente\n");
                    testesFalhados++;
                }
            } else {
                System.out.println("❌ FALHOU - Produto não encontrado após atualização (retornou null)\n");
                testesFalhados++;
            }
        } else {
            System.out.println("❌ FALHOU - Não foi possível atualizar o produto\n");
            testesFalhados++;
        }

        System.out.println("🗑️ TESTE 5: Remover produto");
        System.out.println("-------------------------------------");
        int tamanhoAntes = controller.listar().size();
        boolean removeu = controller.remover(9);
        int tamanhoDepois = controller.listar().size();

        System.out.println("Produtos antes da remoção: " + tamanhoAntes);
        System.out.println("Produtos após remoção: " + tamanhoDepois);

        if (removeu && tamanhoDepois == tamanhoAntes - 1) {
            System.out.println("✅ PASSOU - Produto removido com sucesso\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Produto não foi removido\n");
            testesFalhados++;
        }

        System.out.println("⚠️ TESTE 6: Tentar remover produto inexistente");
        System.out.println("-------------------------------------");
        boolean removeuInexistente = controller.remover(999);

        if (!removeuInexistente) {
            System.out.println("✅ PASSOU - Sistema detectou que produto não existe\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Sistema não validou ID inexistente\n");
            testesFalhados++;
        }

        System.out.println("🔒 TESTE 7: Verificar integridade final da lista");
        System.out.println("-------------------------------------");
        List<Produto> produtosFinais = controller.listar();
        System.out.println("Total final de produtos: " + produtosFinais.size());

        boolean integridadeOk = true;
        for (Produto p : produtosFinais) {
            if (p.getId() <= 0 || p.getNome() == null || p.getPreco() < 0) {
                integridadeOk = false;
                break;
            }
        }

        if (integridadeOk && produtosFinais.size() == 8) {
            System.out.println("✅ PASSOU - Lista íntegra com 8 produtos válidos\n");
            testesPassados++;
        } else {
            System.out.println("❌ FALHOU - Problemas na integridade da lista\n");
            testesFalhados++;
        }

        System.out.println("========================================");
        System.out.println("          RESUMO DOS TESTES");
        System.out.println("========================================");
        System.out.println("✅ Testes Passados: " + testesPassados);
        System.out.println("❌ Testes Falhados: " + testesFalhados);
        System.out.println("📊 Total de Testes: " + (testesPassados + testesFalhados));

        double percentual = (testesPassados * 100.0) / (testesPassados + testesFalhados);
        System.out.println("📈 Taxa de Sucesso: " + String.format("%.1f%%", percentual));
        System.out.println("========================================");

        if (testesFalhados == 0) {
            System.out.println("\n🎉 TODOS OS TESTES PASSARAM! 🎉\n");
        } else {
            System.out.println("\n⚠️ ALGUNS TESTES FALHARAM ⚠️\n");
        }

        System.out.println("\n📋 LISTAGEM FINAL DE PRODUTOS:");
        System.out.println("========================================");
        for (Produto p : produtosFinais) {
            System.out.println(p.toString());
        }
        System.out.println("========================================");
    }
}
