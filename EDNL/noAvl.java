public class AVLNode<T extends Comparable<T>> {
    T key;
    AVLNode<T> left;
    AVLNode<T> right;
    AVLNode<T> pai;
    int fatorBalanceamento;  

    public AVLNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.pai = null; 
        this.fatorBalanceamento = 0;  //Raiz no raiz inicialmente vazio e balanceado
    }
}
