/**
 * Filename: Container.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package container;

import java.util.Collection;
import java.util.Iterator;

import util.searchable.ISearchFilter;

public class Container<E> implements ISearchFilter, Collection<E> {

	private IContainerElement<E> firstElement;
	
	/**
	 * Constructor for class Container.java
	 * @author Alexander Seiler, 11771276
	 */
	public Container() {
		// TODO Auto-generated constructor stub
		firstElement = null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		if (arg0 == null) throw new NullPointerException("Argument is 'null'!");
		if (this.firstElement == null) {
			this.firstElement = (IContainerElement<E>) new ContainerElement<E>(arg0);
			return true;
		}
		IContainerElement<E> last = this.firstElement;
		while (last.hasNextElement()) {
			last = last.getNextElement();
		}
		last.setNextElement((IContainerElement<E>) new ContainerElement<E>(arg0));
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.searchable.ISearchFilter#searchFilterFunction(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Container [firstElement=" + firstElement + "]";
	}

}
