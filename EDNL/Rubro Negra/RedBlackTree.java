import java.util.*;

public class RedBlackTree<T extends Comparable<? super T>> {

    private final RBNode<T> NIL = new RBNode<>(RBNode.Color.BLACK); // sentinela único
    private RBNode<T> root = NIL;

    public boolean isEmpty() { return root == NIL; }

    public boolean contains(T key) { return getNode(key) != NIL; }

    public void insert(T key) {
        // Inserção BST iterativa
        RBNode<T> y = NIL, x = root;
        while (x != NIL) {
            y = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return;           // ignorar duplicatas
            x = (cmp < 0) ? x.left : x.right;
        }
        RBNode<T> z = new RBNode<>(key, RBNode.Color.RED, NIL); // nó nasce RED
        z.parent = y;
        if (y == NIL) {
            root = z;
        } else if (key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        insertFixup(z);
    }

    public void remove(T key) {
        RBNode<T> z = getNode(key);
        if (z == NIL) return;

        RBNode<T> y = z;
        RBNode.Color yOriginalColor = y.color;
        RBNode<T> x;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);                 // sucessor
            yOriginalColor = y.color;
            x = y.right;                          // y nunca tem filho esquerdo
            if (y.parent == z) {
                x.parent = y;                    // ainda que x possa ser NIL
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;                   // preserva cor da posição
        }

        if (yOriginalColor == RBNode.Color.BLACK) {
            deleteFixup(x);
        }
    }


    private RBNode<T> getNode(T key) {
        RBNode<T> x = root;
        while (x != NIL) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            x = (cmp < 0) ? x.left : x.right;
        }
        return NIL;
    }

    private RBNode<T> minimum(RBNode<T> n) {
        RBNode<T> x = n;
        while (x.left != NIL) x = x.left;
        return x;
    }

    private void transplant(RBNode<T> u, RBNode<T> v) {
        if (u.parent == NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /* ===================== Rotações ===================== */

    private void rotateLeft(RBNode<T> x) {
        RBNode<T> y = x.right;
        x.right = y.left;
        y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(RBNode<T> x) {
        RBNode<T> y = x.left;
        x.left = y.right;
        y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }

    /* ===================== Fix-up da inserção ===================== */

    private void insertFixup(RBNode<T> z) {
        while (z.parent.isRed()) {
            RBNode<T> p = z.parent;
            RBNode<T> g = p.parent;

            if (p == g.left) {
                RBNode<T> u = g.right; // tio
                if (u.isRed()) {
                    // Caso 1: tio vermelho -> repintamento e sobe
                    p.color = RBNode.Color.BLACK;
                    u.color = RBNode.Color.BLACK;
                    g.color = RBNode.Color.RED;
                    z = g;
                } else {
                    if (z == p.right) {
                        // Caso 2: z é direito -> rotação no pai
                        z = p;
                        rotateLeft(z);
                        p = z.parent; g = p.parent;
                    }
                    // Caso 3: rotação no avô + repintamento
                    p.color = RBNode.Color.BLACK;
                    g.color = RBNode.Color.RED;
                    rotateRight(g);
                }
            } else {
                // Simétrico
                RBNode<T> u = g.left;
                if (u.isRed()) {
                    p.color = RBNode.Color.BLACK;
                    u.color = RBNode.Color.BLACK;
                    g.color = RBNode.Color.RED;
                    z = g;
                } else {
                    if (z == p.left) {
                        z = p;
                        rotateRight(z);
                        p = z.parent; g = p.parent;
                    }
                    p.color = RBNode.Color.BLACK;
                    g.color = RBNode.Color.RED;
                    rotateLeft(g);
                }
            }
        }
        root.color = RBNode.Color.BLACK; // raiz sempre preta
    }

    /* ===================== Fix-up da remoção (duplo-negro) ===================== */

    private void deleteFixup(RBNode<T> x) {
        while (x != root && x.isBlack()) {
            if (x == x.parent.left) {
                RBNode<T> w = x.parent.right; // irmão
                if (w.isRed()) {
                    // Caso 1
                    w.color = RBNode.Color.BLACK;
                    x.parent.color = RBNode.Color.RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if (w.left.isBlack() && w.right.isBlack()) {
                    // Caso 2
                    w.color = RBNode.Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.isBlack()) {
                        // Caso 3
                        w.left.color = RBNode.Color.BLACK;
                        w.color = RBNode.Color.RED;
                        rotateRight(w);
                        w = x.parent.right;
                    }
                    // Caso 4
                    w.color = x.parent.color;
                    x.parent.color = RBNode.Color.BLACK;
                    w.right.color = RBNode.Color.BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                // Simétrico
                RBNode<T> w = x.parent.left;
                if (w.isRed()) {
                    w.color = RBNode.Color.BLACK;
                    x.parent.color = RBNode.Color.RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if (w.left.isBlack() && w.right.isBlack()) {
                    w.color = RBNode.Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.isBlack()) {
                        w.right.color = RBNode.Color.BLACK;
                        w.color = RBNode.Color.RED;
                        rotateLeft(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = RBNode.Color.BLACK;
                    w.left.color = RBNode.Color.BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = RBNode.Color.BLACK;
    }

    public void printTree() {
        if (root == NIL) {
            System.out.println("(vazia)\n");
            return;
        }   

    // altura em nós (folha = 1)
        int h = height(root);

    // Largura base só para espaçamento proporcional à altura
        int maxWidth = (int) Math.pow(2, h) * 2;

    // BFS com placeholders de NIL para manter a malha de espaçamento
        List<RBNode<T>> level = new ArrayList<>();
        level.add(root);

        while (!level.isEmpty()) {
            List<RBNode<T>> next = new ArrayList<>();
            int between = Math.max(1, maxWidth / (level.size() + 1));

            // Linha 1: chaves
            printSpaces(between - 1);
            for (int i = 0; i < level.size(); i++) {
                RBNode<T> n = level.get(i);
                String s = (n == NIL) ? " " : String.valueOf(n.key);
                System.out.print(s);
                if (i < level.size() - 1) printSpaces(between);
                // empurra filhos (ou NIL) para manter estrutura
                next.add((n == NIL) ? NIL : n.left);
                next.add((n == NIL) ? NIL : n.right);
            }
            System.out.println();

        // Linha 2: cores
            printSpaces(between - 1);
            for (int i = 0; i < level.size(); i++) {
                RBNode<T> n = level.get(i);
                String s = (n == NIL) ? " " : "(" + (n.isRed() ? "R" : "B") + ")";
                System.out.print(s);
                if (i < level.size() - 1) printSpaces(between);
            }
            System.out.println();

        // Decide se continua: só desce se ainda houver algum nó real
            boolean allNil = true;
            for (RBNode<T> n : next) {
                if (n != NIL) { allNil = false; break; }
            }
            if (allNil) {
                System.out.println();
                return;
            }
            level = next;
        }
        System.out.println();
    }

// altura para impressão (NIL = 0, folha = 1)
    private int height(RBNode<T> n) {
        if (n == NIL) return 0;
        int lh = height(n.left);
        int rh = height(n.right);
        return Math.max(lh, rh) + 1;
    }

    private void printSpaces(int n) {
        for (int i = 0; i < n; i++) System.out.print(" ");
    }
}
