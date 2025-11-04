import java.util.ArrayList;
import java.util.List

public class Btree<T extends Comparable<T>> {

    private final int t; // minino da arvore
    private Bnode<T> root;

    public Btree(int t) {
        if (t <2) throw new IllegalArgumentException("Deve ser maior ou igual a 2");
        this.t = t;
        this.root = new Bnode<>();

    }

    //-----------------BUSCA------------------

    public boolean contains(T key){
        return get(key) != null;
    }
    public T get(T key) {
        Bnode<T> n = root;
        while (true){
            int i = lowerBound(n.keys, keys);
            if (i < n.keys.size() && n.keys.get(i).compareTo(key)== 0){
                return n.keys.get(i);
            }
            if (n.leaf) return null;
            n = n.children.get(i);
        }
    }
}