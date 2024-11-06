public class PilhaDuplaEncad implements IPilha{

  private no topo;
  private int tamanho;

  public PilhaDuplaEncad(){
    this.topo =topo;
    this.tamanho = 0;
  }

  @Override
  public void push(Object o){
    no NovoNo = new no(o);
    if (topo != null){
      NovoNo.anterior = topo;
      topo.proximo = NovoNo;
    }
    topo = NovoNo;
    tamanho++;
  }
  @Override
  public Object pop(){
    if(isEmpty()){
      throw new PilhaVaziaExcecao("Pilha vazia");
    }
    Object r = topo.getValor();
    topo = topo.anterior;
    if (topo != null) {
        topo.proximo = null;  // Remove a referência para o nó removido
    }
    tamanho--;
    return r;
  }
  
  @Override
  public int size(){
    if(isEmpty()){
      throw new PilhaVaziaExcecao("Pilha vazia");
    }
    return tamanho;
  }
  
  @Override
  public boolean isEmpty(){
    return topo == null;
  }
  
  @Override
  public Object top(){
    if (isEmpty()){
      throw new PilhaVaziaExcecao("Pilha vazia");
    }
    return topo.valor;
    }
  public void  exibirPilha(){
    if (isEmpty()){
      System.out.println("A pilha está vazia.");
    }
    no atual = topo;
    while (atual != null){
      System.out.println(atual.valor);
      atual = atual.anterior;
    }
  }

}