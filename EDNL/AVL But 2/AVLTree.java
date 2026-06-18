import java.util.*;

public class AVLTree<T extends Comparable<? super T>> {
    private AVLNode<T> root;

    private AVLNode<T> ultimoPai;

    public AVLTree() {
        this.root = null;
        this.ultimoPai = null;
    }

    public AVLNode<T> search(T data) {
        AVLNode<T> current = root;
        ultimoPai = null; // Resetar o último pai antes de iniciar a busca

        while (current !=null) {
            int cmp = data.compareTo(current.data);
            if (cmp == 0) {
                return current;
            } 
            ultimoPai = current; // Atualizar o último pai antes de descer na árvore
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public void insert(T data) {
        if (root == null) {
            root = new AVLNode<>(data);
            return;
        } 

        AVLNode<T> achado = search(data);
        if (achado != null) {
            return; // O valor já existe na árvore, não insere duplicatas
        }

        AVLNode<T> novoNo = novoNo(data, ultimoPai);
        if (data.compareTo(ultimoPai.data) < 0) {
            ultimoPai.left = novoNo;
        } else {
            ultimoPai.right = novoNo;
        }

        balancear(ultimoPai);
    }

    public void delete(T data) {
        AVLNode<T> nodeToDelete = search(data);
        if (nodeToDelete == null) return; // Valor não encontrado, nada a deletar

        AVLNode<T> pai = nodeToDelete.pai;

        if (nodeToDelete.left == null || nodeToDelete.right == null) {
            // Caso 1: Nó tem no máximo um filho
            AVLNode<T> filho = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;

            if (pai == null) {
                root = filho; // Deletando a raiz
            } else if (pai.left == nodeToDelete) {
                pai.left = filho;
            } else {
                pai.right = filho;
            }

            if (filho != null) filho.pai = pai;
        } else {
            // Caso 2: Nó tem dois filhos
            AVLNode<T> sucessor = nodeToDelete.right;
            while (sucessor.left != null) {
                sucessor = sucessor.left;
            }
            T tempData = sucessor.data; // Guardar o valor do sucessor
            delete(sucessor.data); // Deletar o sucessor, que tem no máximo um filho
            nodeToDelete.data = tempData; // Substituir o valor do nó a ser deletado pelo do sucessor
        }

        balancear(pai);
    }


    // =========================== MÉTODOS DE BALANCEAMENTO ===========================
    private void update(AVLNode<T> node) {
        node.height = Math.max(h(node.left), h(node.right)) + 1;
        node.balanceFactor = bf(node);
    }

    private int h(AVLNode<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int bf(AVLNode<T> n) {
        return (n == null) ? 0 : h(n.left) - h(n.right);
    }

    private AVLNode<T> novoNo(T data, AVLNode<T> pai) {
        AVLNode<T> novo = new AVLNode<>(data);
        novo.pai = pai;
        return novo;
    }

    private AVLNode<T> rotacaoEsquerda(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = (y != null) ? y.left : null;

        // rotação
        y.left = x;
        y.pai = x.pai;

        x.right = T2;
        if (T2 != null) T2.pai = x;

        x.pai = y;

        // Reata no pai original
        if (y.pai == null) root = y;
        else if (y.pai.left == x) y.pai.left = y;
        else y.pai.right = y;

        // Atualiza alturas
        update(x);
        update(y);
        return y;
    }

    private AVLNode<T> rotacaoDireita(AVLNode<T> z) {
        AVLNode<T> y = z.left;
        AVLNode<T> T3 = (y != null) ? y.right : null;

        // Rotação
        y.right = z;
        y.pai = z.pai;

        z.left = T3;
        if (T3 != null) T3.pai = z;

        z.pai = y;

        // Reata no pai original
        if (y.pai == null) root = y;
        else if (y.pai.left == z) y.pai.left = y;
        else y.pai.right = y;

        // Atualiza alturas
        update(z);
        update(y);
        return y;
    }

    private AVLNode<T> rebalance(AVLNode<T> reb) {
        update(reb);
        int balance = reb.balanceFactor;

        if (balance > 1) { // pesado à esquerda
            if (bf(reb.left) < 0) rotacaoEsquerda(reb.left); // caso LR
            return rotacaoDireita(reb);                  // LL
        }
        if (balance < -1) { // pesado à direita
            if (bf(reb.right) > 0) rotacaoDireita(reb.right); // RL
            return rotacaoEsquerda(reb);                      // RR
        }
        return reb;
    }

    private void balancear(AVLNode<T> node) {
        AVLNode<T> n = node;
        while (n != null) {
            AVLNode<T> paiAntigo = n.pai;
            AVLNode<T> novoTopo = rebalance(n);
            // se houve rotação, novoTopo pode não ser "n"; já está religado no pai dentro das rotações
            n = paiAntigo;
        }
    }
    

    // =========================== MÉTODOS DE IMPRESSÃO ===========================

    public void printTree() {
        if (root == null) { 
            System.out.println("(árvore vazia)"); 
            return; 
        }

        int altura = root.height;
        List<AVLNode<T>> nivel = new ArrayList<>();
        nivel.add(root);

        for (int depth = 1; depth <= altura; depth++) {
            int gaps = (int) Math.pow(2, altura - depth) - 1;
            int between = (int) Math.pow(2, altura - depth + 1) - 1;

            // ===== PRIMEIRA LINHA: chaves =====
            printSpaces(gaps);
            List<AVLNode<T>> prox = new ArrayList<>();
            for (int i = 0; i < nivel.size(); i++) {
                AVLNode<T> n = nivel.get(i);
                if (n == null) {
                    System.out.print("  "); // espaço vazio
                    prox.add(null); prox.add(null);
                } else {
                    System.out.print(n.key);
                    prox.add(n.left); prox.add(n.right);
                }
                if (i < nivel.size() - 1) printSpaces(between);
            }
            System.out.println();

            // ===== SEGUNDA LINHA: fatores de balanceamento =====
            printSpaces(gaps);
            for (int i = 0; i < nivel.size(); i++) {
                AVLNode<T> n = nivel.get(i);
                if (n == null) {
                    System.out.print("  ");
                } else {
                    System.out.print("(" + n.balanceFactor + ")");
                }
                if (i < nivel.size() - 1) printSpaces(between);
            }
            System.out.println();

            nivel = prox;
        }
        System.out.println();
    }

    private void printSpaces(int n) {
        for (int i = 0; i < n; i++) System.out.print(" ");
    }



}