package lista;

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

}
