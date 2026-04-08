package EDNL.AVl2;

public class No<T extends Comparable<? super T>> {
    protected T o;
    protected No<T> pai;
    protected No<T> leftChild;
    protected No<T> rightChild;

    public No(No<T> pai, T o) {
        this.pai = pai;
        this.o = o;
    }

    public T element() {
        return o;
    }

    public No<T> parent() {
        return pai;
    }

    public void setParent(No<T> pai) {
        this.pai = pai;
    }

    public void setElement(T o) {
        this.o = o;
    }

    public No<T> getLeftChild() {
        return leftChild;
    }

    public No<T> getRightChild() {
        return rightChild;
    }

    public void setLeftChild(No<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(No<T> rightChild) {
        this.rightChild = rightChild;
    }
}