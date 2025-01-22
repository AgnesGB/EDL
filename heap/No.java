public class No {
    private Object o;
    private int chave;
    private No pai;
    private No leftChild; // Filho esquerdo
    private No rightChild; // Filho direito

    public No(No pai, Object o, int chave) {
        this.pai = pai;
        this.chave = chave;
        this.o = o;
    }

    public void setChave(int o){
        this.chave = o;
    }

    public boolean hasRight(){
        return rightChild != null;
    }

    public boolean hasLeft(){
        return leftChild != null;
    }


    public int getChave(){
        return chave;
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

    public void setPai(No no){
        this.pai = no;
    }
}