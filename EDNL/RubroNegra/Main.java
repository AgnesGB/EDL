package EDNL.RubroNegra;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt2 = new RedBlackTree<>();
        int[] vals2 = {40,20,60,10,30,50,70,35};
        for (int z : vals2) rbt2.insert(z);

        System.out.println("Depois das inserções:");
        rbt2.printTree();
        System.out.println("Removendo 35");
        rbt2.remove(35);
        rbt2.printTree();
        System.out.println("Removendo 40");
        rbt2.remove(40);
        rbt2.printTree();
        System.out.println("Removendo 10");
        rbt2.remove(10);
        rbt2.printTree();
        System.out.println("Removendo 30");
        rbt2.remove(30);
        rbt2.printTree();
        
       
        
        

    }
}
