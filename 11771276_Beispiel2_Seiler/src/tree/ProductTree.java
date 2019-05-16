/**
 * Filename: ProductTree.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree;

import rbvs.product.IProduct;
import tree.node.ITreeNode;
import tree.node.ProductTreeNode;

public class ProductTree extends GenericTree<IProduct> {

	public ProductTree(ITreeNode<IProduct> root) {
		super(root);
	}
	public ProductTree(IProduct product) {
		super(new ProductTreeNode(product));
	}
	public ProductTree() {
		super();
	}
}
