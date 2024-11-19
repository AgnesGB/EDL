package lista;

interface Ilista {

        
    public int size();
    public boolean isEmpty();


    public boolean isFirst(no n);
    public boolean isLast(no n);


    public no first();
    public no last();
    public no before(no p);
    public no after(no p);

    public void replaceElement(no n, Object o);
    public void swapElements(no n, no q);
    public void insertBefore(no n, Object o);
    public void insertAfter(no n, Object o);
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(no n);
}
