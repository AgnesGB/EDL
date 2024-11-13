interface Ivector {

    public Object elemAtRank(int r);
    public Object ReplaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object RemoveAtRank(int r);
    public int size();
    public boolean isEmpty();

    
}