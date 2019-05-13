/**
 * Filename: ISearchFilter.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package util.searchable;

public interface ISearchFilter {
	boolean searchFilterFunction(Object originalObject, Object compareObject);
}
