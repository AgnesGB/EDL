import java.util.ArrayList;
import java.util.Iterator;

public class No 
	{
		private Object o;
		private No pai;
		private ArrayList filhos = new ArrayList();

		public No(No pai, Object o)
		{
			this.pai = pai;
			this.o = o;
		}
		public Object element()
		{
			return o;
		}
		public No parent()
		{
			return pai;
		}
		public void setElement(Object o)
		{
			this.o = o;
		}
		public void addChild(No o)
		{
			filhos.add(o);
		}
		public void removeChild(No o)
		{
			filhos.remove(o);
		}
		public int childrenNumber()
		{
			return filhos.size();
		}
		public Iterator children()
		{
			return filhos.iterator();
		}
        public No getLeftChild() {
            return filhos.size() > 0 ? (No) filhos.get(0) : null;
        }
        
        public No getRightChild() {
            return filhos.size() > 1 ? (No) filhos.get(1) : null;
        }
        
        public void setLeftChild(No child) {
            if (filhos.size() > 0) {
                filhos.set(0, child);
            } else {
                filhos.add(child);
            }
        }
        
        public void setRightChild(No child) {
            if (filhos.size() > 1) {
                filhos.set(1, child);
            } else if (filhos.size() == 1) {
                filhos.add(child);
            }
        }
	}
