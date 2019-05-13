/**
 * Filename: ContainerElement.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 13.05.2019
 */
package container;

public class ContainerElement<E> implements IContainerElement<E> {

	private E data;
	private IContainerElement<E> nextElement = null;
	
	/**
	 * Constructor for class ContainerElement.java
	 * @author Alexander Seiler, 11771276
	 */
	public ContainerElement(E data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}

	/**
	 * Constructor for class ContainerElement.java
	 * @author Alexander Seiler, 11771276
	 * @param data
	 * @param nextElement
	 */
	public ContainerElement(E data, IContainerElement<E> nextElement) {
		this.data = data;
		this.nextElement = nextElement;
	}

	@Override
	public void setNextElement(IContainerElement<E> next) {
		// TODO Auto-generated method stub
		this.nextElement = next;
	}

	@Override
	public boolean hasNextElement() {
		// TODO Auto-generated method stub
		return this.nextElement != null;
	}

	@Override
	public IContainerElement<E> getNextElement() {
		// TODO Auto-generated method stub
		return this.nextElement;
	}

	@Override
	public E getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

}
