package EDNL.AVl2;

import java.util.*;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    public AVLTree() {
        super();
    }

    @Override
    public AVLNode<T> getRoot(){
        return cast(this.root);
    }

    @Override
    protected No<T> createNode(T key, No<T> pai) {
        return new AVLNode<>(key, pai);
    }

    @Override
    public AVLNode<T> search(T data) {
        AVLNode<T> current = cast(root);
        ultimoPai = null; // Resetar o último pai antes de iniciar a busca

        while (current != null) {
            int cmp = data.compareTo(current.element());
            if (cmp == 0) {
                return current;
            } 
            ultimoPai = current; // Atualizar o último pai antes de descer na árvore
            current = (cmp < 0) ? cast(current.getLeftChild()) : cast(current.getRightChild());
        }
        return null;
    }

    @Override
    public void insert(T data) {
        if (root == null) {
            root = new AVLNode<>(data, null);
            return;
        } 

        AVLNode<T> achado = search(data);
        if (achado != null) {
            return; // O valor já existe na árvore, não insere duplicatas
        }

        AVLNode<T> pai = cast(ultimoPai);
        AVLNode<T> novoNo = novoNo(data, pai);
        if (data.compareTo(pai.element()) < 0) {
            pai.setLeftChild(novoNo);
        } else {
            pai.setRightChild(novoNo);
        }

        balancear(pai);
    }

    @Override
    public void delete(T data) {
        AVLNode<T> nodeToDelete = search(data);
        if (nodeToDelete == null) return; // Valor não encontrado, nada a deletar

        AVLNode<T> pai = cast(nodeToDelete.parent());

        if (nodeToDelete.getLeftChild() == null || nodeToDelete.getRightChild() == null) {
            // Caso 1: Nó tem no máximo um filho
            AVLNode<T> filho = (nodeToDelete.getLeftChild() != null)
                ? cast(nodeToDelete.getLeftChild())
                : cast(nodeToDelete.getRightChild());

            if (pai == null) {
                root = filho; // Deletando a raiz
                if (filho != null) {
                    filho.setParent(null);
                }
            } else if (pai.getLeftChild() == nodeToDelete) {
                pai.setLeftChild(filho);
            } else {
                pai.setRightChild(filho);
            }

            if (filho != null) filho.setParent(pai);
        } else {
            // Caso 2: Nó tem dois filhos
            AVLNode<T> sucessor = cast(nodeToDelete.getRightChild());
            while (sucessor.getLeftChild() != null) {
                sucessor = cast(sucessor.getLeftChild());
            }
            T tempData = sucessor.element(); // Guardar o valor do sucessor
            delete(sucessor.element()); // Deletar o sucessor, que tem no máximo um filho
            nodeToDelete.setElement(tempData); // Substituir o valor do nó a ser deletado pelo do sucessor
        }

        balancear(pai);
    }


    // =========================== MÉTODOS DE BALANCEAMENTO ===========================
    private void update(AVLNode<T> node) {
        node.height = Math.max(h(cast(node.getLeftChild())), h(cast(node.getRightChild()))) + 1;
        node.balanceFactor = bf(node);
    }

    private int h(AVLNode<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int bf(AVLNode<T> n) {
        return (n == null)
            ? 0
            : h(cast(n.getLeftChild())) - h(cast(n.getRightChild()));
    }

    private AVLNode<T> novoNo(T data, AVLNode<T> pai) {
        return new AVLNode<>(data, pai);
    }

    private AVLNode<T> rotacaoEsquerda(AVLNode<T> x) {
        AVLNode<T> y = cast(x.getRightChild());
        AVLNode<T> T2 = (y != null) ? cast(y.getLeftChild()) : null;

        // rotação
        y.setLeftChild(x);
        y.setParent(x.parent());

        x.setRightChild(T2);
        if (T2 != null) T2.setParent(x);

        x.setParent(y);

        // Reata no pai original
        if (y.parent() == null) {
            root = y;
        } else if (y.parent().getLeftChild() == x) {
            y.parent().setLeftChild(y);
        } else {
            y.parent().setRightChild(y);
        }

        // Atualiza alturas
        update(x);
        update(y);
        return y;
    }

    private AVLNode<T> rotacaoDireita(AVLNode<T> z) {
        AVLNode<T> y = cast(z.getLeftChild());
        AVLNode<T> T3 = (y != null) ? cast(y.getRightChild()) : null;

        // Rotação
        y.setRightChild(z);
        y.setParent(z.parent());

        z.setLeftChild(T3);
        if (T3 != null) T3.setParent(z);

        z.setParent(y);

        // Reata no pai original
        if (y.parent() == null) {
            root = y;
        } else if (y.parent().getLeftChild() == z) {
            y.parent().setLeftChild(y);
        } else {
            y.parent().setRightChild(y);
        }

        // Atualiza alturas
        update(z);
        update(y);
        return y;
    }

    private AVLNode<T> rebalance(AVLNode<T> reb) {
        update(reb);
        int balance = reb.balanceFactor;

        if (balance > 1) { // pesado à esquerda
            if (bf(cast(reb.getLeftChild())) < 0) {
                rotacaoEsquerda(cast(reb.getLeftChild())); // caso LR
            }
            return rotacaoDireita(reb);                  // LL
        }
        if (balance < -1) { // pesado à direita
            if (bf(cast(reb.getRightChild())) > 0) {
                rotacaoDireita(cast(reb.getRightChild())); // RL
            }
            return rotacaoEsquerda(reb);                      // RR
        }
        return reb;
    }

    private void balancear(AVLNode<T> node) {
        AVLNode<T> n = node;
        while (n != null) {
            AVLNode<T> paiAntigo = cast(n.parent());
            rebalance(n);
            n = paiAntigo;
        }
    }
    

    // =========================== MÉTODOS DE IMPRESSÃO ===========================

    public void printTree() {
        if (root == null) { 
            System.out.println("(árvore vazia)"); 
            return; 
        }

        int altura = cast(root).height;
        List<AVLNode<T>> nivel = new ArrayList<>();
        nivel.add(cast(root));

        for (int depth = 1; depth <= altura; depth++) {
            int gaps = (int) Math.pow(2, altura - depth) - 1;
            int between = (int) Math.pow(2, altura - depth + 1) - 1;

            printSpaces(gaps);
            List<AVLNode<T>> prox = new ArrayList<>();
            for (int i = 0; i < nivel.size(); i++) {
                AVLNode<T> n = nivel.get(i);
                if (n == null) {
                    System.out.print("  "); // espaço vazio
                    prox.add(null); prox.add(null);
                } else {
                    System.out.print(n.element() + "(" + n.balanceFactor + ")");
                    prox.add(cast(n.getLeftChild()));
                    prox.add(cast(n.getRightChild()));
                }
                if (i < nivel.size() - 1) printSpaces(between);
            }
            System.out.println();

            nivel = prox;
        }
        System.out.println();
    }

    private void printSpaces(int n) {
        for (int i = 0; i < n; i++) System.out.print("  ");
    }

    private AVLNode<T> cast(No<T> node) {
        return (AVLNode<T>) node;
    }


}