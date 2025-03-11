1) is heap. usando minheap.
contar os numeros da arvore.

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