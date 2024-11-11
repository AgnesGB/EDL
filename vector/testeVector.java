package vector;

public class testeVector {
    public static void main(String[] args) {
        vectorArray vector = new vectorArray(10);

        System.out.println("Inicialmente o vetor está vazio? " + vector.isEmpty());
        
        System.out.println("\n--- Teste de inserção ---");
        vector.insertAtRank(0, 1);
        vector.insertAtRank(1, 2);
        vector.insertAtRank(2, 3);
        vector.insertAtRank(1, 4);
        System.out.println("Após inserções: " + vector.toString());
        
        System.out.println("\nTamanho do vetor: " + vector.size());
        
        System.out.println("\n--- Teste de remoção ---");
        Object removedElement = vector.RemoveAtRank(1);
        System.out.println("Elemento removido: " + removedElement);
        System.out.println("Após remoção: " + vector.toString());

        removedElement = vector.RemoveAtRank(0);
        System.out.println("Elemento removido: " + removedElement);
        System.out.println("Após remoção: " + vector.toString());

        System.out.println("\nO vetor está vazio? " + vector.isEmpty());
        
        vector.RemoveAtRank(0);
        vector.RemoveAtRank(0);
        System.out.println("Após todas as remoções: " + vector.toString());
        System.out.println("O vetor está vazio? " + vector.isEmpty());
    }
}
