package grafos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstração comparativa: Dijkstra vs A*.
 *
 * Grafo: mapa simplificado de cidades do RN/CE com distâncias em km.
 * A heurística do A* usa coordenadas geográficas aproximadas (distância
 * euclidiana), simulando a distância em linha reta — heurística admissível.
 */
public class MainAlgoritmos {

    // Coordenadas aproximadas (latitude, longitude) — usadas só pela heurística
    static final Map<String, double[]> COORDS = new HashMap<>();
    static {
        COORDS.put("Natal",        new double[]{-5.79,  -35.21});
        COORDS.put("Joao Pessoa",  new double[]{-7.12,  -34.86});
        COORDS.put("Recife",       new double[]{-8.05,  -34.88});
        COORDS.put("Mossoro",      new double[]{-5.19,  -37.34});
        COORDS.put("Fortaleza",    new double[]{-3.72,  -38.54});
        COORDS.put("Campina",      new double[]{-7.23,  -35.88});
        COORDS.put("Caico",        new double[]{-6.46,  -37.09});
        COORDS.put("Patos",        new double[]{-7.02,  -37.28});
    }

    /** Heurística: distância euclidiana escalada (~111 km por grau). */
    static double heuristica(Vertice<String> a, Vertice<String> b) {
        double[] ca = COORDS.get(a.getElemento());
        double[] cb = COORDS.get(b.getElemento());
        if (ca == null || cb == null) return 0.0;
        double dlat = ca[0] - cb[0];
        double dlon = ca[1] - cb[1];
        return Math.sqrt(dlat * dlat + dlon * dlon) * 111.0;
    }

    public static void main(String[] args) {

        // ---- Montagem do grafo ----
        Grafo<String, Double> g = new Grafo<>();

        Vertice<String> natal     = g.inserirVertice("Natal");
        Vertice<String> jp        = g.inserirVertice("Joao Pessoa");
        Vertice<String> recife    = g.inserirVertice("Recife");
        Vertice<String> mossoro   = g.inserirVertice("Mossoro");
        Vertice<String> fortaleza = g.inserirVertice("Fortaleza");
        Vertice<String> campina   = g.inserirVertice("Campina");
        Vertice<String> caico     = g.inserirVertice("Caico");
        Vertice<String> patos     = g.inserirVertice("Patos");

        // Arestas não-dirigidas com pesos em km
        g.inserirAresta(natal,     jp,        185.0);
        g.inserirAresta(natal,     mossoro,   284.0);
        g.inserirAresta(natal,     caico,     290.0);
        g.inserirAresta(jp,        recife,    120.0);
        g.inserirAresta(jp,        campina,   150.0);
        g.inserirAresta(recife,    campina,   130.0);
        g.inserirAresta(campina,   patos,     170.0);
        g.inserirAresta(patos,     caico,     110.0);
        g.inserirAresta(patos,     mossoro,   180.0);
        g.inserirAresta(mossoro,   fortaleza, 267.0);
        g.inserirAresta(caico,     mossoro,   200.0);
        g.inserirAresta(fortaleza, mossoro,   267.0);

        System.out.println("============================================================");
        System.out.println("  Grafo: cidades do Nordeste — pesos em km");
        System.out.println("============================================================");
        System.out.println("Vértices : " + g.vertices());
        System.out.println("Arestas  : " + g.numArestas());
        System.out.println();

        // ================================================================
        // DIJKSTRA — fonte única: Natal
        // ================================================================
        Dijkstra<String> dijkstra = new Dijkstra<>(g);
        Dijkstra.Resultado<String> resDijk = dijkstra.executar(natal);
        dijkstra.imprimirResultado(resDijk, natal);

        System.out.println();

        // ================================================================
        // A* — Natal → Fortaleza
        // ================================================================
        AStar<String> astar = new AStar<>(g, MainAlgoritmos::heuristica);

        // Consulta 1: Natal → Fortaleza
        AStar.Resultado<String> res1 = astar.executar(natal, fortaleza);
        astar.imprimirResultado(res1, natal, fortaleza);

        System.out.println();

        // Consulta 2: Natal → Recife
        AStar.Resultado<String> res2 = astar.executar(natal, recife);
        astar.imprimirResultado(res2, natal, recife);

        System.out.println();

        // ================================================================
        // Comparação direta: mesmo par, dois algoritmos
        // ================================================================
        System.out.println("============================================================");
        System.out.println("  Comparação direta: Natal → Fortaleza");
        System.out.println("============================================================");

        // Dijkstra extrai o caminho para Fortaleza do resultado geral
        List<Vertice<String>> caminhoD = resDijk.caminho(fortaleza);
        double custoD = resDijk.distancia.get(fortaleza);

        System.out.printf("  Dijkstra  | custo: %6.1f | caminho: %s%n", custoD, caminhoD);
        System.out.printf("  A*        | custo: %6.1f | nós expandidos: %d | caminho: %s%n",
                res1.custoTotal, res1.nosExpandidos, res1.caminho);

        System.out.println();
        System.out.println("  (Dijkstra expande TODOS os vértices; A* foca no destino)");
        System.out.println("============================================================");
    }
}