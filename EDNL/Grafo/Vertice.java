public class Vertice<T> {
    private T elemento;
    private int indice;

    public Vertice(T elemento, int indice) {
        this.elemento = elemento;
        this.indice = indice;
    }

    public T getElemento() { return elemento; }
    public void setElemento(T elemento) { this.elemento = elemento; }
    public int getIndice() { return indice; }
    void setIndice(int indice) { this.indice = indice; }

    @Override
    public String toString() {
        return "V[" + elemento + "]";
    }
}
