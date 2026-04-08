package EDNL.AVl2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearchTree {
    private No root;

    // Construtor
    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Integer key) {
        if (root == null) {
            root = new No(null, key); // Cria a raiz se a árvore estiver vazia
        } else {
            insertRecursive(root, key);
        }
    }
    
    private void insertRecursive(No current, Integer key) {
        Integer currentValue = (Integer) current.element();
    
        if (key < currentValue) { // Inserir no lado esquerdo
            if (current.getLeftChild() == null) {
                No newNode = new No(current, key); // Cria um novo nó
                current.setLeftChild(newNode); // Define como filho esquerdo
            } else {
                insertRecursive(current.getLeftChild(), key); // Continua no lado esquerdo
            }
        } else if (key > currentValue) { // Inserir no lado direito
            if (current.getRightChild() == null) {
                No newNode = new No(current, key); // Cria um novo nó
                current.setRightChild(newNode); // Define como filho direito
            } else {
                insertRecursive(current.getRightChild(), key); // Continua no lado direito
            }
        }
    }


    // Remover um valor da árvore
    public void remove(Integer value) {
        root = removeRecursive(root, value);
    }

    private No removeRecursive(No current, Integer value) {
        if (current == null) {
            return null;
        }
    
        Integer currentValue = (Integer) current.element();
    
        if (value < currentValue) { // Procurar no lado esquerdo
            No leftChild = current.getLeftChild();
            current.setLeftChild(removeRecursive(leftChild, value));
        } else if (value > currentValue) { // Procurar no lado direito
            No rightChild = current.getRightChild();
            current.setRightChild(removeRecursive(rightChild, value));
        } else { // Encontrou o nó a ser removido
            if (current.getLeftChild() == null && current.getRightChild() == null) {
                return null; // Nó folha
            } else if (current.getLeftChild() == null) {
                return current.getRightChild(); // Apenas filho direito
            } else if (current.getRightChild() == null) {
                return current.getLeftChild(); // Apenas filho esquerdo
            } else {
                // Dois filhos: substituir pelo menor valor do lado direito
                No rightChild = current.getRightChild();
                Integer minValue = findMinValue(rightChild);
                current.setElement(minValue);
                current.setRightChild(removeRecursive(rightChild, minValue));
            }
        }
        return current;
    }
    
    private Integer findMinValue(No node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return (Integer) node.element();
    }

    // Exibir a árvore
    public void display() {
        displayRecursive(root, 0);
    }

    private void displayRecursive(No node, int depth) {
        if (node == null) {
            return;
        }
    
        // Exibir o lado direito primeiro
        displayRecursive(node.getRightChild(), depth + 1);
        System.out.println(String.join("", Collections.nCopies(depth * 4, " ")) + node.element());
        // Exibir o lado esquerdo
        displayRecursive(node.getLeftChild(), depth + 1);
    }
    
    public No search(Integer key, No current) {
    if (current == null) { // Caso base: Nó não encontrado
         throw new NullPointerException("Encontramo nao man :/");
    }

    Integer currentValue = (Integer) current.element();

    if (key.equals(currentValue)) { // Caso base: Encontrou o nó
        return current;
    } else if (key < currentValue) { // Buscar no lado esquerdo
        return search(key, current.getLeftChild());
    } else { // Buscar no lado direito
        return search(key, current.getRightChild());
    }
}
 
    public No getRoot(){

        return root;

    }

    public void setRoot(No root){

        this.root = root;

    }

    public void mirror() {
        root = mirrorRecursive(root);
    }
    
    // Método auxiliar recursivo para espelhar a árvore
    private No mirrorRecursive(No node) {
        if (node == null) {
            return null;
        }
    
        // Inverte os filhos esquerdo e direito
        No left = mirrorRecursive(node.getLeftChild());
        No right = mirrorRecursive(node.getRightChild());
        
        node.setLeftChild(right);
        node.setRightChild(left);
    
        return node;
    }

}