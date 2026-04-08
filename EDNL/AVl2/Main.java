package EDNL.AVl2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========== ÁRVORE AVL ==========");
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
        AVLTree<Integer> arvore = new AVLTree<>();
        
        System.out.println("\n========== ÁRVORE VAZIA ==========");
        arvore.printTree();
        
        int[] nums = { 42, 1, 12, 6, 2, 45 };
        System.out.println("\n========== INSERINDO: 42, 1, 12, 6, 2, 45 ==========");
        for (int n : nums) {
            arvore.insert(n);
        }
        arvore.printTree();
        
        System.out.println("\n========== INSERINDO: 23 ==========");
        arvore.insert(23);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 2 ==========");
        arvore.delete(2);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 45 ==========");
        arvore.delete(45);
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 1 ==========");
        arvore.delete(1);
        arvore.printTree();
        
        System.out.println("\n========== INSERINDO: 34, 35, 36, 37, 38 ==========");
        int[] nums2 = { 34, 35, 36, 37, 38 };
        for (int n : nums2) {
            arvore.insert(n);
        }
        arvore.printTree();
        
        System.out.println("\n========== REMOVENDO: 42 ==========");
        arvore.delete(42);
        arvore.printTree();
        
        System.out.println("\n========== INSERINDO: 68 ==========");
        arvore.insert(68);
        arvore.printTree();
        
        System.out.println("\n========== TESTES CONCLUÍDOS ==========");
    }
    
    // ===== MODO INTERATIVO =====
    private static void modoInterativo() {
        AVLTree<Integer> arvore = new AVLTree<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n========== ÁRVORE AVL - MODO INTERATIVO ==========");
            System.out.println("1. Inserir nó");
            System.out.println("2. Remover nó");
            System.out.println("3. Buscar nó");
            System.out.println("4. Mostrar árvore");
            System.out.println("5. Sair");
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
                    arvore.delete(valorRemover);
                    System.out.println("Nó removido com sucesso!");
                    break;
                    
                case 3:
                    System.out.print("Digite o valor a buscar: ");
                    int valorBuscar = scanner.nextInt();
                    try {
                        AVLNode<Integer> encontrado = arvore.search(valorBuscar);
                        if (encontrado != null) {
                            System.out.println("Nó encontrado: " + valorBuscar);
                        } else {
                            System.out.println("Nó não encontrado!");
                        }
                    } catch (Exception e) {
                        System.out.println("Nó não encontrado!");
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- Estrutura da Árvore AVL ---");
                    arvore.printTree();
                    break;
                    
                case 5:
                    System.out.println("Encerrando...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 5);
        
        scanner.close();
    }
}