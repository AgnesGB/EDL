
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
    bst.insert(8);
    bst.insert(17);
    bst.insert(20);
    bst.insert(1);
    bst.insert(6);
    bst.insert(24);
    bst.insert(13);
    bst.insert(4);
        



    
    
    // Mostrar a árvore
    System.out.println("Árvore Inicial:");
    bst.display();

    
    // Remover 25
    bst.remove(34);
    System.out.println("\nApós remover 2:");
    bst.display();
    No root = bst.getRoot();
    No result = bst.search(2,root);
    System.out.println("Valor encontrado: " + result.element());
    } 
}

