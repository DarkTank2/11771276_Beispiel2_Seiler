/**
 * Filename: IContainerElement.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package container;

public interface IContainerElement<E> {
	void setNextElement(IContainerElement<E> next);
	boolean hasNextElement();
	IContainerElement<E> getNextElement();
	E getData();
}
