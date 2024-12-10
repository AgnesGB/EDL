import java.util.ArrayList;
import java.util.Iterator;

public class BinarySearchTree {
    private No root;

    // Construtor
    public BinarySearchTree() {
        this.root = null;
    }

    // Inserir um valor na árvore
    public void insert(Integer value) {
        if (root == null) {
            root = new No(null, value);
        } else {
            insertRecursive(root, value);
        }
    }

    private void insertRecursive(No current, Integer value) {
        Integer currentValue = (Integer) current.element();
    
        if (value < currentValue) { // Inserir no lado esquerdo
            No leftChild = current.getLeftChild();
            if (leftChild != null) {
                insertRecursive(leftChild, value);
            } else {
                current.setLeftChild(new No(current, value));
            }
        } else if (value > currentValue) { // Inserir no lado direito
            No rightChild = current.getRightChild();
            if (rightChild != null) {
                insertRecursive(rightChild, value);
            } else {
                current.setRightChild(new No(current, value));
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
        System.out.println(" ".repeat(depth * 4) + node.element());
        // Exibir o lado esquerdo
        displayRecursive(node.getLeftChild(), depth + 1);
    }
    
    public No search(Integer chave){

        return searchRecurssive(root, chave);
    }

    private No searchRecurssive(No current, Integer value){
        if (current.o == value){
            return current;
        }
        if (current == null) {
            return null;
        }
    
        Integer currentValue = (Integer) current.element();
        
        
        if (value < currentValue) { // Procurar no lado esquerdo
            No leftChild = current.getLeftChild();
            current.setLeftChild(searchRecurssive(leftChild, value));
            
        } else if (value > currentValue) { // Procurar no lado direito
            No rightChild = current.getRightChild();
            current.setRightChild(searchRecurssive(rightChild, value));
            }
        return current;
    }
    
    public class No 
	{
		private Object o;
		private No pai;
		private ArrayList filhos = new ArrayList();
		public No(No pai, Object o)
		{
			this.pai = pai;
			this.o = o;
		}
		public Object element()
		{
			return o;
		}
		public No parent()
		{
			return pai;
		}
		public void setElement(Object o)
		{
			this.o = o;
		}
		public void addChild(No o)
		{
			filhos.add(o);
		}
		public void removeChild(No o)
		{
			filhos.remove(o);
		}
		public int childrenNumber()
		{
			return filhos.size();
		}
		public Iterator children()
		{
			return filhos.iterator();
		}
        public No getLeftChild() {
            return filhos.size() > 0 ? (No) filhos.get(0) : null;
        }
        
        public No getRightChild() {
            return filhos.size() > 1 ? (No) filhos.get(1) : null;
        }
        
        public void setLeftChild(No child) {
            if (filhos.size() > 0) {
                filhos.set(0, child);
            } else {
                filhos.add(child);
            }
        }
        
        public void setRightChild(No child) {
            if (filhos.size() > 1) {
                filhos.set(1, child);
            } else if (filhos.size() == 1) {
                filhos.add(child);
            }
        }
	}
}