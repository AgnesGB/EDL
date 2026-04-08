package EDNL.AVl2;

public class AVLNode<T extends Comparable<? super T>> extends No<T> {
    public int balanceFactor;
    public int height;

    public AVLNode(T data, No<T> pai) {
        super(pai, data);
        this.height = 1; // novos nós começam com altura 1
        this.balanceFactor = 0; // fator de balanceamento inicial é 0
    }
}