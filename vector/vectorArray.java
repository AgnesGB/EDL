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
        
        if (r < 0 || r > size) {
            throw new IndexOutOfBoundsException("Rank " + r + " is out of bounds");
        }
    
        
        if (size == capacity) {
            duplicar();
        }
    
        
        for (int i = size; i > r; i--) {
            a[convertIndex(i)] = a[convertIndex(i - 1)];
        }
    
       
        a[convertIndex(r)] = o;
    
        
        size++;
    }

    @Override
    public Object RemoveAtRank(int r){

        if ( r < 0 || r>=size){
            throw new IndexOutOfBoundsException("Rank " + r + " is out of bounds");
        }

        Object removedObject = a[convertIndex(r)];
        for (int i =r; i < size - 1; i++){
            a[convertIndex(i)] = a[convertIndex(i + 1)];
        }

        a[convertIndex(size - 1)] = null;
        size--;

    return removedObject;

    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
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
    
        
        for (int i = 0; i < capacity; i++) {
            sb.append(a[i]); 
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }
    
        sb.append("]");
        return sb.toString();
    }


}
