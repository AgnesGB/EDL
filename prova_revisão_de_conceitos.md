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

---

## **3. Skip List**
### **O que é?**
Uma **Skip List** é uma estrutura baseada em **listas encadeadas ordenadas**, mas com atalhos para acelerar as operações de busca.

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

---

## **Resumo**
| Estrutura | O que faz | Vantagens | Complexidade Média |
|-----------|----------|------------|--------------------|
| **Heapsort** | Ordena elementos usando Heap | Sempre O(n log n), eficiente sem precisar de memória extra | O(n log n) |
| **Hash Table** | Mapeia chave → valor com uma função hash | Busca e inserção muito rápidas, O(1) na média | O(1) |
| **Skip List** | Lista encadeada com atalhos para busca rápida | Simples e eficiente sem balanceamento | O(log n) |