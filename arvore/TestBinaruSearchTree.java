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

    
    // Remover 22
    bst.remove(22);
    System.out.println("\nApós remover 2:");
    bst.display();

    System.out.println(bst.search(1).element());
    } 
}

