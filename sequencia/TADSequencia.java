public class TADSequencia {
    private no header; // Nó sentinela inicial
    private no trailer; // Nó sentinela final
    private int size;

    public TADSequencia() {
        header = new no(null); // Sentinela inicial
        trailer = new no(null); // Sentinela final
        header.setProximo(trailer);
        trailer.setAnterior(header);
        size = 0;
    }

    
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkRank(int r) throws IndexOutOfBoundsException {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank fora dos limites.");
        }
    }

    public no nodeAtRank(int r) {
        no current = header.getProximo(); 
        for (int i = 0; i < r; i++) {
            current = current.getProximo();
        }
        return current;
    }

    private void checkNode(no node) {
        if (node == null || node == header) {
            throw new IllegalArgumentException("Nó inválido.");
        }
    }

    public Object elemAtRank(int r) throws IndexOutOfBoundsException {
        checkRank(r);
        return nodeAtRank(r).getValor();
    }

    public Object replaceAtRank(int r, Object o) throws IndexOutOfBoundsException {
        checkRank(r);
        no target = nodeAtRank(r);
        Object oldValue = target.getValor();
        target.setValor(o);
        return oldValue;
        
    }

    public void insertAtRank(int r, Object o) throws IndexOutOfBoundsException {
        if (r < 0 || r > size) {
            throw new IndexOutOfBoundsException("Rank fora dos limites.");
        }

        no nextNode = (r == size) ? trailer : nodeAtRank(r);
        no prevNode = nextNode.getAnterior();
        no newNode = new no(o);
        newNode.setProximo(nextNode);
        newNode.setAnterior(prevNode);
        prevNode.setProximo(newNode);
        nextNode.setAnterior(newNode);

        size++;
    }

    public Object removeAtRank(int r) throws IndexOutOfBoundsException {
        checkRank(r);
        no target = nodeAtRank(r);
        no prevNode = target.getAnterior();
        no nextNode = target.getProximo();

        prevNode.setProximo(nextNode);
        nextNode.setAnterior(prevNode);

        size--;
        return target.getValor();
    }

    public Object first() {
        if (isEmpty()) {
            throw new IllegalStateException("A sequência está vazia.");
        }
        return header.getProximo().getValor();
    }

    public Object last() {
        if (isEmpty()) {
            throw new IllegalStateException("A sequência está vazia.");
        }
        return trailer.getAnterior().getValor();
    }

    public Object before(no n) {
        checkNode(n);
        no prevNode = n.getAnterior();
        if (prevNode == header) {
            throw new IllegalStateException("Não há elemento anterior.");
        }
        return prevNode.getValor();
    }

    public Object after(no n) {
        checkNode(n);
        no nextNode = n.getProximo();
        if (nextNode == trailer) {
            throw new IllegalStateException("Não há elemento seguinte.");
        }
        return nextNode.getValor();
    }

    public Object replaceElement(no n, Object o) {
        checkNode(n);
        Object oldValue = n.getValor();
        n.setValor(o);
        return oldValue;
    }

    public void swapElements(no n, no q) {
        checkNode(n);
        checkNode(q);
        Object temp = n.getValor();
        n.setValor(q.getValor());
        q.setValor(temp);
    }

    public void insertBefore(no n, Object o) {
        checkNode(n);
        no prevNode = n.getAnterior();
        no newNode = new no(o);

        newNode.setAnterior(prevNode);
        newNode.setProximo(n);
        prevNode.setProximo(newNode);
        n.setAnterior(newNode);

        size++;
    }

    public void insertAfter(no n, Object o) {
        checkNode(n);
        no nextNode = n.getProximo();
        no newNode = new no(o);

        
        newNode.setAnterior(n);
        newNode.setProximo(nextNode);
        n.setProximo(newNode);
        nextNode.setAnterior(newNode);

        size++;
    }

    public void insertFirst(Object o) {
        insertBefore(header.getProximo(), o);
    }

    public void insertLast(Object o) {
        // Inserir antes do nó trailer
        insertBefore(trailer, o);
    }

    public Object remove(no n) {
        checkNode(n);
        no prevNode = n.getAnterior();
        no nextNode = n.getProximo();

       
        prevNode.setProximo(nextNode);
        nextNode.setAnterior(prevNode);

        size--;
        return n.getValor();
    }

    public no atRank(int r) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank fora dos limites.");
        }
        
        no currentNode = header.getProximo(); // Começa no primeiro nó real
        for (int i = 0; i < r; i++) {
            currentNode = currentNode.getProximo(); // Navega para o próximo nó
        }
        return currentNode;
    }

    public int rankOf(no n) {
        checkNode(n); // Garante que o nó é válido
    
        no currentNode = header.getProximo(); // Começa do primeiro nó válido
        int rank = 0;
        
        while (currentNode != n) {
            currentNode = currentNode.getProximo();
            rank++;
        }
        return rank;
    }

}
