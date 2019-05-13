/**
 * Filename: ISearchableByFilter.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package util.searchable;

import java.util.Collection;

public interface ISearchableByFilter<T> {
	Collection<T> searchByFilter(ISearchFilter filter, Object compareObject);
}
