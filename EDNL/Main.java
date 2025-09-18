public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("Árvore inicial (antes das inserções):");
        avl.printTree();

        int[] nums = { 30, 20, 40, 10, 25, 35, 50, 5, 27, 45, 60, 55 };
        for (int n : nums) avl.insert(n);

        System.out.println("\nÁrvore após inserções:");
        avl.printTree();

        avl.remove(40);
        avl.remove(50);

        System.out.println("\nÁrvore após remoções (40 e 50):");
        avl.printTree();
    }
}
