public class arrayheap {

    private int[] a;
    private int capacidade;
    private int size;

    public arrayheap(int capacidade) {
        this.capacidade = capacidade;
        this.size = 0;
        this.a = new int[capacidade];
    }

    private void upheap(){

        int posi = size;
        int temp = 0;

        while (a[posi] < a[posi/2]){

            temp = a[posi];
            a[posi] = a[posi/2];
            a[posi/2] = temp;
            posi = posi/2;

        }

    }

    private void downheap(){
        
        int posi = 1;
        int temp = 0;
        for(int i = 1;i < size;i++){
            if(a[posi*2] < a[posi*2+1]){

                temp = a[posi];
                a[posi] = a[posi*2];
                a[posi*2] = temp;
                posi = posi*2;
            }
            else {

                temp = a[posi];
                a[posi] = a[posi*2+1];
                a[posi*2+1] = temp;
                posi = posi*2+1;

            }
            
        }

    }

    public int size(){

        return this.size;

    }

    public Object min(){

        return a[1];

    }

    public boolean isEmpty(){

        return size == 0;

    }

    public void insert(int o){
        
        int temp = 0;
        int posi = size + 1;
        a[posi] = o;
        size++;
        
        upheap();

    }

    public int removeMin(){

        int temp = a[1];
        a[1] = a[size-1];
        size--;

        downheap(); 

        return temp;
        
    }

    public void exibir() {
        System.out.println("Elementos no HEAP:");
        for (int i = 0; i <= size; i++) { 
            System.out.println(a[i]);
        }
    }
}
 
