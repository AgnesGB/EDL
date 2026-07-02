3. Enquanto a fila não estiver vazia:
   a. Extrai o vértice u com menor dist[u]
   b. Para cada vizinho v de u:
      - novaDist = dist[u] + peso(u, v)
      - Se novaDist < dist[v]:
          dist[v]    = novaDist
          anterior[v] = u
          Atualiza v na fila de prioridade
4. Ao final, dist[v] contém o menor custo de origem até v
   e anterior[] permite reconstruir o caminho
```

### Propriedade fundamental — Relaxamento

A operação central é o **relaxamento**: se encontramos um caminho mais curto até `v` passando por `u`, atualizamos a estimativa:

```
se dist[u] + peso(u,v) < dist[v]:
    dist[v] = dist[u] + peso(u,v)
```

Dijkstra garante que, ao extrair um vértice da fila, sua distância já é a **definitivamente mínima** — isso só vale porque todos os pesos são não-negativos.

### Complexidade

| Estrutura | Tempo | Espaço |
|---|---|---|
| Fila de prioridade (min-heap) | O((V + E) log V) | O(V) |
| Matriz de adjacência sem heap | O(V²) | O(V²) |

### Limitações

- **Não funciona com pesos negativos** (use Bellman-Ford nesses casos)
- Explora **todos os vértices alcançáveis**, mesmo quando o interesse é apenas um destino específico

---

## Algoritmo A\* (A-estrela)

### O que resolve

Dado um grafo com pesos não-negativos, um vértice de **origem** e um vértice de **destino**, o A\* encontra o **caminho mínimo entre esse par específico**, usando uma **heurística** para guiar a busca e expandir menos vértices que o Dijkstra.

### Ideia central

O A\* combina duas informações em cada vértice `n`:

```
f(n) = g(n) + h(n)
```

| Componente | Significado |
|---|---|
| `g(n)` | Custo real acumulado da origem até `n` |
| `h(n)` | Estimativa heurística do custo de `n` até o destino |
| `f(n)` | Estimativa do custo total do caminho passando por `n` |

O algoritmo sempre expande o vértice com menor `f(n)`, priorizando os que parecem estar mais próximos do destino.

### Passos do algoritmo

```
1. Inicializa g[origem] = 0, f[origem] = h(origem, destino)
2. Insere origem no conjunto aberto (open set / min-heap por f)
3. Enquanto o open set não estiver vazio:
   a. Extrai o vértice u com menor f[u]
   b. Se u == destino: reconstruir caminho e retornar
   c. Move u para o conjunto fechado (já processado)
   d. Para cada vizinho v de u não fechado:
      - tentativaG = g[u] + peso(u, v)
      - Se tentativaG < g[v]:
          g[v]       = tentativaG
          f[v]       = g[v] + h(v, destino)
          anterior[v] = u
          Insere/atualiza v no open set
4. Se o open set esvaziar sem encontrar destino: inalcançável
```

### A heurística — regras obrigatórias

Para que o A\* encontre o caminho **ótimo**, a heurística `h` deve ser:

**Admissível**: nunca superestima o custo real
```
h(n) ≤ custo_real(n, destino)    para todo n
```

**Consistente** (monotônica): satisfaz a desigualdade triangular
```
h(n) ≤ custo(n, vizinho) + h(vizinho)    para todo vizinho
```

Uma heurística consistente implica admissibilidade. Exemplos comuns:

| Problema | Heurística |
|---|---|
| Mapa geográfico | Distância em linha reta (Euclidiana) |
| Grade 4-direcional | Distância Manhattan |
| Grade 8-direcional | Distância Chebyshev |
| Sem informação | h = 0 (equivale ao Dijkstra) |

### Complexidade

| Caso | Tempo |
|---|---|
| Pior caso (h=0, sem informação) | O((V + E) log V) — igual ao Dijkstra |
| Heurística perfeita h=h* | O(V) — expande apenas o caminho ótimo |
| Caso geral | Depende da qualidade de h |

---

## Comparação: Dijkstra vs A\*

| Critério | Dijkstra | A\* |
|---|---|---|
| **Objetivo** | Menor custo da origem até **todos** os vértices | Menor custo da origem até **um destino** |
| **Heurística** | Não usa | Sim — guia a busca |
| **Ordem de expansão** | Por `g(n)` — custo real | Por `f(n) = g(n) + h(n)` |
| **Completude** | Sim (grafo finito, pesos ≥ 0) | Sim (heurística admissível) |
| **Otimalidade** | Sim (pesos ≥ 0) | Sim (heurística admissível) |
| **Pesos negativos** | Não | Não |
| **Vértices expandidos** | Todos os alcançáveis | Potencialmente muito menos |
| **Quando usar** | Precisa do caminho até múltiplos destinos | Precisa do caminho até um destino específico |
| **Informação extra** | Nenhuma | Precisa de heurística adequada ao problema |

### Visualização do comportamento

```
Grafo:   Natal → Joao Pessoa → Recife
              ↘ Mossoro → Fortaleza

Dijkstra: expande Natal, Joao Pessoa, Mossoro, Caico, Recife, Campina, Fortaleza, Patos
          (todos os vértices, sem saber qual é o destino)

A* (→ Fortaleza): expande Natal, Mossoro, Fortaleza
          (guiado pela heurística, ignora os nós "errados")
```

### Resultado do exemplo executado

```
Comparação: Natal → Fortaleza

Dijkstra  | custo: 551.0 | caminho: [Natal, Mossoro, Fortaleza]
           | expandiu TODOS os 8 vértices do grafo

A*        | custo: 551.0 | nós expandidos: 3 | caminho: [Natal, Mossoro, Fortaleza]
           | expandiu apenas 3 vértices
```

Ambos encontram o **mesmo custo ótimo** (551 km), mas o A\* chega lá expandindo muito menos vértices — especialmente vantajoso em grafos grandes.

### Relação entre os dois

O A\* com heurística `h(n) = 0` para todo `n` se comporta exatamente como o Dijkstra.
O Dijkstra é, portanto, um **caso particular do A\*** sem informação heurística.

```
A*(h = 0)  ≡  Dijkstra
```

### Quando escolher cada um

```
┌─────────────────────────────────────────────────────────┐
│ Precisa do menor caminho até UM destino específico?     │
│   Tem uma heurística admissível disponível?             │
│     → USE A*                                            │
│                                                         │
│ Precisa do menor caminho até VÁRIOS/TODOS os vértices?  │
│   Ou não tem heurística adequada?                       │
│     → USE DIJKSTRA                                      │
└─────────────────────────────────────────────────────────┘
```

---

## Referências

- GOODRICH, M. T.; TAMASSIA, R. *Data Structures and Algorithms in Java*. Wiley, 2014.
- CORMEN, T. H. et al. *Introduction to Algorithms*. 3. ed. MIT Press, 2009.
- HART, P. E.; NILSSON, N. J.; RAPHAEL, B. A Formal Basis for the Heuristic Determination of Minimum Cost Paths. *IEEE Transactions on Systems Science and Cybernetics*, 1968.