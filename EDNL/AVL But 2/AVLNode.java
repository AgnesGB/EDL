public class AVLNode<T extends Comparable<? super T>> {
    T data;
    public AVLNode<T> left;
    public AVLNode<T> right;
    public AVLNode<T> pai;
    public int balanceFactor;
    public int height;

    public AVLNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.pai = null;
        this.height = 1; // novos nós começam com altura 1
        this.balanceFactor = 0; // fator de balanceamento inicial é 0
    }
}