/**
 * Filename: Container.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package container;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import util.searchable.ISearchFilter;
import util.searchable.ISearchableByFilter;

public class Container<E> implements ISearchableByFilter<E>, Collection<E> {

	private IContainerElement<E> firstElement;
	
	/**
	 * Constructor for class Container.java
	 * @author Alexander Seiler, 11771276
	 */
	public Container() {
		// TODO Auto-generated constructor stub
		this.firstElement = null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		if (arg0 == null) throw new NullPointerException("[add] Passed argument is 'null'!");
		IContainerElement<E> toBeAdded = new ContainerElement<E>(arg0);
		if (this.contains(toBeAdded)) return false;
		if (this.firstElement == null) {
			this.firstElement = toBeAdded;
			return true;
		}
		IContainerElement<E> last = this.firstElement;
		while (last.hasNextElement()) {
			last = last.getNextElement();
		}
		last.setNextElement(toBeAdded);
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		if (arg0 == null) throw new NullPointerException("[addAll] Passed argument is 'null'!");
		List<Boolean> l = arg0.stream().map(el -> this.add(el)).collect(Collectors.toList());
		if (l.contains(true)) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.firstElement = null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
//		if (arg0 == null) throw new NullPointerException("[contains] Passed argument is 'null'!");
		if (!(arg0 instanceof IContainerElement<?>)) return false;
		if (this.firstElement == null) return false;
		IContainerElement<E> check = (IContainerElement<E>) arg0;
		IContainerElement<E> last = this.firstElement;
		if (last.getData().equals(check.getClass())) return true;
		while (last.hasNextElement()) {
			last = last.getNextElement();
			if (last.getData().equals(check.getData())) return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
//		if (arg0 == null) throw new NullPointerException("[containsAll] Passed argument is 'null'!");
		List<Boolean> l = arg0.stream().map(el -> this.contains(el)).collect(Collectors.toList());
		if (l.contains(false)) return false;
		return true;
	}

	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException("[get] Index not in range between 0 and " + this.size());
		if (this.firstElement == null) return null;
		IContainerElement<E> toGet = this.firstElement;
		for (int i = 0; i < index; ++i) {
//			if the current element has no next element, although the size implicates that it shall have one
			if (!toGet.hasNextElement()) throw new NullPointerException("[get] element at index '" + i + "' has no next element");
			toGet = toGet.getNextElement();
		}
		return toGet.getData();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.firstElement == null;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Itr<E>(this.firstElement);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
//		if (arg0 == null) throw new NullPointerException("[remove] Passed argument is 'null'!");
		if (!(arg0 instanceof IContainerElement<?>)) return false;
		IContainerElement<E> check = (IContainerElement<E>) arg0;
		if (!this.contains(check)) return false;
		IContainerElement<E> last = this.firstElement;
		if (last.getData().equals(check.getData())) {
			this.firstElement = last.getNextElement();
			return true;
		}
		while (last.hasNextElement()) {
			// if the current element has a next element, it is not the last one
			
			// copy the next element on a temporary var
			IContainerElement<E> tmp = last.getNextElement();
			// check if the temporary (the next one) equals the to-be-checked-one
			if (tmp.getData().equals(check.getData())) {
				// if so, set the reference to the next element of the current one to the next element of the temporary (next one) 
				// so it gets skipped
				last.setNextElement(tmp.getNextElement());
				// and return true since the list shall not be altered any more
				return true;
			}
			// this happens if the temporary next el is not equal
			// in this case set the current element to the next
			last = tmp;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
//		if (arg0 == null) throw new NullPointerException("[removeAll] Passed argument is 'null'!");
		if (arg0 == null) return false;
		List<Boolean> l = arg0.stream().map(el -> this.remove(el)).collect(Collectors.toList());
		if (l.contains(true)) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("[retainAll] this method is not supported!");
		try {
			throw new UnsupportedOperationException("[retainAll] this method is not supported!"); 
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		int i = 1;
		if (this.firstElement == null) return 0;
		IContainerElement<E> last = this.firstElement;
		while (last.hasNextElement()) {
			if (i == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			++i;
			last = last.getNextElement();
		}
		return i;
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("[Object[] toArray] this method is not supported!");
		try {
			throw new UnsupportedOperationException("[Object[] toArray] this method is not supported!"); 
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("[<T> T[] toArray] this method is not supported!");
		try {
			throw new UnsupportedOperationException("[<T> T[] toArray] this method is not supported!"); 
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.firstElement == null) {
			return "Container [firstElement=null]";
		}
		return "Container [firstElement=" + this.firstElement.toString() + "]";
	}

	@Override
	public Collection<E> searchByFilter(ISearchFilter filter, Object compareObject) {
		// TODO Auto-generated method stub
//		if (filter == null) throw new NullPointerException("[searchByFilter] filter is 'null'!");
//		if (compareObject == null) throw new NullPointerException("[searchByFilter] compareObject is 'null'!");
		if (filter == null) return null;
		if (!(compareObject instanceof IContainerElement<?>)) return null;
		IContainerElement<E> compObj = (IContainerElement<E>) compareObject;
		Container<E> retCont = new Container<E>();
		if (this.firstElement == null) return retCont;
		IContainerElement<E> last = this.firstElement;
		if (filter.searchFilterFunction(last, compareObject)) {
			retCont.add(last.getData());
		}
		while (last.hasNextElement()) {
			last = last.getNextElement();
			if (filter.searchFilterFunction(last, compareObject)) {
				retCont.add(last.getData());
			}
		}
		return retCont;
	}

}
