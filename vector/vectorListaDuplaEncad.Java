public class vectorListaDuplaEncad implements Ivector{

    private no head;
    private no tail;
    private int capacity;

    public vectorListaDuplaEncad(int capacidadeInicial){
        this.capacity = capacidadeInicial;
        this.head = new no();
        this.tail = new no();
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
    public void insertAtRank(int r, Object o){
    no inserir = new no(o);
    no novoNo =  new no(head.proximo);
    int i = 0;
    while (i != r){

        novoNo = novoNo.proximo;
        i++;

        }
    inserir.setProximo(novoNo.getAnterior().getProximo());
    inserir.setAnterior(novoNo.getAnterior());
    novoNo.getAnterior().getProximo().setAnterior(inserir);
    inserir.getAnterior().setProximo(inserir);
    
    }



    
    @Override
    public Object RemoveAtRank(int r);
    
    @Override
    public int size();
    
    @Override
    public boolean isEmpty();



}