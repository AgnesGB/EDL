import java.util.ArrayList;

/**
 * TAD Grafo implementado com Matriz de Adjacência.
 * Suporta grafos simples e multigrafos mistos (arestas direcionadas,
 * não-direcionadas, paralelas e laços).
 *
 * Cada célula da matriz é um ArrayList<Aresta<A>> que armazena
 * todas as arestas entre o par de vértices (i, j).
 *
 * @param <V> tipo do valor armazenado nos vértices
 * @param <A> tipo do valor armazenado nas arestas
 */
public class Grafo<V, A> {

    private ArrayList<Vertice<V>> listaVertices;

    // matrizAdj.get(i).get(j) = lista de arestas de i para j
    // Aresta não-direcionada (v,w): aparece em [i][j] E em [j][i] (mesmo objeto)
    // Aresta direcionada (v,w):     aparece somente em [i][j]
    // Laço (v,v):                   aparece somente em [i][i]
    private ArrayList<ArrayList<ArrayList<Aresta<A>>>> matrizAdj;

    public Grafo() {
        listaVertices = new ArrayList<>();
        matrizAdj = new ArrayList<>();
    }

    // ===================================================================
    // MÉTODOS DE ATUALIZAÇÃO
    // ===================================================================

    /**
     * Insere e retorna um novo vértice armazenando o elemento o.
     */
    public Vertice<V> inserirVertice(V elemento) {
        int novoIdx = listaVertices.size();
        Vertice<V> v = new Vertice<>(elemento, novoIdx);
        listaVertices.add(v);

        // Adiciona nova coluna (célula) em cada linha já existente
        for (ArrayList<ArrayList<Aresta<A>>> linha : matrizAdj) {
            linha.add(new ArrayList<>());
        }

        // Adiciona nova linha com (novoIdx + 1) células
        ArrayList<ArrayList<Aresta<A>>> novaLinha = new ArrayList<>();
        for (int j = 0; j <= novoIdx; j++) {
            novaLinha.add(new ArrayList<>());
        }
        matrizAdj.add(novaLinha);

        return v;
    }

    /**
     * Insere e retorna uma nova aresta NÃO-DIRIGIDA (v, w) armazenando o elemento o.
     */
    public Aresta<A> inserirAresta(Vertice<V> v, Vertice<V> w, A elemento) {
        validarVertices(v, w);
        Aresta<A> aresta = new Aresta<>(elemento, v, w, false);
        int i = v.getIndice();
        int j = w.getIndice();
        matrizAdj.get(i).get(j).add(aresta);
        if (i != j) {                              // evita duplicar laços
            matrizAdj.get(j).get(i).add(aresta);
        }
        return aresta;
    }

    /**
     * Insere e retorna uma nova aresta DIRIGIDA de v para w armazenando o elemento o.
     */
    public Aresta<A> inserirArestaDirecionada(Vertice<V> v, Vertice<V> w, A elemento) {
        validarVertices(v, w);
        Aresta<A> aresta = new Aresta<>(elemento, v, w, true);
        matrizAdj.get(v.getIndice()).get(w.getIndice()).add(aresta);
        return aresta;
    }

    /**
     * Remove o vértice v e todas as suas arestas incidentes.
     * Retorna o elemento armazenado em v.
     */
    public V removeVertice(Vertice<V> v) {
        int idx = v.getIndice();
        V elemento = v.getElemento();

        matrizAdj.remove(idx);                            // remove linha
        for (ArrayList<ArrayList<Aresta<A>>> linha : matrizAdj) {
            linha.remove(idx);                            // remove coluna
        }

        listaVertices.remove(idx);
        for (int i = idx; i < listaVertices.size(); i++) {
            listaVertices.get(i).setIndice(i);            // atualiza índices
        }

        return elemento;
    }

    /**
     * Remove a aresta e e retorna o elemento armazenado nela.
     */
    public A removeAresta(Aresta<A> e) {
        int i = e.getOrigem().getIndice();
        int j = e.getDestino().getIndice();
        matrizAdj.get(i).get(j).remove(e);
        if (!e.isDirecionada() && i != j) {
            matrizAdj.get(j).get(i).remove(e);
        }
        return e.getElemento();
    }

    // ===================================================================
    // MÉTODOS DE ACESSO
    // ===================================================================

    /**
     * Retorna um array com os dois vértices finais da aresta e.
     */
    @SuppressWarnings("unchecked")
    public Vertice<V>[] finalVertices(Aresta<A> e) {
        Vertice<V>[] resultado = new Vertice[2];
        resultado[0] = (Vertice<V>) e.getOrigem();
        resultado[1] = (Vertice<V>) e.getDestino();
        return resultado;
    }

    /**
     * Retorna o vértice oposto a v na aresta e.
     * Lança exceção se v não for incidente a e.
     */
    @SuppressWarnings("unchecked")
    public Vertice<V> oposto(Vertice<V> v, Aresta<A> e) {
        if (e.getOrigem().equals(v))  return (Vertice<V>) e.getDestino();
        if (e.getDestino().equals(v)) return (Vertice<V>) e.getOrigem();
        throw new IllegalArgumentException("Vértice " + v + " não é incidente à aresta " + e);
    }

    /**
     * Retorna true se v e w são adjacentes (existe ao menos uma aresta entre eles).
     */
    public boolean eAdjacente(Vertice<V> v, Vertice<V> w) {
        int i = v.getIndice();
        int j = w.getIndice();
        return !matrizAdj.get(i).get(j).isEmpty()
            || !matrizAdj.get(j).get(i).isEmpty();
    }

    /**
     * Substitui o elemento armazenado no vértice v pelo valor x.
     */
    public void substituir(Vertice<V> v, V x) {
        v.setElemento(x);
    }

    /**
     * Substitui o elemento armazenado na aresta e pelo valor x.
     */
    public void substituir(Aresta<A> e, A x) {
        e.setElemento(x);
    }

    /**
     * Retorna true se a aresta e for direcionada.
     */
    public boolean eDirecionado(Aresta<A> e) {
        return e.isDirecionada();
    }

    // ===================================================================
    // MÉTODOS ITERADORES
    // ===================================================================

    /**
     * Retorna uma coleção de todos os vértices do grafo.
     */
    public ArrayList<Vertice<V>> vertices() {
        return new ArrayList<>(listaVertices);
    }

    /**
     * Retorna uma coleção de todas as arestas do grafo.
     * Cada aresta aparece apenas uma vez, mesmo que esteja em duas
     * células da matriz (caso não-direcionado).
     */
    public ArrayList<Aresta<A>> arestas() {
        ArrayList<Aresta<A>> resultado = new ArrayList<>();
        int n = listaVertices.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (Aresta<A> e : matrizAdj.get(i).get(j)) {
                    if (!resultado.contains(e)) {
                        resultado.add(e);
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Retorna uma coleção de todas as arestas incidentes ao vértice v
     * (saindo, chegando ou laços).
     */
    public ArrayList<Aresta<A>> arestasIncidentes(Vertice<V> v) {
        ArrayList<Aresta<A>> resultado = new ArrayList<>();
        int idx = v.getIndice();
        int n   = listaVertices.size();

        // Percorre a linha idx: arestas que saem de v (e não-direcionadas)
        for (int j = 0; j < n; j++) {
            for (Aresta<A> e : matrizAdj.get(idx).get(j)) {
                if (!resultado.contains(e)) resultado.add(e);
            }
        }

        // Percorre a coluna idx: captura arestas DIRIGIDAS que chegam em v
        for (int i = 0; i < n; i++) {
            if (i == idx) continue;               // laço já foi pego acima
            for (Aresta<A> e : matrizAdj.get(i).get(idx)) {
                if (!resultado.contains(e)) resultado.add(e);
            }
        }

        return resultado;
    }

    // ===================================================================
    // MÉTODOS AUXILIARES
    // ===================================================================

    /** Número de vértices (ordem) do grafo. */
    public int ordem() {
        return listaVertices.size();
    }

    /** Número total de arestas do grafo. */
    public int tamanho() {
        return arestas().size();
    }

    /**
     * Imprime a matriz de adjacência mostrando a quantidade de arestas
     * em cada célula.
     */
    public void imprimirMatriz() {
        int n = listaVertices.size();
        System.out.print("        ");
        for (Vertice<V> v : listaVertices) {
            System.out.printf("%-8s", v.getElemento());
        }
        System.out.println();
        System.out.print("        ");
        System.out.println("-".repeat(8 * n));

        for (int i = 0; i < n; i++) {
            System.out.printf("%-8s", listaVertices.get(i).getElemento());
            for (int j = 0; j < n; j++) {
                int qtd = matrizAdj.get(i).get(j).size();
                System.out.printf("%-8s", qtd == 0 ? "." : qtd);
            }
            System.out.println();
        }
    }

    private void validarVertices(Vertice<V> v, Vertice<V> w) {
        if (!listaVertices.contains(v) || !listaVertices.contains(w)) {
            throw new IllegalArgumentException("Um ou mais vértices não pertencem ao grafo.");
        }
    }
}
