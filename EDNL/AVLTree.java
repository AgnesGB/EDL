// AVLTree.java
import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<? super T>> {

    private AVLNode<T> root;


    public boolean isEmpty() { return root == null; }

    public void insert(T key) {
        root = insertRec(root, key, null);
    }

    public void remove(T key) {
        root = removeRec(root, key);
    }

    public boolean contains(T key) {
        AVLNode<T> n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp == 0) return true;
            n = (cmp < 0) ? n.left : n.right;
        }
        return false;
    }

    public List<T> inorder() {
        List<T> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }

    public List<T> preorder() {
        List<T> out = new ArrayList<>();
        preorderRec(root, out);
        return out;
    }

    public List<T> postorder() {
        List<T> out = new ArrayList<>();
        postorderRec(root, out);
        return out;
    }

    /* =================== RECURSIVOS =================== */

    private AVLNode<T> insertRec(AVLNode<T> node, T key, AVLNode<T> parent) {
        if (node == null) {
            AVLNode<T> novo = new AVLNode<>(key);
            novo.pai = parent;
            return novo;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertRec(node.left, key, node);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, key, node);
        } else {
            return node; 
        }

        update(node);
        return rebalance(node);
    }

    private AVLNode<T> removeRec(AVLNode<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeRec(node.left, key);
            if (node.left != null) node.left.pai = node;
        } else if (cmp > 0) {
            node.right = removeRec(node.right, key);
            if (node.right != null) node.right.pai = node;
        } else {
            // achou o nó a remover
            if (node.left == null || node.right == null) {
                AVLNode<T> temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    // sem filhos
                    node = null;
                } else {
                    // 1 filho
                    temp.pai = node.pai;
                    node = temp;
                }
            } else {
                // 2 filhos: usa sucessor
                AVLNode<T> suc = minNode(node.right);
                node.key = suc.key;
                node.right = removeRec(node.right, suc.key);
                if (node.right != null) node.right.pai = node;
            }
        }

        if (node == null) return null;

        update(node);
        return rebalance(node);
    }

    /* =================== UTILITÁRIOS =================== */

    private void inorderRec(AVLNode<T> n, List<T> out) {
        if (n == null) return;
        inorderRec(n.left, out);
        out.add(n.key);
        inorderRec(n.right, out);
    }

    private void preorderRec(AVLNode<T> n, List<T> out) {
        if (n == null) return;
        out.add(n.key);
        preorderRec(n.left, out);
        preorderRec(n.right, out);
    }

    private void postorderRec(AVLNode<T> n, List<T> out) {
        if (n == null) return;
        postorderRec(n.left, out);
        postorderRec(n.right, out);
        out.add(n.key);
    }

    private AVLNode<T> minNode(AVLNode<T> n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private int h(AVLNode<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int bf(AVLNode<T> n) {
        return (n == null) ? 0 : h(n.left) - h(n.right);
    }

    private void update(AVLNode<T> n) {
        n.height = Math.max(h(n.left), h(n.right)) + 1;
        n.fatorBalanceamento = bf(n);
    }

    private AVLNode<T> rebalance(AVLNode<T> z) {
        int balance = z.fatorBalanceamento;

        // Casos de rotações!!
        // Simples a esquerda
        if (balance > 1 && bf(z.left) >= 0) {
            return rotateRight(z);
        }
        // Dupla a direita
        if (balance > 1 && bf(z.left) < 0) {
            z.left = rotateLeft(z.left);
            if (z.left != null) z.left.pai = z;
            return rotateRight(z);
        }
        // Simples a Direita
        if (balance < -1 && bf(z.right) <= 0) {
            return rotateLeft(z);
        }
        // Dupla a esquerda
        if (balance < -1 && bf(z.right) > 0) {
            z.right = rotateRight(z.right);
            if (z.right != null) z.right.pai = z;
            return rotateLeft(z);
        }

        return z;
    }

    /* =================== ROTAÇÕES =================== */

    private AVLNode<T> rotateRight(AVLNode<T> z) {
        AVLNode<T> y = z.left;
        AVLNode<T> T3 = (y != null) ? y.right : null;

        // Rotação
        y.right = z;
        y.pai = z.pai;
        z.pai = y;
        z.left = T3;
        if (T3 != null) T3.pai = z;

        // Atualiza alturas/BF
        update(z);
        update(y);

        // Atualiza raiz se necessário
        if (y.pai == null) root = y;
        return y;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> z) {
        AVLNode<T> y = z.right;
        AVLNode<T> T2 = (y != null) ? y.left : null;

        // Rotação
        y.left = z;
        y.pai = z.pai;
        z.pai = y;
        z.right = T2;
        if (T2 != null) T2.pai = z;

        // Atualiza alturas/BF
        update(z);
        update(y);

        // Atualiza raiz se necessário
        if (y.pai == null) root = y;
        return y;
    }

    // Imprime a árvore em várias linhas (para debug rápido)
    public void printTree() {
    if (root == null) {
        System.out.println("(árvore vazia)");
        return;
    }
    int altura = root.height;
    int largura = (int) Math.pow(2, altura) * 2; // define largura máxima

    java.util.Queue<AVLNode<T>> fila = new java.util.LinkedList<>();
    fila.add(root);

    int nivel = 0;
    while (!fila.isEmpty() && nivel < altura) {
        int qtd = fila.size();
        int espacos = (int) Math.pow(2, altura - nivel);

        // imprime nós do nível
        for (int i = 0; i < qtd; i++) {
            AVLNode<T> atual = fila.poll();
            printEspacos(espacos);

            if (atual != null) {
                System.out.print(atual.key);
                fila.add(atual.left);
                fila.add(atual.right);
            } else {
                System.out.print(" ");
                fila.add(null);
                fila.add(null);
            }

            printEspacos(espacos);
        }
        System.out.println();
        nivel++;
    }
}

private void printEspacos(int qtd) {
    for (int i = 0; i < qtd; i++) System.out.print(" ");
}
}
