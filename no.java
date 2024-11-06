class no {
  Object valor;
  no proximo;
  no anterior;

  public no(Object valor) {
    this.valor = valor;
    this.proximo = null;
    this.anterior = null;
  }
  public Object getValor(){
    return this.valor;
  }
  public no getProximo(){
    return this.proximo;
  }
  public no getAnterior(){
    return this.anterior;
  }
  public void setValor(Object valor){
    this.valor = valor;
  }
  public void setProximo(no proximo){
    this.proximo = proximo;
  }
  public void setAnterior(no anterior){
    this.anterior = anterior;
  }
}