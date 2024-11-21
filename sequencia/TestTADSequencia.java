public class TestTADSequencia {
    public static void main(String[] args) {
        TADSequencia lista = new TADSequencia();

        // Adicionando alguns elementos
        lista.insertLast("A");
        lista.insertLast("B");
        lista.insertLast("C");
        lista.insertLast("D");

        // Teste do atRank(r)
        System.out.println("==== Teste atRank(r) ====");
        System.out.println("Elemento no rank 0: " + lista.atRank(0).getValor()); // Esperado: A
        System.out.println("Elemento no rank 1: " + lista.atRank(1).getValor()); // Esperado: B
        System.out.println("Elemento no rank 2: " + lista.atRank(2).getValor()); // Esperado: C
        System.out.println("Elemento no rank 3: " + lista.atRank(3).getValor()); // Esperado: D

        // Teste do rankOf(n)
        System.out.println("==== Teste rankOf(n) ====");
        no nodeB = lista.atRank(1); // O nó B
        System.out.println("Rank do nó B: " + lista.rankOf(nodeB)); // Esperado: 1

        no nodeC = lista.atRank(2); // O nó C
        System.out.println("Rank do nó C: " + lista.rankOf(nodeC)); // Esperado: 2

        // Teste de nó inválido
        try {
            System.out.println("Rank de um nó inválido: " + lista.rankOf(null)); // Esperado: Exceção
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Teste de rank fora dos limites no atRank
        try {
            System.out.println("Elemento no rank 4: " + lista.atRank(4).getValor()); // Esperado: Exceção
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Teste de inserção e acesso
        lista.insertLast("E");
        System.out.println("Elemento no rank 4 após inserção: " + lista.atRank(4).getValor()); // Esperado: E
    }
}
