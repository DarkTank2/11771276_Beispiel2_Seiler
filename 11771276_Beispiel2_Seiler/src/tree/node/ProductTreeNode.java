/**
 * Filename: ProductTreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import java.util.Collection;
import rbvs.product.CompositeProduct;
import rbvs.product.IProduct;
import rbvs.product.Product;

public class ProductTreeNode extends GenericTreeNode<IProduct> {

	public ProductTreeNode(IProduct nodeValue, String label) {
		super(nodeValue, label);
		// TODO Auto-generated constructor stub
		this.initialize(nodeValue);
	}

	public ProductTreeNode(IProduct nodeValue) {
		super(nodeValue, nodeValue == null ? "" : nodeValue.getName());
		this.initialize(nodeValue);
	}
	
	private ProductTreeNode(CompositeProduct value) {
		super(value, value == null ? "" : value.getName());
	}
	
	private void initialize(IProduct item) {
		if (item instanceof CompositeProduct) {
			Collection<ITreeNode<IProduct>> l = this.getChildren();
			CompositeProduct comp = (CompositeProduct) item;
			Collection<Product> li = comp.getProducts();
			li.stream().forEach(el -> l.add(new ProductTreeNode(el)));
		}
	}
	
	public ProductTreeNode deepCopy() {
		ProductTreeNode retNode = new ProductTreeNode((IProduct) this.nodeValue().deepCopy(), this.getLabel());
		Collection<ITreeNode<IProduct>> l = retNode.getChildren();
//		clear the list of the newly generated product since it might happen that a value is a composite product then the list will be initialized with the references of the to-be-copied product
		l.clear();
		this.getChildren().stream().forEach(el -> l.add(el.deepCopy()));
		return retNode;
	}
}
