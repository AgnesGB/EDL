public class teste {

	public static void main(String[] args) {
        arrayheap pp = new arrayheap(100);
        pp.insert(9);
        pp.insert(10);
        pp.insert(4);
        pp.insert(20);
        pp.insert(3);
        pp.insert(8);
        pp.insert(54);
        pp.exibir();
        System.out.println("min:");
        System.out.println(pp.min());
        System.out.println("removido:");
        System.out.println(pp.removeMin());
        System.out.println("size");
        System.out.println(pp.size());
        pp.exibir();
        System.out.println("min:");
        System.out.println(pp.min());
        System.out.println("removido:");
        System.out.println(pp.removeMin());
        System.out.println("size");
        System.out.println(pp.size());
        pp.exibir();
        System.out.println("min:");
        System.out.println(pp.min());
        System.out.println("removido:");
        System.out.println(pp.removeMin());
        System.out.println("size");
        System.out.println(pp.size());
        pp.exibir();
        System.out.println("min:");
        System.out.println(pp.min());
        System.out.println("removido:");
        System.out.println(pp.removeMin());
        System.out.println("size");
        System.out.println(pp.size());
        pp.exibir();
    }
}