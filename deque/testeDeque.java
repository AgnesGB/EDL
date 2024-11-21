package deque;

public class testeDeque {
    public static void main(String[] args) {
        dequeArray deque = new dequeArray();

        
        System.out.println("Inserindo no início:");
        deque.inserirInicio("A");
        deque.inserirInicio("B");
        deque.inserirInicio("C");
        deque.exibirDeque();

       
        System.out.println("Removendo do início:");
        Object removidoInicio = deque.removerInicio();
        System.out.println("Removido: " + removidoInicio);
        deque.exibirDeque();

        
        System.out.println("Inserindo no fim:");
        deque.inserirFim("X");
        deque.inserirFim("Y");
        deque.inserirFim("Z");
        deque.exibirDeque();

       
        System.out.println("Removendo do fim:");
        Object removidoFim = deque.removerFim();
        System.out.println("Removido: " + removidoFim);
        deque.exibirDeque();

       
        System.out.println("Primeiro elemento:");
        System.out.println(deque.primeiro());

        
        System.out.println("Último elemento:");
        System.out.println(deque.ultimo());

        
        System.out.println("Deque está vazio?");
        System.out.println(deque.estaVazio());

        
        System.out.println("Tamanho do deque:");
        System.out.println(deque.tamanho());

       
        System.out.println("Esvaziando o deque:");
        while (!deque.estaVazio()) {
            System.out.println("Removendo: " + deque.removerInicio());
        }
        System.out.println("Deque está vazio agora?");
        System.out.println(deque.estaVazio());
    }
}
