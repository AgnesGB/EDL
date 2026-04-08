package EDNL.AVl2;

import java.util.Collections;

public class BinarySearchTree<T extends Comparable<? super T>> {
    protected No<T> root;
    protected No<T> ultimoPai;

    // Construtor
    public BinarySearchTree() {
        this.root = null;
        this.ultimoPai = null;
    }

    protected No<T> createNode(T key, No<T> pai) {
        return new No<>(pai, key);
    }

    public void insert(T key) {
        if (root == null) {
            root = createNode(key, null); // Cria a raiz se a árvore estiver vazia
        } else {
            insertRecursive(root, key);
        }
    }
    
    private void insertRecursive(No<T> current, T key) {
        T currentValue = current.element();
    
        if (key.compareTo(currentValue) < 0) { // Inserir no lado esquerdo
            if (current.getLeftChild() == null) {
                No<T> newNode = createNode(key, current); // Cria um novo nó
                current.setLeftChild(newNode); // Define como filho esquerdo
            } else {
                insertRecursive(current.getLeftChild(), key); // Continua no lado esquerdo
            }
        } else if (key.compareTo(currentValue) > 0) { // Inserir no lado direito
            if (current.getRightChild() == null) {
                No<T> newNode = createNode(key, current); // Cria um novo nó
                current.setRightChild(newNode); // Define como filho direito
            } else {
                insertRecursive(current.getRightChild(), key); // Continua no lado direito
            }
        }
    }


    // Remover um valor da árvore
    public void remove(T value) {
        root = removeRecursive(root, value);
        if (root != null) {
            root.setParent(null);
        }
    }

    public void delete(T value) {
        remove(value);
    }

    private No<T> removeRecursive(No<T> current, T value) {
        if (current == null) {
            return null;
        }
    
        T currentValue = current.element();
    
        if (value.compareTo(currentValue) < 0) { // Procurar no lado esquerdo
            No<T> leftChild = current.getLeftChild();
            No<T> newLeft = removeRecursive(leftChild, value);
            current.setLeftChild(newLeft);
            if (newLeft != null) {
                newLeft.setParent(current);
            }
        } else if (value.compareTo(currentValue) > 0) { // Procurar no lado direito
            No<T> rightChild = current.getRightChild();
            No<T> newRight = removeRecursive(rightChild, value);
            current.setRightChild(newRight);
            if (newRight != null) {
                newRight.setParent(current);
            }
        } else { // Encontrou o nó a ser removido
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null; // Nó folha
            } else if (current.getLeftChild() == null) {
                No<T> onlyRight = current.getRightChild();
                if (onlyRight != null) {
                    onlyRight.setParent(current.parent());
                }
                return onlyRight; // Apenas filho direito
            } else if (current.getRightChild() == null) {
                No<T> onlyLeft = current.getLeftChild();
                if (onlyLeft != null) {
                    onlyLeft.setParent(current.parent());
                }
                return onlyLeft; // Apenas filho esquerdo
            } else {
                // Dois filhos: substituir pelo menor valor do lado direito
                No<T> rightChild = current.getRightChild();
                T minValue = findMinValue(rightChild);
                current.setElement(minValue);
                No<T> newRight = removeRecursive(rightChild, minValue);
                current.setRightChild(newRight);
                if (newRight != null) {
                    newRight.setParent(current);
                }
            }
        }
        return current;
    }
    
    private T findMinValue(No<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node.element();
    }

    // Exibir a árvore
    public void display() {
        displayRecursive(root, 0);
    }

    private void displayRecursive(No<T> node, int depth) {
        if (node == null) {
            return;
        }
    
        // Exibir o lado direito primeiro
        displayRecursive(node.getRightChild(), depth + 1);
        System.out.println(String.join("", Collections.nCopies(depth * 4, " ")) + node.element());
        // Exibir o lado esquerdo
        displayRecursive(node.getLeftChild(), depth + 1);
    }
    
    public No<T> search(T key) {
        No<T> current = root;
        ultimoPai = null;

        while (current != null) {
            int cmp = key.compareTo(current.element());
            if (cmp == 0) {
                return current;
            }
            ultimoPai = current;
            current = (cmp < 0) ? current.getLeftChild() : current.getRightChild();
        }
        return null;
    }

    public No<T> search(T key, No<T> current) {
        if (current == null) {
            return null;
        }

        int cmp = key.compareTo(current.element());

        if (cmp == 0) {
            return current;
        } else if (cmp < 0) {
            return search(key, current.getLeftChild());
        }
        return search(key, current.getRightChild());
    }
 
    public No<T> getRoot(){

        return root;

    }

    public void setRoot(No<T> root){

        this.root = root;
        if (this.root != null) {
            this.root.setParent(null);
        }

    }

    public void mirror() {
        root = mirrorRecursive(root);
    }
    
    // Método auxiliar recursivo para espelhar a árvore
    private No<T> mirrorRecursive(No<T> node) {
        if (node == null) {
            return null;
        }
    
        // Inverte os filhos esquerdo e direito
        No<T> left = mirrorRecursive(node.getLeftChild());
        No<T> right = mirrorRecursive(node.getRightChild());
        
        node.setLeftChild(right);
        if (right != null) {
            right.setParent(node);
        }
        node.setRightChild(left);
        if (left != null) {
            left.setParent(node);
        }
    
        return node;
    }

}