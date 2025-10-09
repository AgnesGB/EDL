import java.util.*;

public class AVLTree<T extends Comparable<? super T>> {

    private AVLNode<T> root;

    private AVLNode<T> ultimoPai; 

    public boolean isEmpty() { return root == null; }

    private AVLNode<T> buscar(T key) {
        AVLNode<T> curr = root;
        ultimoPai = null;

        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp == 0) {
                return curr; // achou
            }
            ultimoPai = curr; // guarda o pai antes de descer
            curr = (cmp < 0) ? curr.left : curr.right;
        }

        return null;
    }

    public void insert(T key) {
        if (root == null) { root = novoNo(key, null); return; }

        AVLNode<T> achado = buscar(key);
        if (achado != null) return; // já existe → ignora duplicata

        // se não achou, ultimoPai aponta pro nó onde deve ser inserido
        AVLNode<T> novo = novoNo(key, ultimoPai);
        if (key.compareTo(ultimoPai.key) < 0) {
            ultimoPai.left = novo;
        } else {
            ultimoPai.right = novo;
        }

        subirRebalanceando(ultimoPai);
    }

    public void remove(T key) {
        if (root == null) return;

        AVLNode<T> curr = buscar(key);
        if (curr == null) return; // chave não existe

        // caso 2 filhos: substitui pelo sucessor
        if (curr.left != null && curr.right != null) {
            AVLNode<T> suc = curr.right;
            while (suc.left != null) suc = suc.left;
            curr.key = suc.key;
            curr = suc; // remove o sucessor agora
        }

        AVLNode<T> child = (curr.left != null) ? curr.left : curr.right;
        AVLNode<T> parent = curr.pai;

        if (child != null) child.pai = parent;

        if (parent == null) {
            root = child; // removendo raiz
        } else if (parent.left == curr) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        subirRebalanceando(parent);
    }

    /* =================== SUPORTE (altura, BF, update) =================== */

    private int h(AVLNode<T> n) { return (n == null) ? 0 : n.height; }

    private int bf(AVLNode<T> n) { return (n == null) ? 0 : h(n.left) - h(n.right); }

    private void update(AVLNode<T> n) {
        n.height = Math.max(h(n.left), h(n.right)) + 1;
        n.fatorBalanceamento = bf(n);
    }

    private AVLNode<T> novoNo(T key, AVLNode<T> pai) {
        AVLNode<T> n = new AVLNode<>(key);
        n.pai = pai;
        // height e fatorBalanceamento já devem começar como 1 e 0 no construtor;
        n.height = 1;
        n.fatorBalanceamento = 0;
        return n;
    }

    private AVLNode<T> rotateRight(AVLNode<T> z) {
        AVLNode<T> y = z.left;
        AVLNode<T> T3 = (y != null) ? y.right : null;

        // rotação
        y.right = z;
        y.pai = z.pai;

        z.left = T3;
        if (T3 != null) T3.pai = z;

        z.pai = y;

        // reata no pai original
        if (y.pai == null) root = y;
        else if (y.pai.left == z) y.pai.left = y;
        else y.pai.right = y;

        // atualiza alturas/BF (filho antes do pai)
        update(z);
        update(y);
        return y;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> z) {
        AVLNode<T> y = z.right;
        AVLNode<T> T2 = (y != null) ? y.left : null;

        // rotação
        y.left = z;
        y.pai = z.pai;

        z.right = T2;
        if (T2 != null) T2.pai = z;

        z.pai = y;

        // reata no pai original
        if (y.pai == null) root = y;
        else if (y.pai.left == z) y.pai.left = y;
        else y.pai.right = y;

        // atualiza alturas/BF (filho antes do pai)
        update(z);
        update(y);
        return y;
    }

    /** Reequilibra um único nó z e retorna a nova raiz da subárvore (já ligada ao pai). */
    private AVLNode<T> rebalance(AVLNode<T> z) {
        update(z);
        int balance = z.fatorBalanceamento;

        if (balance > 1) { // pesado à esquerda
            if (bf(z.left) < 0) rotateLeft(z.left); // caso LR
            return rotateRight(z);                  // LL
        }
        if (balance < -1) { // pesado à direita
            if (bf(z.right) > 0) rotateRight(z.right); // RL
            return rotateLeft(z);                      // RR
        }
        return z;
    }

    /** Sobe do nó "start" até a raiz, atualizando e reequilibrando. */
    private void subirRebalanceando(AVLNode<T> start) {
        AVLNode<T> n = start;
        while (n != null) {
            AVLNode<T> paiAntigo = n.pai;
            AVLNode<T> novoTopo = rebalance(n);
            // se houve rotação, novoTopo pode não ser "n"; já está religado no pai dentro das rotações
            n = paiAntigo;
        }
    }


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
                    System.out.print("(" + n.fatorBalanceamento + ")");
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