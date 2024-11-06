import java.awt.PopupMenu;

/*
 * Created on 01/09/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Robinson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class testePilha {

	public static void main(String[] args) {		
		Integer[] b = new Integer[1];		
		Object f = 5;
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
