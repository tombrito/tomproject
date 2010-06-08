package tomPack;

import java.util.ArrayList;
import java.util.List;

public class TomColletions {
    
    /**
     * Remove all the specified element list from the other specified element
     * list.
     * 
     * @return the list of removed elements
     */
    public static <E> List<E> getAll(List<? extends E> elementsToGet, List<? extends E> elements) {
	List<E> gets = new ArrayList<E>();
	for (E e : elementsToGet) {
	    if (elements.contains(e)) {
		gets.add(e);
	    }
	}
	return gets;
    }

    /**
     * Remove all the specified element list from the other specified element
     * list.
     * 
     * @return the list of removed elements
     */
    public static <E> List<E> removeAll(List<E> elementsToRemove, List<E> elements) {
	List<E> removed = new ArrayList<E>();
	for (E e : elementsToRemove) {
	    if (elements.contains(e)) {
		elements.remove(e);
		removed.add(e);
	    }
	}
	return removed;
    }

}
