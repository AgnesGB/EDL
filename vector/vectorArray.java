package vector;

public class vectorArray implements Ivector {
//tentando fazer com array circular
    
    private Object[] a;
    private int size; //numero de elementos
    private int capacity; //capacidade do vetor
    private int first; // indice do primeiro elemento do array
    
    public vectorArray(int capacidadeInicial){

        this.capacity = capacidadeInicial;
        this.size = 0;
        this.first = 0;
        this.a = new Object[capacidadeInicial];
    }
    
    
    
    @Override
    public Object elemAtRank(int r){

        return a[convertIndex(r)];

    }

    @Override
    public Object ReplaceAtRank(int r, Object o){
        int indexCirc = convertIndex(r);
        Object temp = a[indexCirc];
        a[indexCirc] = o;
        return temp;
    
    }

    @Override
    public void insertAtRank(int r, Object o) {
    
        // Redimensiona o array se a capacidade for atingida
        if (size == capacity) {
            duplicar();
        }
    
        // Desloca os elementos à direita para abrir espaço
        // Percorre de trás para frente para evitar sobrescrever elementos
        for (int i = size - 1; i >= r; i--) {
            a[convertIndex(i + 1)] = a[convertIndex(i)];
        }
    
        // Insere o novo elemento no índice correto
        a[convertIndex(r)] = o;
    
    
        // Atualiza o tamanho
        size++;
    }

    private int convertIndex(int r) {
        return (first + r) % capacity;
    }

    public void duplicar(){

        int newCapacity = capacity * 2;
        Object newa[] = new Object[newCapacity];
        
        for (int i = 0; i < size; i++) {
            newa[i] = a[convertIndex(i)];
        }
        
        a = newa;
        first = 0;
        capacity = newCapacity;

    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
    
        // Percorre todos os índices do array, de 0 até capacity - 1
        for (int i = 0; i < capacity; i++) {
            sb.append(a[i]); // Adiciona o elemento no índice i
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }
    
        sb.append("]");
        return sb.toString();
    }


}
