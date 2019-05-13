/**
 * Filename: TextSearchIgnoreCaseFilter.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package util.searchable;

public class TextSearchIgnoreCaseFilter implements ISearchFilter {

	
	/**
	 * Constructor for class TextSearchIgnoreCaseFilter.java
	 * @author Alexander Seiler, 11771276
	 */
	public TextSearchIgnoreCaseFilter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see util.searchable.ISearchFilter#searchFilterFunction(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		// TODO Auto-generated method stub
		String oObject = originalObject.toString();
		String cObject = compareObject.toString();
		return oObject.equalsIgnoreCase(cObject);
	}

}
