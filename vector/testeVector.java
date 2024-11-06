package vector;

public class testeVector {
    public static void main(String[] args) {
        vectorArray a = new vectorArray(10);  // Capacidade 10
        a.insertAtRank(0, 2);  // Inserir 2 na posição 1
        System.out.println(a.toString()); // Esperado: [null, 2, null, null, null, null, null, null, null, null]
        a.insertAtRank(1, 3);  // Inserir 3 na posição 0
        System.out.println(a.toString()); // Esperado: [3, 2, null, null, null, null, null, null, null, null]
        a.insertAtRank(2, 4);  // Inserir 4 na posição 2
        System.out.println(a.toString()); // Esperado: [3, 2, 4, null, null, null, null, null, null, null]
        a.insertAtRank(1, 5);  // Inserir 5 na posição 1
        System.out.println(a.toString()); // Esperado: [3, 5, 2, 4, null, null, null, null, null, null]
    }
}
