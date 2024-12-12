import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestBinaruSearchTree{
    // metodo para unir duas arvores (questao da prova passada)//
    public static BinarySearchTree mergeTrees(BinarySearchTree tree1, BinarySearchTree tree2) {
        // Lista para armazenar os elementos de ambas as árvores
        List<Object> elements = new ArrayList<>();
    
        // Extrair os elementos de cada árvore em ordem
        inOrderTraversal(tree1.getRoot(), elements);
        inOrderTraversal(tree2.getRoot(), elements);
    
        // Ordenar a lista resultante e remover duplicatas
        elements = elements.stream().distinct().sorted().collect(Collectors.toList());
    
        // Construir uma nova árvore balanceada a partir da lista ordenada
        BinarySearchTree mergedTree = new BinarySearchTree();
        mergedTree.setRoot(buildBalancedTree(elements, 0, elements.size() - 1));
    
        return mergedTree;
    }
    
    // Método auxiliar para realizar a travessia em ordem e coletar elementos
    private static void inOrderTraversal(No node, List<Object> elements) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.getLeftChild(), elements); // Lado esquerdo
        elements.add(node.element());                   // Nó atual
        inOrderTraversal(node.getRightChild(), elements); // Lado direito
    }
    
    // Construir uma árvore balanceada a partir de uma lista ordenada
    private static No buildBalancedTree(List<Object> elements, int start, int end) {
        if (start > end) {
            return null;
        }
    
        // Escolher o elemento do meio como raiz
        int mid = (start + end) / 2;
        No node = new No(null, elements.get(mid));
    
        // Recursivamente construir a subárvore esquerda e direita
        node.setLeftChild(buildBalancedTree(elements, start, mid - 1));
        node.setRightChild(buildBalancedTree(elements, mid + 1, end));
    
        return node;
    }


    public static void main(String[] args) {
        BinarySearchTree bst1 = new BinarySearchTree();

    // Inserções
    bst1.insert(8);
    bst1.insert(5);
    bst1.insert(15);
    bst1.insert(4);
    bst1.insert(17);
    

    BinarySearchTree bst2 = new BinarySearchTree();

    bst2.insert(8);
    bst2.insert(3);
    bst2.insert(12);
    bst2.insert(18);
    bst2.insert(6);


    BinarySearchTree bst3 = mergeTrees(bst1, bst2);
    System.out.println("Arvore 1: ");
    bst1.display();
    System.out.println("Arvore 2: ");
    bst2.display();
    System.out.println("Arvore fusao: ");
    bst3.display();
    System.out.println("Arvore espelhada: ");
    bst3.mirror();
    bst3.display();

    } 
}

