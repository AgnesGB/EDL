public class FilaArray implements IFila {

    private Object[] fila;
    private int inicio;
    private int fim;
    private int tamanho; //quantidade de elementos 
    private int capacidade; //capacidade do array
    private static final int INCREMENTO = 2; // Tamanho de incremento

    public FilaArray(int capacidadeInicial){
        
        fila = new Object[capacidadeInicial];
        
        inicio = 0;
        fim = 0;
        tamanho = 0;
        capacidade = capacidadeInicial;
    }

    @Override
    public int size(){
        return (capacidade - inicio + fim) % capacidade;
    }

    @Override
    public boolean isEmpty(){
        return tamanho == 0;
    }

    public void duplicar(){
        capacidade = tamanho * 2;
        Object[] novaFila = new Object[capacidade];
           for (int i = 0; i < tamanho; i++) {
                novaFila[i] = fila[(inicio + i) % fila.length];
        }
          fila = novaFila;
          inicio = 0;
          fim = tamanho;
    }
    
    public void incrementar(){
        capacidade = fila.length + INCREMENTO;
        Object[] novaFila = new Object[capacidade];
           for (int i = 0; i < tamanho; i++) {
                novaFila[i] = fila[(inicio + i) % fila.length];
        }
          fila = novaFila;
          inicio = 0;
          fim = tamanho;
    }
    
    @Override
    public void enqueue(Object o){
        
        if (tamanho == fila.length) {
           //incrementar(); Só pra ter ><
            duplicar();
        }
        
        fila[fim] = o;
        fim = (fim + 1) % fila.length;
        tamanho++;
        }
    
    @Override
    public Object dequeue(){

        if (isEmpty()) {
            throw new FilaVaziaException("Fila vazia.");
        }
        Object item = fila[inicio];
        fila[inicio] = null; 
        inicio = (inicio + 1) % fila.length;
        tamanho--;
        return item;

    }

    @Override
    public Object first(){
        
        if (isEmpty()) {
            throw new FilaVaziaException("Fila vazia.");
        }
        return fila[inicio];

    }

    public void exibirFila() {
        System.out.print("Fila: [");
        for (int x = 0; x < tamanho; x++) {
            System.out.print(fila[(inicio + x) % fila.length]);
            if (x < tamanho - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
