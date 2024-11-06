
public class testePilha {

	public static void main(String[] args) {		
		PilhaDuplaEncad pp=new PilhaDuplaEncad();
		System.out.println("inserindo");
		pp.push(1);
		pp.push(2);
		pp.push(3);
		pp.push(4);
		pp.exibirPilha();
		System.out.println("retirando");
		while(!pp.isEmpty()){
			pp.pop();
		}
		pp.exibirPilha();		
		/*pilhaArray nn=new pilhaArray(1,0);
		for(int f=5;f<10;f++){
		nn.push(f);}
		nn.exibirPilha();
		pp.exibirPilha();
		nn.exibirPilha();*/
	}
}
