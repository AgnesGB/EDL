1) is heap. usando minheap.
contar os numeros da arvore.

```java

private int contarNos(No raiz){
    if (raiz == null){return 0;} //chegou no final.
    return 1+ contarNos(raiz.esquerda) + contarNos(raiz.direita);
}
private boolean ehCompleta(No raiz, int index, int totalNos){
    if (raiz == null){
        return true;
    }
    //verifica se o indice do nó entá dentro do n de nós.
    if (index>=totalNos){ //se o index for maior que o num de nós, está fora do range, n é heap.
        return false;
    }

    return ehCompleta(raiz.esquerda, 2*index +1, totalNos) && ehCompleta(raiz.direita, 2*index +2, totalNos);
}
private boolean segueProp(No raiz){
    if (raiz == null){return true;}
    if (raiz.esquerda != null && raiz.esquerda.valor < raiz.valor){
        return false;
    }
    if (raiz.direita != null && raiz.direita.valor < raiz.valor){
        return false;
    } (se o filho esquerdo ou direito for menor que o pai, retorna falso.)
    return segueProp(raiz.esquerda) && segueProp(raiz.direita);
}

public boolean isHeap(No raiz){
    int totalNos = contarNos(raiz);
    return ehCompleta(raiz, 0, totalNos) && segueProp(raiz);
}
```

2) Usando minHeap

Entrada: 7, 8, 15, 3, 20, 2, 16, 6

primeiro insere.
7 -> heap:[7];
8 -> heap:[7, 8] (esquerdo)
15 -> heap:[7, 8, 15] (direito)
3 -> heap: [7, 8, 15, 3] -> upheap. (esquerdo. 3<7, sobe, 3<8, sobe.) [3, 7, 15, 8]
20 -> heap:[3, 7, 15, 8, 20] (direito do 7)
2 -> heap:[3, 7, 15, 8, 20, 2] -> upheap.(esquerdo do 15. 2<15, sobe, 2< 3, sobe) [2, 7, 3, 8, 20, 15] 
16 -> heap:[2, 7, 3, 8, 20, 15, 16] (direito do 3)
6 -> heap:[2, 7, 3, 8, 20, 15, 16, 6] (esquerdo do 8. 6<8, sobe. 6<7, sobe) [2, 6, 3, 7, 20, 15, 16, 8]

remover o menor.
remove o 2, 8 vai pra cima. heapify. menor 3. troca de lugar com 8. 8<15/16? não. [3, 6, 8, 7, 20, 15, 16]
remove 3. 16 vai pra cima. [16, 6, 8, 7, 20, 15] heapify. menor 6. troca de lugar com o 16. [6, 16, 8, 7, 20, 15] 16 menor 7, troca de lugar com o 7. [6, 7, 8, 16, 20, 15]
remove 6. 15 vai pra cima. [15, 7, 8, 16, 20]. 15 maior, 7 menor, troca. [7, 15, 8, 16, 20] 15<16 ou 20? não.
remove o 7. 20 vai pra cima. [20, 15, 8, 16]. 20 maior, 8 menor. troca. [8, 15, 20, 16].
remove o 8. 16 vai pra cima. [16, 15, 20]. 16 maior. 15 menor. troca. [15, 16, 20]
remove o 15. 20 vai pra cima. [20, 16]. 20 maior. 16 troca. [16, 20].
remove o 16. 20 vai pra cima. [20].
remove o 20. heap vazio.

array: [2, 3, 6, 7, 8, 15, 16, 20]

3) espelho de arvore

```java
public void espelho(No no){
    if (no == null){
        return;
    }
    espelho(no.esquerdo);
    espelho(no.direito);

    No temp = no.esquerdo;
    no.direito = no.esquerdo;
    no.esquerdo = temp;
}
```

4) inserir na hash table:

### **4. Inserção na Hash Table com função `h(i) = i mod 7`**
Vamos calcular `i mod 7` para cada valor e inserir na tabela com **endereçamento aberto (teste linear)**.

#### **Passo 1: Calcular `h(i) = i mod 7`**
| Valor | h(i) = i mod 7 |
|--------|---------------|
| 51     | 51 mod 7 = 2  |
| 9      | 9 mod 7 = 2   |
| 13     | 13 mod 7 = 6  |
| 7      | 7 mod 7 = 0   |
| 24     | 24 mod 7 = 3  |
| 55     | 55 mod 7 = 6  |
| 37     | 37 mod 7 = 2  |

#### **Passo 2: Inserção com teste linear**
Se houver colisão, procuramos a próxima posição disponível.

1. `51` → Índice `2`
2. `9` → Índice `2` ocupado → Vai para `3`
3. `13` → Índice `6`
4. `7` → Índice `0`

Ocupou metade. rehash. proximo primo após o dobro do mod. 7*2=14. prox=17.

### Inserir os valores na nova tabela de tamanho 17
Agora, vamos inserir os valores na tabela com 17 posições, seguindo a lógica de resolução de colisões (endereçamento aberto com teste linear).

1. **51** → Índice 0
2. **9** → Índice 9
3. **13** → Índice 13
4. **7** → Índice 7
5. **24** → Índice 7 ocupado → Vai para 8
6. **55** → Índice 4
7. **37** → Índice 3

### Passo 4: Tabela final após o rehash:

| Índice | Valor |
|--------|-------|
| 0      | 51    |
| 1      |       |
| 2      |       |
| 3      | 37    |
| 4      | 55    |
| 5      |       |
| 6      |       |
| 7      | 7     |
| 8      | 24    |
| 9      | 9     |
| 10     |       |
| 11     |       |
| 12     |       |
| 13     | 13    |
| 14     |       |
| 15     |       |
| 16     |       |

Agora, a tabela foi reorganizada após o rehash, com tamanho 17, e todos os valores foram realocados corretamente com base na nova função de hash.

se fosse com hashing duplo:

Quando usamos **hashing duplo** (ou **double hashing**), estamos utilizando **duas funções de hash** para resolver colisões de maneira mais eficiente, reduzindo o risco de ciclos infinitos durante a tentativa de encontrar um novo índice.

### Como funciona o Hashing Duplo?

Em vez de usar apenas uma função de hash para calcular o índice, o **hashing duplo** usa duas funções:

1. **Primeira função de hash** (`h1(i)`) - como o cálculo do índice padrão, por exemplo `i mod 17`.
2. **Segunda função de hash** (`h2(i)`) - utilizada para calcular o deslocamento em caso de colisão, como `i mod 5`.

A ideia é, quando ocorre uma colisão no índice calculado por `h1(i)`, usarmos a segunda função `h2(i)` para determinar a quantidade de deslocamento e buscar a próxima posição disponível na tabela.

### Fórmula do Hashing Duplo

Se houver uma colisão no índice gerado por `h1(i)`, usamos a fórmula:

```
novo_indice = (h1(i) + j * h2(i)) % tabela_size
```

Onde:
- **`h1(i)`** é a primeira função de hash (por exemplo, `i mod 17`).
- **`h2(i)`** é a segunda função de hash (por exemplo, `i mod 5`).
- **`j`** é o número de tentativas (ou colisões). Começa de 1, e aumenta a cada nova colisão.
- **`tabela_size`** é o tamanho da tabela (neste caso, o novo tamanho seria 17, que já foi calculado).

### Exemplo de Hashing Duplo

Vamos supor que temos as seguintes duas funções de hash:
- **`h1(i) = i mod 17`** (primeira função).
- **`h2(i) = i mod 5`** (segunda função).

E queremos inserir os seguintes valores na tabela de tamanho 17 (depois de um rehash):

- **Valores a inserir:** 51, 9, 13, 7, 24, 55, 37.

#### Cálculos de `h1(i)` e `h2(i)`:

| Valor | h1(i) = i mod 17 | h2(i) = i mod 5 |
|-------|------------------|-----------------|
| 51    | 51 mod 17 = 0    | 51 mod 5 = 1    |
| 9     | 9 mod 17 = 9     | 9 mod 5 = 4     |
| 13    | 13 mod 17 = 13   | 13 mod 5 = 3    |
| 7     | 7 mod 17 = 7     | 7 mod 5 = 2     |
| 24    | 24 mod 17 = 7    | 24 mod 5 = 4    |
| 55    | 55 mod 17 = 4    | 55 mod 5 = 0    |
| 37    | 37 mod 17 = 3    | 37 mod 5 = 2    |

#### Inserção na tabela com hashing duplo:

Agora, inserimos os valores na tabela de tamanho 17, resolvendo colisões com a fórmula mencionada.

1. **51** → `h1(51) = 0` → Índice 0 (sem colisão).
2. **9** → `h1(9) = 9` → Índice 9 (sem colisão).
3. **13** → `h1(13) = 13` → Índice 13 (sem colisão).
4. **7** → `h1(7) = 7` → Índice 7 (sem colisão).
5. **24** → `h1(24) = 7` → Colisão no índice 7.
   - Usa `h2(24) = 4`. Tentativa 1: `novo_indice = (7 + 1 * 4) % 17 = 11`. Então, insere no índice 11.
6. **55** → `h1(55) = 4` → Índice 4 (sem colisão).
7. **37** → `h1(37) = 3` → Índice 3 (sem colisão).

#### Tabela final após inserção:

| Índice | Valor |
|--------|-------|
| 0      | 51    |
| 1      |       |
| 2      |       |
| 3      | 37    |
| 4      | 55    |
| 5      |       |
| 6      |       |
| 7      | 7     |
| 8      |       |
| 9      | 9     |
| 10     |       |
| 11     | 24    |
| 12     |       |
| 13     | 13    |
| 14     |       |
| 15     |       |
| 16     |       |