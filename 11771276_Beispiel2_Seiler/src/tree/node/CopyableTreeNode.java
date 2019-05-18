/**
 * Filename: CopyableTreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import java.util.Collection;

import rbvs.copy.IDeepCopy;

public class CopyableTreeNode<NODETYPE extends IDeepCopy> extends GenericTreeNode<NODETYPE> {

	/**
	 * Constructor for class CopyableTreeNode.java
	 * @author Alexander Seiler, 11771276
	 * @param nodeValue
	 * @param label
	 */
	public CopyableTreeNode(NODETYPE nodeValue, String label) {
		super(nodeValue, label);
		// TODO Auto-generated constructor stub
	}

	public CopyableTreeNode<NODETYPE> deepCopy() {
		CopyableTreeNode<NODETYPE> retVal = new CopyableTreeNode<NODETYPE>((NODETYPE) this.nodeValue().deepCopy(), this.getLabel());
		Collection<ITreeNode<NODETYPE>> l = retVal.getChildren();
		this.getChildren().stream().forEach(el -> l.add(el.deepCopy()));
		return retVal;
	}
}
