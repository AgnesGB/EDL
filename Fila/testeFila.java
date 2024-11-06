public class testeFila {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilaDuplaEncad f=new FilaDuplaEncad(); //criando fila tamnho 1 e duplicação
		f.enqueue(1);
		f.enqueue(2);
		f.enqueue(3);
		f.enqueue(4);
		try{
			f.exibirFila();
			System.out.println(f.size());
			System.out.println(f.first());
			f.dequeue();
			f.exibirFila();
			System.out.println(f.first());
			f.dequeue();
			f.exibirFila();
			System.out.println(f.first());
			f.dequeue();
			f.exibirFila();
			System.out.println(f.first());
			f.dequeue();
			f.exibirFila();
			System.out.println(f.first());
			f.dequeue();
			f.exibirFila();
			System.out.println(f.size());

		}
		
		catch(FilaVaziaException erro){
			System.out.println(erro.getMessage());

		}
	}

}
