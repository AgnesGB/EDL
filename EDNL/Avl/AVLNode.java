// AVLNode.java
public class AVLNode<T extends Comparable<? super T>> {
    T key;
    AVLNode<T> left;
    AVLNode<T> right;
    AVLNode<T> pai;                 
    int height;                     
    int fatorBalanceamento;         

    public AVLNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.pai = null;
        this.height = 1;            
        this.fatorBalanceamento = 0;
    }
}
