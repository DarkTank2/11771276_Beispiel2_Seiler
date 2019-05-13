/**
 * Filename: ProductPriceLessFilter.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 12.05.2019
 */
package util.searchable;

import rbvs.product.IProduct;

public class ProductPriceLessFilter implements ISearchFilter {

	/**
	 * Constructor for class ProductPriceLessFilter.java
	 * @author Alexander Seiler, 11771276
	 */
	public ProductPriceLessFilter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see util.searchable.ISearchFilter#searchFilterFunction(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		// TODO Auto-generated method stub
		IProduct oObject = (IProduct) originalObject;
		IProduct cObject = (IProduct) compareObject;
		return oObject.getPrice() < cObject.getPrice();
	}

}
