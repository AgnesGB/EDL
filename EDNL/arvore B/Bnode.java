import java.util.ArrayList;
import java.util.List

public class Bnode<T extends Comparable<T>> {
    public final List<T> keys = new ArrayList<>();
    public final List<Bnode<T>> children = new ArrayList<>();
    public boolean leaf = true;

    public int keyCount() {return keys.size(): }
    public int childCount() { return children.size(): }
 }