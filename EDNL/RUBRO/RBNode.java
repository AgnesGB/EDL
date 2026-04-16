package EDNL.RUBRO;

public class RBNode<T extends Comparable<? super T>> {
    enum Color { RED, BLACK }

    T key;
    RBNode<T> left, right, parent;
    Color color;

    RBNode(T key, Color color, RBNode<T> nil) {
        this.key = key;
        this.color = color;
      
        this.left = nil;
        this.right = nil;
        this.parent = nil;
    }

    
    RBNode(Color color) {
        this.key = null;
        this.color = color;
        this.left = this;
        this.right = this;
        this.parent = this;
    }

    boolean isRed()   { return color == Color.RED; }
    boolean isBlack() { return color == Color.BLACK; }

    @Override public String toString() { return String.valueOf(key); }
}
