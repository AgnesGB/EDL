public class FilaDuplaEncad implements IFila{
  private no inicio;
  private no fim;
  private int tamanho;
  public FilaDuplaEncad() {
    this.inicio = null;
    this.fim = null;
    this.tamanho = 0;
  }

public int GetTamanho(){
   return this.tamanho;
 }
public no getInicio(){
  return this.inicio;
}
public no getFim(){
  return this.fim;
}

public void setInicio(no inicio){
  this.inicio = inicio;
}

public void setFim(no fim){
  this.fim = fim;
} 

  @Override
  public void enqueue(Object o){
    no novoNo = new no(o);
    if(isEmpty()){
      inicio = novoNo;
    }else{
      fim.setProximo(novoNo);
      novoNo.setAnterior(fim);
    }
    fim = novoNo;
    tamanho++;

  }

  @Override
  public Object dequeue(){
    if(isEmpty()){
      throw new FilaVaziaException("Fila vazia.");
    }
    Object valorRemovido = inicio.valor;
    inicio = inicio.proximo;
    if(inicio == null){
      fim = null;
    }
    tamanho--;
    return valorRemovido;
  }
  @Override
  public Object first(){
    if(isEmpty()){
      throw new FilaVaziaException("Fila vazia.");
    }
    return inicio.valor;
  }
  @Override
  public int size(){
    return tamanho;
  }
  @Override
  public boolean isEmpty(){
    return tamanho == 0;
  }

  public void exibirFila() {
    System.out.print("Fila: [");
    no atual = inicio;
    while (atual != null) {
      System.out.print(atual.valor);
      atual = atual.proximo;
      if (atual != null) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }
}