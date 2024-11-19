public class vectorListaDuplaEncad implements Ivector{

    private no head;
    private no tail;
    private int tamanho;

    public vectorListaDuplaEncad(){
        this.tamanho = 0;
        this.head = new no (null);
        this.tail = new no(null);
        head.setProximo(tail);
        tail.setAnterior(head); 
        
    }

    @Override
    public Object elemAtRank(int r){
        no novoNo =  new no(head.getProximo());
        int i = 0;
        while (i != r){
    
            novoNo = novoNo.getProximo();
            i++;
    
            }
        return novoNo; 
    }
    
    @Override
    public Object ReplaceAtRank(int r, Object o){
        no novoNo =  new no(head.getProximo());
        int i = 0;
        while (i != r){

            novoNo = novoNo.getProximo();
            i++;

        }
        Object temp = novoNo.getValor();
        novoNo.setValor(o);
        return temp; 
    }


    @Override
    public void insertAtRank(int r, Object o) {
        if (r < 0 || r > tamanho) {
            throw new IndexOutOfBoundsException("Rank " + r + " is out of bounds");
        }
    
        if (r == 0) {
            no novoNo = new no(o);
            novoNo.proximo = head;
            if (head != null) {
                head.anterior = novoNo;
            }
            head = novoNo;
            if (tamanho == 0) {
                tail = novoNo;
            }
        } else if (r == tamanho) {
            no novoNo = new no(o);
            novoNo.anterior = tail;
            if (tail != null) {
                tail.proximo = novoNo;
            }
            tail = novoNo;
        } else {
            no proximoNo = getNodeAtRank(r);
            no novoNo = new no(o);
            novoNo.anterior = proximoNo.anterior;
            novoNo.proximo = proximoNo;
            proximoNo.anterior.proximo = novoNo;
            proximoNo.anterior = novoNo;
        }
    
        tamanho++;
    }
    


    @Override
    public Object RemoveAtRank(int r){
        no novoNo =  head.getProximo();
        int i = 0;
        while (i != r){
        
            novoNo = novoNo.proximo;
            i++;
        
        }
        Object temp = novoNo.getValor();
        novoNo.getAnterior().setProximo(novoNo.getProximo());
        novoNo.getProximo().setAnterior(novoNo.getAnterior());
        tamanho--;
        return temp;
    }
    
    @Override
    public int size(){
    return tamanho;
    }

    @Override
    public boolean isEmpty(){
    return tamanho == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        no current = head;
        while (current != null) {
            sb.append(current.valor);
            if (current.proximo != null) sb.append(", ");
            current = current.proximo;
        }
        sb.append("]");
        return sb.toString();
    }

    private no getNodeAtRank(int r) {
        no currentNode;
        if (r < tamanho / 2) {
            currentNode = head;
            for (int i = 0; i < r; i++) {
                currentNode = currentNode.proximo;
            }
        } else {
            currentNode = tail;
            for (int i = tamanho - 1; i > r; i--) {
                currentNode = currentNode.anterior;
            }
        }
        return currentNode;
    }


}