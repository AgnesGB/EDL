public class RBMain {
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        //int[] vals = {10, 20, 30, 15, 25, 5, 1, 50, 60, 55, };
        int[] vals = {10,9,8,7,6,5,4,3,2,1};
        for (int v : vals) rbt.insert(v);

        System.out.println("Depois das inserções:");
        rbt.printTree();

        /*int[] rem = {20, 60, 10, 55, 1};
        for (int k : rem) {
            System.out.println("Removendo " + k + "...");
            rbt.remove(k);
            rbt.printTree();
        }
        rbt.insert(55);
        System.out.println(" Insereindo 55");
        rbt.printTree();
        rbt.insert(26);
        System.out.println(" Insereindo 26");
        */

    }
}
