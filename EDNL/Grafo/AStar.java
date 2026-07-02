package GRAFO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

/**
 * Algoritmo A* (A-estrela) para caminho mínimo entre dois vértices.
 *
 * Combina o custo real acumulado g(n) com uma heurística admissível h(n)
 * que estima o custo restante até o destino:
 *
 *      f(n) = g(n) + h(n)
 *
 * A heurística deve ser:
 *   - Admissível : h(n) <= custo_real(n, destino)  (nunca superestima)
 *   - Consistente: h(n) <= c(n,viz) + h(viz)        (garante ótimo com grafo)
 *
 * Com heurística h=0 o A* se reduz ao algoritmo de Dijkstra.
 *
 * Complexidade no pior caso: O((V + E) log V), melhor na prática quando
 * a heurística é informativa.
 */
public class AStar<TV> {

    /** Resultado de uma execução do A*. */
    public static class Resultado<TV> {
        public final List<Vertice<TV>> caminho;
        public final double custoTotal;
        public final int    nosExpandidos;

        Resultado(List<Vertice<TV>> caminho, double custoTotal, int nosExpandidos) {
            this.caminho       = caminho;
            this.custoTotal    = custoTotal;
            this.nosExpandidos = nosExpandidos;
        }
    }

    private final Grafo<TV, Double>              grafo;
    private final BiFunction<Vertice<TV>, Vertice<TV>, Double> heuristica;

    /**
     * @param grafo      grafo com pesos Double nas arestas
     * @param heuristica função h(v, destino) — deve ser admissível
     */
    public AStar(Grafo<TV, Double> grafo,
                 BiFunction<Vertice<TV>, Vertice<TV>, Double> heuristica) {
        this.grafo      = grafo;
        this.heuristica = heuristica;
    }

    /**
     * Executa o A* de {@code origem} até {@code destino}.
     *
     * @return Resultado com caminho, custo total e número de nós expandidos;
     *         caminho vazio se destino for inalcançável.
     */
    public Resultado<TV> executar(Vertice<TV> origem, Vertice<TV> destino) {

        // g(v): menor custo real conhecido da origem até v
        Map<Vertice<TV>, Double>      g        = new HashMap<>();
        // f(v) = g(v) + h(v)
        Map<Vertice<TV>, Double>      f        = new HashMap<>();
        Map<Vertice<TV>, Vertice<TV>> anterior = new HashMap<>();

        for (Vertice<TV> v : grafo.vertices()) {
            g.put(v, Double.MAX_VALUE);
            f.put(v, Double.MAX_VALUE);
        }
        g.put(origem, 0.0);
        f.put(origem, heuristica.apply(origem, destino));

        // Open set: min-heap por f(v); armazena [f, índice]
        PriorityQueue<double[]> aberto = new PriorityQueue<>(
            (a, b) -> Double.compare(a[0], b[0])
        );
        aberto.offer(new double[]{f.get(origem), origem.getIndice()});

        // Closed set: vértices já finalizados
        Map<Integer, Boolean> fechado = new HashMap<>();

        // Mapa índice → vértice
        Map<Integer, Vertice<TV>> porIndice = new HashMap<>();
        for (Vertice<TV> v : grafo.vertices()) porIndice.put(v.getIndice(), v);

        int nosExpandidos = 0;

        while (!aberto.isEmpty()) {
            double[]    entrada = aberto.poll();
            Vertice<TV> u       = porIndice.get((int) entrada[1]);

            // Lazy deletion: ignora se já fechado ou se a entrada é obsoleta
            if (fechado.getOrDefault(u.getIndice(), false)) continue;
            if (entrada[0] > f.get(u)) continue;

            fechado.put(u.getIndice(), true);
            nosExpandidos++;

            // Chegamos ao destino
            if (u.equals(destino)) {
                return new Resultado<>(reconstruirCaminho(anterior, destino),
                                       g.get(destino),
                                       nosExpandidos);
            }

            for (Aresta<Double> aresta : grafo.arestasIncidentes(u)) {
                // Para arestas dirigidas, só seguimos na direção correta
                if (aresta.isDirigida() && !aresta.getOrigem().equals(u)) continue;

                @SuppressWarnings("unchecked")
                Vertice<TV> viz = (Vertice<TV>) (aresta.getOrigem().equals(u)
                        ? aresta.getDestino()
                        : aresta.getOrigem());

                if (fechado.getOrDefault(viz.getIndice(), false)) continue;

                double tentativaG = g.get(u) + aresta.getElemento();
                if (tentativaG < g.get(viz)) {
                    g.put(viz, tentativaG);
                    double novoF = tentativaG + heuristica.apply(viz, destino);
                    f.put(viz, novoF);
                    anterior.put(viz, u);
                    aberto.offer(new double[]{novoF, viz.getIndice()});
                }
            }
        }

        // Destino inalcançável
        return new Resultado<>(Collections.emptyList(), Double.MAX_VALUE, nosExpandidos);
    }

    private List<Vertice<TV>> reconstruirCaminho(Map<Vertice<TV>, Vertice<TV>> anterior,
                                                  Vertice<TV> destino) {
        List<Vertice<TV>> caminho = new ArrayList<>();
        Vertice<TV> atual = destino;
        while (atual != null) {
            caminho.add(atual);
            atual = anterior.get(atual);
        }
        Collections.reverse(caminho);
        return caminho;
    }

    // -------------------------------------------------------------------------
    // Utilitário de impressão
    // -------------------------------------------------------------------------

    public void imprimirResultado(Resultado<TV> resultado,
                                  Vertice<TV> origem,
                                  Vertice<TV> destino) {
        System.out.println("=== A* — " + origem + " → " + destino + " ===");
        if (resultado.caminho.isEmpty()) {
            System.out.println("  Destino inalcançável.");
        } else {
            System.out.printf("  Custo total  : %.1f%n", resultado.custoTotal);
            System.out.printf("  Nós expandidos: %d%n", resultado.nosExpandidos);
            System.out.println("  Caminho      : " + resultado.caminho);
        }
    }
}