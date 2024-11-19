package deque;
import java.util.ArrayList;

public class dequeArray {
    private ArrayList<Object> data;
    private int size; //quantidade de elementos
    private int capacity; // tamanho do array

    public dequeArray(){
        this.capacity = 10;
        this.size = 0;
        this.data = new ArrayList<>(capacity);
    }


    public void inserirInicio(Object o){ 
        if (data.isEmpty()){
            data.add(o);
            size++;
        } else{ 
            for (int i=size;i > 0; i--){
                data.add(0);
                data.set(i, data.get(i - 1));
            }
            data.set(0,o);
            size ++;
        }
        
    }

    public Object removerInicio(){
        Object temp = data.get(0);
        if (estaVazio()){
            throw new IndexOutOfBoundsException("Ta vazia");
        }else{
            for (int i=0;i < size; i++){
                data.set(i,data.get(i + 1));
            }
        size --;
        }
        return temp;
    }

    
    public void inserirFim(Object o){
        data.add(size, o);
        size++;
    }


    public Object removerFim(){

        if (estaVazio()){
            throw new IndexOutOfBoundsException("Ta vazia");
        }

        Object temp = data.get(size - 1);
        data.remove(size - 1);
        size --;
        return temp;

    }

    public Object primeiro(){
        if (estaVazio()){
            throw new IndexOutOfBoundsException("Ta vazia");
        }
        
        return data.get(0);
    }

    public Object ultimo(){
        if (estaVazio()){
            throw new IndexOutOfBoundsException("Ta vazia");
        }
        
        return data.get(size - 1);
    }

    public int tamanho(){
    return size;
    }   
    
    public boolean estaVazio(){
        if (size == 0){
            return true;
        } else {return false;}
    }

    public void exibirDeque() {
        System.out.print("Deque: [");
        for (int x = 0; x < size; x++) {
            System.out.print(data.get(x));
            System.out.print(", ");
        }
        System.out.println("]");
    }
}



