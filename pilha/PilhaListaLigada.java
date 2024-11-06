public class PilhaListaLigada implements IPilha{

private no topo;
private int tamanho;
  
  public PilhaListaLigada(){
    this.topo = null;
    this.tamanho = 0;
  }
  @Override
  public void push(Object o){
    no NovoNo = new no(o);
    NovoNo.proximo = topo;
    topo = NovoNo;
    tamanho++;
  }

  @Override
  public Object pop(){
    if(isEmpty()){
      throw new PilhaVaziaExcecao("Pilha vazia");
    }
    Object r = topo.getValor();
    topo = topo.proximo;
    tamanho--;
    return r;
    
  } 

  @Override
  public Object top(){
    if(isEmpty()){
      throw new PilhaVaziaExcecao("Pilha vazia");
    }
    return topo;
  }
  @Override
  public boolean isEmpty(){

    return topo == null;
  }

  @Override
    public int size(){
      return tamanho;
    }

  public void exibirPilha() {
    if (isEmpty()) {
        System.out.println("A pilha está vazia.");
    } else {
        System.out.println("Elementos na pilha:");
        no atual = topo;
        while (atual != null) {
            System.out.println(atual.valor);
            atual = atual.proximo;
        }
    }
  }
}
