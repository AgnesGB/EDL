
public class testePilha {

	public static void main(String[] args) {		
		pilhaArray pp=new pilhaArray(5, 0);
		System.out.println("inserindo");
		pp.push(1);
		pp.push(2);
		pp.push(3);
		pp.push(4);
		pp.exibirPilha();
		System.out.println("retirando");
		pp.pop();
		pp.exibirPilha();
		pp.empty();
		pp.exibirPilha();
		pp.push(56);
		pp.exibirPilha();		
		/*pilhaArray nn=new pilhaArray(1,0);
		for(int f=5;f<10;f++){
		nn.push(f);}
		nn.exibirPilha();
		pp.exibirPilha();
		nn.exibirPilha();*/
	}
}
