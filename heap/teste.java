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
        pp.removeMin();
        pp.exibir();
        pp.removeMin();
        pp.exibir();
        pp.removeMin();
        pp.exibir();
    }
}