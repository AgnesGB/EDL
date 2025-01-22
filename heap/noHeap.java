public class noHeap {
    private No raiz;
    private No last;
    private int size;

    public noHeap(Object o){
        this.raiz = new No(null, o, 1);
        this.last = raiz;
        this.size = 1;
    }

    private void upheap();
    private void downheap();

    private boolean isLeft(No no){
        if (no.parent().getLeftChild() == no){
            return true;
        } else {
            return false;
        }
    }

    private boolean isRight(No no){
        if (no.parent().getRightChild() == no){
            return true;
        } else {
            return false;
        }
    }

    public void insert(Object o){
        size++;
        No antonó = new No(null, o, size);

        if (isLeft(last)){
            antonó.setPai(last.parent());
            last.parent().setRightChild(antonó);
            last = antonó;
        }
        
        
        if (last.getLeftChild() == null){
            last.setLeftChild(antonó);
        } else { 
            last.setRightChild(antonó);
        }
        this.last = antonó;
    }
    
    public int removeMin();

    public boolean isEmpty(){
        return size == 0;
    }

    public Object min(){
        return this.raiz;
    }

    public int size(){
        return this.size;
    }

}
