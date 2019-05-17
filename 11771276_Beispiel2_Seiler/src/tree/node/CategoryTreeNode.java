/**
 * Filename: CategoryTreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import java.util.Collection;

public class CategoryTreeNode<NODETYPE, CATEGORY> extends GenericTreeNode<NODETYPE> {

	private CATEGORY category;

	/**
	 * Constructor for class CategoryTreeNode.java
	 * @author Alexander Seiler, 11771276
	 * @param category
	 */
	public CategoryTreeNode(CATEGORY category) {
		super(null, category == null ? "" : category.toString());
		this.category = category;
	}
	
	public CATEGORY getCategory() {
		return this.category;
	}
	
	public NODETYPE nodeValue() {
		return null;
	}
	
	public String getLabel() {
		return super.getLabel();
	}
	
	public CategoryTreeNode<NODETYPE,CATEGORY> deepCopy() {
		CategoryTreeNode<NODETYPE, CATEGORY> retVal = new CategoryTreeNode<NODETYPE, CATEGORY>(this.category);
		Collection<ITreeNode<NODETYPE>> l = retVal.getChildren();
		this.getChildren().stream().forEach(el -> l.add(el.deepCopy()));
		return retVal;
	}
}
