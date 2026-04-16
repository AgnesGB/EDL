package EDNL.RUBRO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========== ÁRVORE RUBRO-NEGRA ==========");
        System.out.println("1. Modo Automático (Testes Pré-definidos)");
        System.out.println("2. Modo Interativo (Menu Manual)");
        System.out.print("Escolha uma opção: ");
        
        int modo = scanner.nextInt();
        
        if (modo == 1) {
            modoAutomatico();
        } else if (modo == 2) {
            modoInterativo();
        } else {
            System.out.println("Opção inválida!");
        }
        
        scanner.close();
    }
    
    // ===== MODO AUTOMÁTICO =====
    private static void modoAutomatico() {
        RedBlackTree<Integer> arvore = new RedBlackTree<>();
        
        System.out.println("\n========== ÁRVORE VAZIA ==========");
        arvore.printTree();
        
        int[] nums = {10, 20, 30, 40, 50, 60, 70, 35};
        System.out.println("\n========== INSERINDO: 10, 20, 30, 40, 50, 60, 70, 35 ==========");
        for (int n : nums) {
            arvore.insert(n);
        }
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 35 ==========");
        arvore.remove(35);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 40 ==========");
        arvore.remove(40);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 10 ==========");
        arvore.remove(10);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 30 ==========");
        arvore.remove(30);
        arvore.printTree();
        
        System.out.println("\n========== TESTES CONCLUÍDOS ==========");
    }
    
    // ===== MODO INTERATIVO =====
    private static void modoInterativo() {
        RedBlackTree<Integer> arvore = new RedBlackTree<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n========== ÁRVORE RUBRO-NEGRA - MODO INTERATIVO ==========");
            System.out.println("1. Inserir nó");
            System.out.println("2. Remover nó");
            System.out.println("3. Mostrar árvore");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a inserir: ");
                    int valor = scanner.nextInt();
                    arvore.insert(valor);
                    System.out.println("Nó inserido com sucesso!");
                    break;
                    
                case 2:
                    System.out.print("Digite o valor a remover: ");
                    int valorRemover = scanner.nextInt();
                    arvore.remove(valorRemover);
                    System.out.println("Nó removido com sucesso!");
                    break;
                    
                case 3:
                    System.out.println("\n--- Estrutura da Árvore Rubro-Negra ---");
                    arvore.printTree();
                    break;
                    
                case 4:
                    System.out.println("Encerrando...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 4);
        
        scanner.close();
    }
}
