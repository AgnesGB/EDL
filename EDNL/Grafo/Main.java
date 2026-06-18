import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ============================================================
        // TESTE 1 – Grafo simples não-direcionado (rede de amizades)
        // ============================================================
        System.out.println("=== GRAFO SIMPLES NÃO-DIRECIONADO ===\n");

        Grafo<String, String> g1 = new Grafo<>();

        Vertice<String> maria  = g1.inserirVertice("Maria");
        Vertice<String> jose   = g1.inserirVertice("José");
        Vertice<String> ana    = g1.inserirVertice("Ana");
        Vertice<String> luiz   = g1.inserirVertice("Luiz");

        Aresta<String> a1 = g1.inserirAresta(maria, jose, "amizade");
        Aresta<String> a2 = g1.inserirAresta(maria, ana,  "amizade");
        Aresta<String> a3 = g1.inserirAresta(jose,  luiz, "amizade");
        Aresta<String> a4 = g1.inserirAresta(jose,  ana,  "amizade");

        System.out.println("Vértices: " + g1.vertices());
        System.out.println("Arestas : " + g1.arestas());
        System.out.println("Ordem   : " + g1.ordem());
        System.out.println("Tamanho : " + g1.tamanho());

        System.out.println("\nArestas incidentes a José: " + g1.arestasIncidentes(jose));

        Vertice<String>[] fv = g1.finalVertices(a3);
        System.out.println("Vértices finais de " + a3 + ": [" + fv[0] + ", " + fv[1] + "]");

        System.out.println("Oposto de José em " + a3 + ": " + g1.oposto(jose, a3));

        System.out.println("Maria e Ana adjacentes? " + g1.eAdjacente(maria, ana));
        System.out.println("Maria e Luiz adjacentes? " + g1.eAdjacente(maria, luiz));

        System.out.println("\nMatriz de Adjacência:");
        g1.imprimirMatriz();

        // substituir valor
        g1.substituir(maria, "Mariazinha");
        System.out.println("\nApós substituir Maria → " + maria);

        // removeAresta
        g1.removeAresta(a2);
        System.out.println("Após remover aresta Maria-Ana: " + g1.arestas());

        // removeVertice
        g1.removeVertice(luiz);
        System.out.println("Após remover Luiz, vértices: " + g1.vertices());
        System.out.println("Após remover Luiz, arestas : " + g1.arestas());

        // ============================================================
        // TESTE 2 – Dígrafo (grafo direcionado)
        // ============================================================
        System.out.println("\n=== DÍGRAFO (GRAFO DIRIGIDO) ===\n");

        Grafo<String, Integer> g2 = new Grafo<>();

        Vertice<String> v1 = g2.inserirVertice("v1");
        Vertice<String> v2 = g2.inserirVertice("v2");
        Vertice<String> v3 = g2.inserirVertice("v3");
        Vertice<String> v4 = g2.inserirVertice("v4");

        Aresta<Integer> d1 = g2.inserirArestaDirecionada(v1, v2, 10);
        Aresta<Integer> d2 = g2.inserirArestaDirecionada(v1, v3, 5);
        Aresta<Integer> d3 = g2.inserirArestaDirecionada(v2, v4, 20);
        Aresta<Integer> d4 = g2.inserirArestaDirecionada(v4, v2, 15);
        Aresta<Integer> d5 = g2.inserirArestaDirecionada(v4, v3, 30);

        System.out.println("Vértices : " + g2.vertices());
        System.out.println("Arestas  : " + g2.arestas());

        System.out.println("\nAresta d1 é direcionada? " + g2.eDirecionado(d1));

        System.out.println("Arestas incidentes a v4: " + g2.arestasIncidentes(v4));
        System.out.println("Arestas incidentes a v2: " + g2.arestasIncidentes(v2));

        System.out.println("v1 adjacente a v2? " + g2.eAdjacente(v1, v2));
        System.out.println("v2 adjacente a v1? " + g2.eAdjacente(v2, v1));  // false

        System.out.println("\nMatriz de Adjacência (dígrafo):");
        g2.imprimirMatriz();

        // ============================================================
        // TESTE 3 – Multigrafo misto (paralelas + laço + misto)
        // ============================================================
        System.out.println("\n=== MULTIGRAFO MISTO ===\n");

        Grafo<String, String> g3 = new Grafo<>();

        Vertice<String> a = g3.inserirVertice("A");
        Vertice<String> b = g3.inserirVertice("B");
        Vertice<String> c = g3.inserirVertice("C");

        // Arestas paralelas entre A e B (não-direcionadas)
        Aresta<String> p1 = g3.inserirAresta(a, b, "paralela-1");
        Aresta<String> p2 = g3.inserirAresta(a, b, "paralela-2");
        Aresta<String> p3 = g3.inserirAresta(a, b, "paralela-3");

        // Aresta direcionada A -> C
        Aresta<String> dc = g3.inserirArestaDirecionada(a, c, "dirigida");

        // Aresta não-direcionada B -- C
        Aresta<String> bc = g3.inserirAresta(b, c, "nao-dirigida");

        // Laço em A
        Aresta<String> laco = g3.inserirAresta(a, a, "laço");

        System.out.println("Vértices : " + g3.vertices());
        System.out.println("Arestas  : " + g3.arestas());
        System.out.println("Tamanho  : " + g3.tamanho());

        System.out.println("\nArestas incidentes a A: " + g3.arestasIncidentes(a));
        System.out.println("Arestas incidentes a B: " + g3.arestasIncidentes(b));
        System.out.println("Arestas incidentes a C: " + g3.arestasIncidentes(c));

        System.out.println("\nMatriz de Adjacência (multigrafo misto):");
        g3.imprimirMatriz();

        System.out.println("\nA--B adjacentes? " + g3.eAdjacente(a, b));
        System.out.println("A--C adjacentes? " + g3.eAdjacente(a, c));

        // substituir valor de aresta
        g3.substituir(p1, "paralela-1-MODIFICADA");
        System.out.println("\nApós substituir p1: " + p1);

        // removeAresta (remove uma das paralelas)
        g3.removeAresta(p2);
        System.out.println("Após remover p2, arestas entre A e B: "
                + g3.arestasIncidentes(a));

        System.out.println("\nMatriz após remoção:");
        g3.imprimirMatriz();
    }
}
