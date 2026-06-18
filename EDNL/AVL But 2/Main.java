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
        AVLTree arvore = new AVLTree();
        
        System.out.println("\n========== ÁRVORE VAZIA ==========");
        arvore.printAVL();
        
        int[] nums = { 42, 1, 12, 6, 2, 45 };
        System.out.println("\n========== INSERINDO: 42, 1, 12, 6, 2, 45 ==========");
        for (int n : nums) {
            arvore.insert(n);
        }
        arvore.printAVL();
        
        System.out.println("\n========== INSERINDO: 23 ==========");
        arvore.insert(23);
        arvore.printAVL();
        
        System.out.println("\n========== REMOVENDO: 2 ==========");
        arvore.remove(2);
        arvore.printAVL();
        
        System.out.println("\n========== REMOVENDO: 45 ==========");
        arvore.remove(45);
        arvore.printAVL();
        
        System.out.println("\n========== REMOVENDO: 1 ==========");
        arvore.remove(1);
        arvore.printAVL();
        
        System.out.println("\n========== INSERINDO: 34, 35, 36, 37, 38 ==========");
        int[] nums2 = { 34, 35, 36, 37, 38 };
        for (int n : nums2) {
            arvore.insert(n);
        }
        arvore.printAVL();
        
        System.out.println("\n========== REMOVENDO: 42 ==========");
        arvore.remove(42);
        arvore.printAVL();
        
        System.out.println("\n========== INSERINDO: 68 ==========");
        arvore.insert(68);
        arvore.printAVL();
        
        System.out.println("\n========== TESTES CONCLUÍDOS ==========");
    }
    
    // ===== MODO INTERATIVO =====
    private static void modoInterativo() {
        AVLTree arvore = new AVLTree();
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
                    arvore.remove(valorRemover);
                    System.out.println("Nó removido com sucesso!");
                    break;
                    
                case 3:
                    System.out.print("Digite o valor a buscar: ");
                    int valorBuscar = scanner.nextInt();
                    try {
                        No encontrado = arvore.search(valorBuscar, (AVLNode) arvore.getRoot());
                        if (encontrado != null) {
                            System.out.println("Nó encontrado: " + encontrado.element());
                        } else {
                            System.out.println("Nó não encontrado!");
                        }
                    } catch (Exception e) {
                        System.out.println("Nó não encontrado!");
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- Estrutura da Árvore AVL ---");
                    arvore.printAVL();
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