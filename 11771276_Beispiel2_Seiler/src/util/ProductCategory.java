/**
 * Filename: ProductCategory.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 04.05.2019
 */
package util;

public enum ProductCategory extends Enum<ProductCategory> {
	
	public static final ProductCategory FOOD;
	public static final ProductCategory BEVERAGE;
	public static final ProductCategory DEFAULT;
	
	private String label;

	/**
	 * Constructor for class ProductCategory.java
	 * @author Alexander Seiler, 11771276
	 * @param label
	 */
	private ProductCategory(String label) {
		this.label = label;
	}



	/**
	 * Getter-method for label
	 * @author Alexander Seiler, 11771276
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	
}
