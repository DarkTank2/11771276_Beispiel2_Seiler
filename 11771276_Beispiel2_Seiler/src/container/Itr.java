/**
 * Filename: Itr.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package container;

import java.util.Iterator;

public class Itr<E> implements Iterator<E> {

	IContainerElement<E> next;
	
	/**
	 * Constructor for class Itr.java
	 * @author Alexander Seiler, 11771276
	 */
	public Itr(IContainerElement<E> next) {
		// TODO Auto-generated constructor stub
		this.next = next;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		// idea is that if the current element is null, that means that there is no next element
		return this.next != null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next() {
		// TODO Auto-generated method stub
		// idea is that if i call itr.next() i get the current element but set the next element
		// so i can iterate over all elements
		if (this.next == null) {
			return null;
		}
		E tmp = this.next.getData();
		this.next = this.next.hasNextElement() ? this.next.getNextElement() : null;
		return tmp;
	}
	
	public void remove() {
		
	}

}
