package tomPack;

import java.util.ArrayList;
import java.util.List;

public class TomCollections {

	/**
	 * Get the specified elementsToGet from the elements list, if they are
	 * contained on it.
	 * 
	 * @return the list of elementsToGet that was contained in elements list.
	 */
	public static <E> List<E> getAll(List<? extends E> elementsToGet,
			List<? extends E> elements) {
		List<E> gets = new ArrayList<E>();
		for (E e : elementsToGet) {
			if (elements.contains(e)) {
				gets.add(e);
			}
		}
		return gets;
	}

	/**
	 * Remove the specified elementsToRemove from the specified elements list.
	 * 
	 * @return the list of removed elements.
	 */
	public static <E> List<E> removeAll(List<E> elementsToRemove,
			List<E> elements) {
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
