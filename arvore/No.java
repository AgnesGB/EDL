public class No {
    private Object o;
    private No pai;
    private No leftChild; // Filho esquerdo
    private No rightChild; // Filho direito

    public No(No pai, Object o) {
        this.pai = pai;
        this.o = o;
    }

    public Object element() {
        return o;
    }

    public No parent() {
        return pai;
    }

    public void setElement(Object o) {
        this.o = o;
    }

    public No getLeftChild() {
        return leftChild;
    }

    public No getRightChild() {
        return rightChild;
    }

    public void setLeftChild(No leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(No rightChild) {
        this.rightChild = rightChild;
    }
}