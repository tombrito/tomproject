package tomPack;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleArrayList<E> extends ArrayList<E> {
    
    private static final long serialVersionUID = 3715085547695599050L;

    public SimpleArrayList() {
	super();
    }
    
    public SimpleArrayList(Collection<? extends E> c) {
	super(c);
    }
    
    public SimpleArrayList(int initialCapacity) {
	super(initialCapacity);
    }
    
    public SimpleArrayList(E[] eArray) {
	super();
	for (int i = 0; i < eArray.length; i++) {
	    add(eArray[i]);
	}
    }
}
