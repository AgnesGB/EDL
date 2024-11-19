

public class listaEncad implements Ilista {
    private no head;
    private no tail;
    private int tamanho;

    public listaEncad(){

        head = new no(null);
        tail = new no(null);
        head.proximo = tail;
        tail.anterior = head;
        tamanho = 0;
    }

    @Override
    public int size(){
        return tamanho;
    }

    @Override
    public boolean isEmpty(){

        return tamanho == 0;

    }

    @Override
    public boolean isFirst(no n){
        return n == head.proximo;
    }

    @Override
    public boolean isLast(no n){
        return n == tail.anterior;
    }

    @Override
    public no first() {
        if (isEmpty()) throw new IllegalStateException("A lista está vazia.");
        return head.proximo;
    }

    @Override
    public no last() {
        if (isEmpty()) throw new IllegalStateException("A lista está vazia.");
        return tail.anterior;
    }

    @Override
    public no before(no p){
    if (p == head) throw new IllegalArgumentException("Nó inválido.");
        return p.anterior;
    }

    @Override
    public no after(no p){
        if (p == head) throw new IllegalArgumentException("Nó inválido.");
            return p.proximo;
    }
    
    @Override
    public void replaceElement(no n, Object o) {
        n.valor = o;
    }

    @Override
    public void swapElements(no n, no q){

        Object temp = n.valor;
        n.valor = q.valor;
        q.valor = temp;

    }

    @Override
    public void insertAfter(no n, Object q){ // n é oq já tá, q é o nó que quero inserir
        
        no novoNo = new no(q);
        novoNo.anterior = n;
        novoNo.proximo = n.proximo;
        (n.proximo).anterior = novoNo;
        n.proximo = novoNo;
        tamanho ++;

    }

    @Override
    public void insertBefore(no n, Object q){ // n é oq já tá, q é o nó que quero inserir

        no novoNo = new no(q);
        novoNo.anterior = n.anterior;
        novoNo.proximo = n;
        n.anterior = novoNo;
        (n.anterior).proximo = novoNo; 
        tamanho ++;

    }

    @Override
    public void insertFirst(Object o){

        insertAfter(head, o);
    }

    @Override
    public void insertLast(Object o){

        insertBefore(tail, o);

    }

    @Override
    public void remove(no n) {
        no anterior = n.anterior;
        no proximo = n.proximo;
        anterior.proximo = proximo;
        proximo.anterior = anterior;
        tamanho--;
    }

    public void exibirLista() {
        if (isEmpty()) {
            System.out.println("A lista está vazia.");
            return;
        }
    
        no atual = head.proximo; // Começa após o sentinela inicial
        System.out.print("Elementos da lista: ");
        while (atual != tail) { // Para antes do sentinela final
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
    
}
