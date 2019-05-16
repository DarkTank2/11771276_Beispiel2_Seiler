/**
 * Filename: ProductCategoryTreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import util.ProductCategory;

public class ProductCategoryTreeNode<NODETYPE> extends CategoryTreeNode<NODETYPE, ProductCategory> {

	public ProductCategoryTreeNode(ProductCategory category) {
		super(category);
		// TODO Auto-generated constructor stub
	}

	public String getLabel() {
		return super.getCategory().toString();
	}
}
