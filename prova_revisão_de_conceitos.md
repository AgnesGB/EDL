## **1. Heapsort**
### **O que é?**
O **Heapsort** é um algoritmo de ordenação baseado na estrutura de dados chamada **Heap**, que é uma árvore binária onde os elementos seguem uma propriedade específica:
- **Max-Heap:** O pai é maior ou igual aos filhos.
- **Min-Heap:** O pai é menor ou igual aos filhos.

### **Passos do Heapsort**
1. **Construir um Heap** a partir do array (Max-Heap para ordenar em ordem crescente).
2. **Repetidamente remover o maior elemento** (a raiz), colocando-o no final do array.
3. **Reajustar o Heap** para manter a propriedade de Heap após cada remoção.

### **Regras do Heap**
- **Inserção:** O novo elemento entra na última posição e é ajustado para cima (`upheap`).
- **Remoção:** Remove-se a raiz e o último elemento a substitui, então ajustamos para baixo (`downheap`).
- **Heapify:** Processo de transformar um array em um Heap.

### **Complexidade**
- **O(n log n)** no pior, médio e melhor caso.
- **Não é estável** (elementos iguais podem trocar de posição).
- **É um algoritmo em-place** (não usa memória extra significativa).

### **Código Java**
```java
import java.util.Arrays;

class MaxHeap {
    private int[] heap;
    private int size;
    
    public MaxHeap(int capacity) {
        heap = new int[capacity + 1]; // Índice começa em 1
        size = 0;
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    private void upHeap(int i) {
        while (i > 1 && heap[i] > heap[i / 2]) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public void insert(int value) {
        if (size + 1 >= heap.length) throw new RuntimeException("Heap cheio");
        heap[++size] = value;
        upHeap(size);
    }

    private void downHeap(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && heap[j] < heap[j + 1]) j++; 
            if (heap[i] >= heap[j]) break;
            swap(i, j);
            i = j;
        }
    }

    public int removeMax() {
        if (size == 0) throw new RuntimeException("Heap vazio");
        int max = heap[1];
        heap[1] = heap[size--];
        downHeap(1);
        return max;
    }

    public void heapify(int[] array) {
        heap = new int[array.length + 1];
        size = array.length;
        System.arraycopy(array, 0, heap, 1, array.length);
        for (int i = size / 2; i >= 1; i--) {
            downHeap(i);
        }
    }

    public static MaxHeap mergeHeaps(MaxHeap h1, MaxHeap h2) {
        int[] mergedArray = new int[h1.size + h2.size];
        System.arraycopy(h1.heap, 1, mergedArray, 0, h1.size);
        System.arraycopy(h2.heap, 1, mergedArray, h1.size, h2.size);

        MaxHeap mergedHeap = new MaxHeap(mergedArray.length);
        mergedHeap.heapify(mergedArray);
        return mergedHeap;
    }

    public void printHeap() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(heap, 1, size + 1)));
    }
}
```

### **Operações em Heaps**
1. **Inserção (Insert)**
   - Adiciona um novo elemento no último nó disponível.
   - **Upheap:** Caso a propriedade heap-order seja violada, o elemento é "subido" até encontrar sua posição correta. Complexidade: \( O(\log n) \).

2. **Remoção do Mínimo (RemoveMin)**
   - A raiz do heap (menor valor em um min-heap) é removida e substituída pelo último nó.
   - **Downheap:** Caso a heap-order seja violada, o nó é "descido" até encontrar sua posição correta. Complexidade: \( O(\log n) \).

3. **Heap-Sort**
   - Ordenação usando um heap baseado em **fila de prioridade**.
   - Complexidade: \( O(n \log n) \).
   - Muito mais eficiente do que algoritmos quadráticos como **insertion sort** e **selection sort**.

### **Implementação com Vetor**
- Um heap pode ser armazenado em um vetor:
  - Pai no índice \( i \).
  - Filho esquerdo em \( 2i \).
  - Filho direito em \( 2i + 1 \).

### **Fusão de Heaps**
- Para juntar dois heaps, cria-se um novo heap com uma raiz e as duas subárvores.
- Aplica-se **downheap** para restaurar a heap-order.
---

## **2. Hash Table (Tabela Hash)**
### **O que é?**
Uma **Hash Table** é uma estrutura que **mapeia chaves para valores** usando uma **função hash**, que transforma a chave em um índice de array.

### **Regras da Hash Table**
- **Função Hash (h(k))** → Calcula o índice do array onde o valor será armazenado.
- **Colisões** → Ocorrem quando duas chaves geram o mesmo índice.

### **Maneiras de tratar colisões**
1. **Endereçamento Aberto** (usa posições vazias na tabela):
   - **Linear Probing:** Se houver colisão, tenta a próxima posição.
   - **Quadratic Probing:** Tenta posições seguindo um padrão quadrático.
   - **Double Hashing:** Usa uma segunda função hash para resolver colisões.
   
2. **Encadeamento Separado** (listas ligadas nos índices):
   - Cada posição da tabela tem uma lista encadeada onde os valores colididos são armazenados.

### **Complexidade**
- **Busca média: O(1)** (tempo constante, se não houver muitas colisões).
- **Pior caso: O(n)** (se todas as chaves caírem na mesma posição).  

### **TAD Dicionário**
```java
import java.util.Arrays;

class HashTable {
    private static final int NO_SUCH_KEY = -1;
    private static final int EMPTY = -99999;
    private int[] keys;
    private int[] values;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keys = new int[capacity];
        this.values = new int[capacity];
        Arrays.fill(keys, EMPTY);
    }

    private int hashFunction(int key) {
        return (key * 31) % capacity; // h1: chave → inteiro
    }

    private int compressionFunction(int hash) {
        return Math.abs(hash) % capacity; // h2: inteiro → índice
    }

    private int findIndex(int key) {
        int index = compressionFunction(hashFunction(key));
        while (keys[index] != EMPTY && keys[index] != key) {
            index = (index + 1) % capacity; // Linear Probing
        }
        return index;
    }

    public void insertItem(int key, int value) {
        int index = findIndex(key);
        if (keys[index] == EMPTY) size++;
        keys[index] = key;
        values[index] = value;
    }

    public int findElement(int key) {
        int index = findIndex(key);
        return keys[index] == key ? values[index] : NO_SUCH_KEY;
    }

    public int removeElement(int key) {
        int index = findIndex(key);
        if (keys[index] == key) {
            int value = values[index];
            keys[index] = EMPTY;
            size--;
            return value;
        }
        return NO_SUCH_KEY;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
```

### **TAD Dicionário**
- Modela uma coleção de itens **chave-elemento**.
- Suporta operações básicas como:
  - **findElement(k)** → Retorna o elemento associado à chave \( k \).
  - **insertItem(k, o)** → Insere um novo item no dicionário.
  - **removeElement(k)** → Remove um item pela chave \( k \).
- Aplicações: **Agendas, autorização de cartões de crédito, mapeamento de hosts para IPs**.

### **Implementações de Dicionário**
1. **Arquivo de LOG (Sequência Não Ordenada)**
   - Armazena os itens em uma ordem arbitrária.
   - **Vantagens:** Inserção em \( O(1) \).
   - **Desvantagens:** Busca e remoção em \( O(n) \).

2. **Busca Binária (Array Ordenado)**
   - Usada em tabelas de pesquisa ordenadas.
   - **Vantagens:** Busca em \( O(\log n) \).
   - **Desvantagens:** Inserção e remoção em \( O(n) \) (pois é necessário deslocar elementos).

### **Tabelas de Dispersão (Hash Tables)**
- Utilizam uma **função de dispersão (hash function)** para mapear chaves em índices de um vetor de tamanho fixo \( N \).
- **Colisões** (quando duas chaves têm o mesmo índice) são resolvidas por:
  - **Encadeamento**: Itens que colidem são armazenados em listas vinculadas.
  - **Endereçamento aberto**: Busca-se outro local na tabela.

### **Funções de Dispersão (Hash Functions)**
- **Fórmulas comuns:**
  - \( h(x) = x \mod N \) (para chaves inteiras).
  - **Acumulação Polinomial** para strings: \( p(z) = a_0 + a_1 z + a_2 z^2 + \dots + a_{n-1}z^{n-1} \).

### **Técnicas de Resolução de Colisões**
1. **Linear Probing**: Procura o próximo espaço disponível sequencialmente.
2. **Hashing Duplo**: Usa uma segunda função de dispersão \( d(k) \) para definir o deslocamento.

### **Desempenho**
- No **pior caso**, as operações podem rodar em \( O(n) \).
- **Caso médio**: Tempo esperado \( O(1) \) para busca, inserção e remoção, se a dispersão for eficiente.

### **Aplicações de Hash Tables**
- **Pequenos bancos de dados, compiladores, caches de navegadores**.

---

## **3. Skip List**
### **O que é?**
- Uma **Skip List** é uma estrutura de dados baseada em múltiplas listas ligadas ordenadas em **níveis**.
- Cada nível é uma **subsequência** do nível anterior, contendo apenas alguns elementos selecionados aleatoriamente.
- A estrutura contém **chaves especiais**: \( -\infty \) e \( +\infty \), representando o início e o fim.
- Utilizada para **implementar dicionários** de forma eficiente.

### **Regras da Skip List**
- Cada nó pode ter **vários níveis** de altura.
- O nível 0 tem todos os elementos (lista normal).
- Níveis superiores contêm apenas alguns elementos, servindo como "atalhos".
- A altura dos nós é determinada aleatoriamente (probabilidade de 50%).

### **Operações**
1. **Busca:** Começa do topo e desce quando necessário, ignorando várias etapas, tornando-se mais rápida que uma lista normal.
2. **Inserção:** Adiciona na lista normal e decide, aleatoriamente, se o elemento sobe de nível.
3. **Remoção:** Remove o elemento de todos os níveis onde ele aparece.

### **Complexidade**
- **Média: O(log n)**
- **Pior caso: O(n)** (se a distribuição dos níveis for ruim).

### **Por que usar Skip List?**
- Simples de implementar, diferente de árvores balanceadas.
- Boa para buscas rápidas sem precisar de balanceamento complexo.

### **Código Java**
```java
import java.util.Random;

class SkipListNode {
    int key;
    int value;
    SkipListNode[] next;

    public SkipListNode(int key, int value, int level) {
        this.key = key;
        this.value = value;
        this.next = new SkipListNode[level + 1];
    }
}

class SkipList {
    private static final int MAX_LEVEL = 4;
    private final SkipListNode head;
    private int level;
    private final Random rand;

    public SkipList() {
        this.head = new SkipListNode(-1, -1, MAX_LEVEL);
        this.level = 0;
        this.rand = new Random();
    }

    private int randomLevel() {
        int lvl = 0;
        while (lvl < MAX_LEVEL && rand.nextDouble() < 0.5) lvl++;
        return lvl;
    }

    public void insert(int key, int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;

        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key < key) {
                current = current.next[i];
            }
            update[i] = current;
        }

        int newLevel = randomLevel();
        if (newLevel > level) {
            level = newLevel;
        }

        SkipListNode newNode = new SkipListNode(key, value, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public Integer search(int key) {
        SkipListNode current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].key < key) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return (current != null && current.key == key) ? current.value : null;
    }
}
```

### **Operações em Skip Lists**
1. **Busca (\( O(\log n) \))**
   - Inicia no topo e segue para frente até encontrar um valor maior.
   - Se o valor desejado for maior, desce para o próximo nível.
   - Repete até encontrar o elemento ou determinar que ele não existe.

2. **Inserção (\( O(\log n) \))**
   - Determina aleatoriamente quantos níveis o novo elemento terá.
   - Insere o elemento nos níveis correspondentes.
   - Se necessário, adiciona um novo nível ao Skip List.

3. **Remoção (\( O(\log n) \))**
   - Encontra o elemento em todos os níveis e o remove.
   - Se um nível ficar vazio, ele é descartado.

---

### **Implementação**
- Cada nó da Skip List (chamado **quad-node**) contém:
  - Um **item**.
  - **Links** para o nó anterior e posterior (horizontalmente).
  - **Links** para o nó acima e abaixo (verticalmente).

---

### **Análise de Complexidade**
- **Espaço utilizado**: \( O(n) \)
- **Altura da estrutura**: \( O(\log n) \) com alta probabilidade.
- **Tempo esperado para busca, inserção e remoção**: \( O(\log n) \).

---

### **Conclusão**
- **Skip Lists** são uma alternativa eficiente a **árvores balanceadas** para implementar dicionários.
- São **fáceis de implementar** e oferecem um bom desempenho esperado.
- O uso de um **algoritmo randômico** garante boa distribuição dos elementos nos níveis.

---

## **Resumo**
| Estrutura | O que faz | Vantagens | Complexidade Média |
|-----------|----------|------------|--------------------|
| **Heapsort** | Ordena elementos usando Heap | Sempre O(n log n), eficiente sem precisar de memória extra | O(n log n) |
| **Hash Table** | Mapeia chave → valor com uma função hash | Busca e inserção muito rápidas, O(1) na média | O(1) |
| **Skip List** | Lista encadeada com atalhos para busca rápida | Simples e eficiente sem balanceamento | O(log n) |