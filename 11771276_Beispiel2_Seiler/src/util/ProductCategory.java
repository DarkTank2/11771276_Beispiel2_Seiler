/**
 * Filename: ProductCategory.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 04.05.2019
 */
package util;

public enum ProductCategory {
	
	FOOD,
	BEVERAGE,
	DEFAULT;
	
	private final String label;

	private ProductCategory() {
		this.label = null;
	}
	
	/**
	 * Constructor for class ProductCategory.java
	 * @author Alexander Seiler, 11771276
	 * @param label
	 */
	ProductCategory(String label) {
		this.label = label;
	}



	/**
	 * Getter-method for label
	 * @author Alexander Seiler, 11771276
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}
	
	
}
