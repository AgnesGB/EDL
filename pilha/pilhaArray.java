public class pilhaArray implements IPilha{

  private int capacidade;
  private Object[] a;
  private int t;
  private int fc;

  public pilhaArray(int capacidade, int crescimento){

    t = -1;
    this.capacidade = capacidade;
    fc = crescimento;
    if (crescimento<=0)
      fc = 0;
    a = new Object[capacidade];
  }

  @Override
  public void push(Object o){
    if (t>=capacidade-1){
      if(fc==0)
        capacidade = capacidade*2;
      else capacidade+=fc;
      Object b[] = new Object[capacidade];
      for (int i =0; i<a.length;i++)
        b[i] = a[i];
      a = b;
    }
    a[++t] = o; 
  }

  @Override
  public Object top(){
    if (isEmpty())
      throw new PilhaVaziaExcecao("Pilha vazia");
    return a[t];
  }

  @Override
  public Object pop(){
    if (isEmpty())
      throw new PilhaVaziaExcecao("Pilha vazia");
   Object r = a[t--];
    return r;
   }

  @Override
  public int size(){
    return t+1;
  }

  @Override
  public boolean isEmpty(){
    return t==-1;
  }

  public void exibirPilha() {
      if (t == -1) { // Verifica se a pilha está vazia
          System.out.println("A pilha está vazia.");
      } else {
          System.out.println("Elementos na pilha:");
          for (int i = 0; i <= t; i++) { // Percorre do início até o topo da pilha
              System.out.println(a[i]);
          }
      }
  }

  public void adicionaPilha(pilhaArray p){
    for(int i = 0; i < p.size();i++){
      this.push(p.a[i]);
    }
  }
  
}