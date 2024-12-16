import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreSimples {
    // Atributos da árvore
    private No raiz;
    private int tamanho;

    // Construtor
    public ArvoreSimples(Object o) {
        raiz = new No(null, o);
        tamanho = 1;
    }

    /** Retorna a raiz da árvore */
    public No root() {
        return raiz;
    }

    /** Retorna o No pai de um No */
    public No parent(No v) {
        return v.parent();
    }

    /** Retorna os filhos de um No */
    public Iterator<No> children(No v) {
        return v.children();
    }

    /** Testa se um No é interno */
    public boolean isInternal(No v) {
        return v.childrenNumber() > 0;
    }

    /** Testa se um No é externo */
    public boolean isExternal(No v) {
        return v.childrenNumber() == 0;
    }

    /** Testa se um No é a raiz */
    public boolean isRoot(No v) {
        return v == raiz;
    }

    /** Adiciona um filho a um No */
    public void addChild(No v, Object o) {
        No novo = new No(v, o);
        v.addChild(novo);
        tamanho++;
    }

    /** Remove um No */
	public Object remove(No v) {
		if (v == null) {
			throw new IllegalArgumentException("Nó inválido");
		}
	
		Object elementoRemovido = v.element();
	
		if (isRoot(v)) { // Caso especial: Remover a raiz
			if (isExternal(v)) { // Raiz sem filhos
				raiz = null; // Árvore ficará vazia
			} else { // Raiz com filhos
				raiz = reorganizarSubarvore(v); // Promove um filho e reorganiza a árvore
				raiz.pai = null; // Atualiza a raiz
			}
		} else { // Remoção de nós não raiz
			No pai = v.parent();
			pai.removeChild(v); // Remove o nó do pai
	
			if (!isExternal(v)) { // Se o nó tem filhos, reorganiza
				No novoSubarvore = reorganizarSubarvore(v);
				pai.addChild(novoSubarvore);
			}
		}
	
		tamanho--;
		return elementoRemovido;
	}
	
	private No reorganizarSubarvore(No no) {
		Iterator<No> filhos = no.children();
		if (!filhos.hasNext()) {
			return null; // Sem filhos, nada a reorganizar
		}
	
		// Promove o primeiro filho para substituir o nó removido
		No novoRaiz = (No) filhos.next();
	
		// Reanexa os outros filhos ao novo nó raiz
		while (filhos.hasNext()) {
			No outroFilho = (No) filhos.next();
			novoRaiz.addChild(outroFilho);
		}
	
		return novoRaiz;
	}
	

    /** Troca dois elementos de posição */
    public void swapElements(No v, No w) {
        Object temp = v.element();
        v.setElement(w.element());
        w.setElement(temp);
    }

    /** Retorna a profundidade de um No */
    public int depth(No v) {
        if (v == raiz)
            return 0;
        else
            return 1 + depth(v.parent());
    }

    /** Retorna a altura da árvore */
    public int height() {
        return height(raiz);
    }

    private int height(No v) {
        if (isExternal(v)) {
            return 0;
        }
        int maxHeight = 0;
        Iterator<No> children = v.children();
        while (children.hasNext()) {
            maxHeight = Math.max(maxHeight, height(children.next()));
        }
        return 1 + maxHeight;
    }

    /** Retorna o número de Nos da árvore */
    public int size() {
        return tamanho;
    }

    /** Retorna se a árvore está vazia */
    public boolean isEmpty() {
        return tamanho == 0;
    }

    /** Procura um No pela chave */
    public No search(Object chave, No root) {
        if (root.element().equals(chave)) {
            return root;
        }
        Iterator<No> children = root.children();
        while (children.hasNext()) {
            No found = search(chave, children.next());
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /** Substitui o elemento de um No */
    public Object replace(No v, Object o) {
        Object old = v.element();
        v.setElement(o);
        return old;
    }

	/** Imprime a árvore em formato hierárquico */
	public void printTree() {
    	printSubTree(raiz, 0);
	}

	private void printSubTree(No v, int depth) {
    	// Indentação para refletir a profundidade
    	for (int i = 0; i < depth; i++) {
        	System.out.print("  "); // Dois espaços para cada nível
    	}
    	System.out.println(v.element()); // Imprime o elemento do nó atual

    	// Recursivamente imprime os filhos
    	Iterator<No> children = v.children();
    	while (children.hasNext()) {
        	printSubTree(children.next(), depth + 1);
    	}
	}

    // Classe aninhada para armazenar o No
    public class No {
        private Object o;
        private No pai;
        private ArrayList<No> filhos = new ArrayList<>();

        public No(No pai, Object o) {
            this.pai = pai;
            this.o = o;
        }

        public Object element() {
            return o;
        }

        public No parent() {
            return pai;
        }

        public void setElement(Object o) {
            this.o = o;
        }

        public void addChild(No o) {
            filhos.add(o);
        }

        public void removeChild(No o) {
            filhos.remove(o);
        }

        public int childrenNumber() {
            return filhos.size();
        }

        public Iterator<No> children() {
            return filhos.iterator();
        }
    }
}
