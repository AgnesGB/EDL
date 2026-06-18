public class Aresta<T> {
    private T elemento;
    private Vertice<?> origem;
    private Vertice<?> destino;
    private boolean direcionada;

    public Aresta(T elemento, Vertice<?> origem, Vertice<?> destino, boolean direcionada) {
        this.elemento = elemento;
        this.origem = origem;
        this.destino = destino;
        this.direcionada = direcionada;
    }

    public T getElemento() { return elemento; }
    public void setElemento(T elemento) { this.elemento = elemento; }
    public Vertice<?> getOrigem() { return origem; }
    public Vertice<?> getDestino() { return destino; }
    public boolean isDirecionada() { return direcionada; }

    @Override
    public String toString() {
        String seta = direcionada ? " -> " : " -- ";
        return "Aresta(" + elemento + ": " + origem + seta + destino + ")";
    }
}
