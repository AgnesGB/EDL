
public class TestBinaruSearchTree{
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

    // Inserções
    bst.insert(7);
    bst.insert(10);
    bst.insert(9);
    bst.insert(12);
    bst.insert(5);
    bst.insert(2);
    bst.insert(3);
    
    
    // Mostrar a árvore
    System.out.println("Árvore Inicial:");
    bst.display();

    
    // Remover 25
    bst.remove(2);
    System.out.println("\nApós remover 2:");
    bst.display();
    No root = bst.getRoot();
    No result = bst.search(3,root);
    System.out.println("Valor encontrado: " + result.element());
    } 
}

