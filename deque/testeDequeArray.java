package deque;

public class testeDequeArray {

    public static void main(String[] args) {
        dequeArray teste = new dequeArray();
        System.out.println();
    

        System.out.println("Está Vazio: " + teste.estaVazio());

        teste.inserirInicio(1);
        teste.exibirDeque();
        teste.inserirInicio(2);
        teste.exibirDeque();
        teste.inserirInicio(3);
        teste.exibirDeque();
        teste.inserirInicio(4);
        teste.exibirDeque();
        teste.inserirInicio(5);
        teste.exibirDeque();
        teste.inserirInicio(6);
        teste.exibirDeque();
        teste.inserirInicio(7);
        teste.exibirDeque();

        teste.inserirFim("xuxu");
        teste.exibirDeque();
        teste.inserirFim("valor");
        teste.exibirDeque();
        teste.inserirFim("sim");
        teste.exibirDeque();
        teste.inserirFim("não");
        teste.exibirDeque();

        System.out.println("Removido: " + teste.removerInicio());
        teste.exibirDeque();

        System.out.println("Removido: " + teste.removerFim());
        teste.exibirDeque();

        System.out.println("Está Vazio: " + teste.estaVazio());

        System.out.println("Primeiro: " + teste.primeiro());

        System.out.println("Ultimo: " + teste.ultimo());

        System.out.println("Tamanho: " + teste.tamanho());

    }
}