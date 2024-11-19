public class TesteListaEncad {
    public static void main(String[] args) {
        listaEncad lista = new listaEncad();

        System.out.println("Testando métodos genéricos:");
        System.out.println("A lista está vazia? " + lista.isEmpty());
        System.out.println("Tamanho inicial: " + lista.size());

        System.out.println("\nTestando inserções:");
        lista.insertFirst(10);
        lista.insertLast(20);
        lista.insertFirst(5);
        lista.insertLast(25);
        System.out.println("A lista está vazia? " + lista.isEmpty());
        System.out.println("Após inserções: ");
        lista.exibirLista();

        System.out.println("\nTestando acesso:");
        System.out.println("Primeiro elemento: " + lista.first().valor);
        System.out.println("Último elemento: " + lista.last().valor);

        System.out.println("\nTestando posição relativa:");
        System.out.println("5 é o primeiro? " + lista.isFirst(lista.first()));
        System.out.println("25 é o último? " + lista.isLast(lista.last()));

        System.out.println("\nTestando antes/depois:");
        System.out.println("Elemento antes de 10: " + lista.before(lista.first().proximo).valor);
        System.out.println("Elemento após 20: " + lista.after(lista.last().anterior).valor);

        System.out.println("\nTestando atualizações:");
        lista.replaceElement(lista.first(), 50);
        System.out.println("Primeiro elemento substituído por 50:");
        lista.exibirLista();

        lista.swapElements(lista.first(), lista.last());
        System.out.println("Primeiro e último elementos trocados:");
        lista.exibirLista();

        System.out.println("\nTestando inserção antes/depois:");
        lista.insertBefore(lista.first().proximo, 15);
        lista.insertAfter(lista.last().anterior, 22);
        System.out.println("Após inserções antes e depois:");
        lista.exibirLista();

        System.out.println("\nTestando remoção:");
        lista.remove(lista.first());
        lista.remove(lista.last());
        System.out.println("Após remover primeiro e último elementos:");
        lista.exibirLista();

        System.out.println("\nEstado final:");
        System.out.println("Tamanho da lista: " + lista.size());
        System.out.println("A lista está vazia? " + lista.isEmpty());
    }

    
}
