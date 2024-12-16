public class testArvore {
    public static void main(String[] args) {
        ArvoreSimples arvore = new ArvoreSimples("Raiz");

        // Adicionando alguns nós
        ArvoreSimples.No raiz = arvore.root();
        arvore.addChild(raiz, "Filho 1");
        arvore.addChild(raiz, "Filho 2");
        ArvoreSimples.No filho1 = arvore.search("Filho 1", raiz);
        arvore.addChild(filho1, "Neto 1");
        arvore.addChild(filho1, "Neto 2");
        ArvoreSimples.No filho2 = arvore.search("Filho 2", raiz);
        arvore.addChild(filho2, "Neto 3");

        // Imprimindo a árvore
        arvore.printTree();
    }
}
