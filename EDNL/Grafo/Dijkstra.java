package GRAFO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Algoritmo de Dijkstra para caminhos mínimos de fonte única.
 *
 * Encontra o menor custo (soma dos pesos das arestas) do vértice
 * de origem até todos os demais vértices alcançáveis do grafo.
 *
 * Restrição: todos os pesos das arestas devem ser não-negativos.
 *
 * Complexidade: O((V + E) log V) com fila de prioridade (min-heap).
 */
public class Dijkstra<TV> {

    /** Encapsula o resultado de uma execução do algoritmo. */
    public static class Resultado<TV> {
        public final Map<Vertice<TV>, Double> distancia;
        public final Map<Vertice<TV>, Vertice<TV>> anterior;

        Resultado(Map<Vertice<TV>, Double> distancia,
                  Map<Vertice<TV>, Vertice<TV>> anterior) {
            this.distancia = distancia;
            this.anterior  = anterior;
        }

        /** Reconstrói o caminho da origem até o destino. */
        public List<Vertice<TV>> caminho(Vertice<TV> destino) {
            List<Vertice<TV>> path = new ArrayList<>();
            Vertice<TV> atual = destino;
            while (atual != null) {
                path.add(atual);
                atual = anterior.get(atual);
            }
            Collections.reverse(path);
            // Caminho vazio significa que destino é inalcançável
            if (path.size() == 1 && distancia.getOrDefault(path.get(0), Double.MAX_VALUE) == Double.MAX_VALUE) {
                return Collections.emptyList();
            }
            return path;
        }
    }

    private final Grafo<TV, Double> grafo;

    public Dijkstra(Grafo<TV, Double> grafo) {
        this.grafo = grafo;
    }

    /**
     * Executa Dijkstra a partir de {@code origem}.
     *
     * @param origem vértice inicial
     * @return Resultado com mapa de distâncias e mapa de predecessores
     */
    public Resultado<TV> executar(Vertice<TV> origem) {
        Map<Vertice<TV>, Double>         dist     = new HashMap<>();
        Map<Vertice<TV>, Vertice<TV>>    anterior = new HashMap<>();

        // Inicializa todas as distâncias como infinito
        for (Vertice<TV> v : grafo.vertices()) {
            dist.put(v, Double.MAX_VALUE);
            anterior.put(v, null);
        }
        dist.put(origem, 0.0);

        // Min-heap: (distância, vértice)
        PriorityQueue<double[]> fila = new PriorityQueue<>(
            (a, b) -> Double.compare(a[0], b[0])
        );
        // Guarda índice do vértice para recuperá-lo da fila
        Map<Double[], Vertice<TV>> mapaFila = new HashMap<>();

        // Mapa auxiliar: índice do vértice → vértice (para recuperar da fila pelo índice)
        List<Vertice<TV>> vertices = grafo.vertices();
        Map<Integer, Vertice<TV>> porIndice = new HashMap<>();
        for (Vertice<TV> v : vertices) porIndice.put(v.getIndice(), v);

        fila.offer(new double[]{0.0, origem.getIndice()});

        while (!fila.isEmpty()) {
            double[] entrada = fila.poll();
            double   custoU  = entrada[0];
            Vertice<TV> u    = porIndice.get((int) entrada[1]);

            // Descarta entradas obsoletas (lazy deletion)
            if (custoU > dist.get(u)) continue;

            for (Aresta<Double> aresta : grafo.arestasIncidentes(u)) {
                // Para arestas dirigidas só seguimos na direção correta
                if (aresta.isDirigida() && !aresta.getOrigem().equals(u)) continue;

                @SuppressWarnings("unchecked")
                Vertice<TV> v = (Vertice<TV>) (aresta.getOrigem().equals(u)
                        ? aresta.getDestino()
                        : aresta.getOrigem());

                double novaDist = dist.get(u) + aresta.getElemento();
                if (novaDist < dist.get(v)) {
                    dist.put(v, novaDist);
                    anterior.put(v, u);
                    fila.offer(new double[]{novaDist, v.getIndice()});
                }
            }
        }

        return new Resultado<>(dist, anterior);
    }

    // -------------------------------------------------------------------------
    // Utilitário de impressão
    // -------------------------------------------------------------------------

    /** Imprime as distâncias e o caminho mínimo da origem a cada vértice. */
    public void imprimirResultado(Resultado<TV> resultado, Vertice<TV> origem) {
        System.out.println("=== Dijkstra — origem: " + origem + " ===");
        for (Map.Entry<Vertice<TV>, Double> entry : resultado.distancia.entrySet()) {
            Vertice<TV> dest = entry.getKey();
            double dist = entry.getValue();
            if (dest.equals(origem)) continue;
            if (dist == Double.MAX_VALUE) {
                System.out.println("  " + dest + " -> inalcançável");
            } else {
                List<Vertice<TV>> cam = resultado.caminho(dest);
                System.out.printf("  %-20s  custo = %6.1f  caminho: %s%n",
                        dest, dist, cam);
            }
        }
    }
}