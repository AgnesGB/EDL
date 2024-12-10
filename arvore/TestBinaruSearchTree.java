public class TestBinaruSearchTree{
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

    // Inserções
    bst.insert(10);
    bst.insert(5);
    bst.insert(22);
    bst.insert(2);
    bst.insert(8);
    bst.insert(1);
    bst.insert(3);
    bst.insert(15);
    bst.insert(25);
    bst.insert(14);
    // Mostrar a árvore
    System.out.println("Árvore Inicial:");
    bst.display();

    
    // Remover 5
    bst.remove(5);
    System.out.println("\nApós remover 5:");
    bst.display();
    } 
}

