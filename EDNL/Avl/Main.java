public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("Árvore inicial (antes das inserções):");
        avl.printTree();

        int[] nums = { 22,5,10,2,8,15 };
        for (int n : nums) avl.insert(n);

        System.out.println("\nÁrvore após inserções:");
        avl.printTree();

        avl.insert(25);
        System.out.println("\nÁrvore após insersecao do 25 ");
        avl.printTree();
        avl.remove(5);

        System.out.println("\nÁrvore após remoções 5 ");
        avl.printTree();
        avl.remove(22);

        System.out.println("\nÁrvore após remoções 22 ");
        avl.printTree();
        avl.remove(10);

        System.out.println("\nÁrvore após remoções 10 ");
        avl.printTree();
        avl.insert(26);
        avl.insert(27);
        avl.insert(28);
        avl.insert(29);
        avl.insert(30);
        avl.printTree();
        avl.remove(15);
        avl.printTree();
        avl.insert(56);
        avl.printTree();

    }
}
