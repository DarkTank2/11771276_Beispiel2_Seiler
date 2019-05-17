/**
 * Filename: GenericTreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import util.searchable.ISearchFilter;

public class GenericTreeNode<NODETYPE> implements ITreeNode<NODETYPE> {

	private NODETYPE nodeValue;
	private String label;
	private List<ITreeNode<NODETYPE>> children;
	
	/**
	 * Constructor for class GenericTreeNode.java
	 * @author Alexander Seiler, 11771276
	 * @param nodeValue
	 * @param label
	 */
	public GenericTreeNode(NODETYPE nodeValue, String label) {
		this.nodeValue = nodeValue;
		this.label = label;
		this.children = new Vector<ITreeNode<NODETYPE>>();
	}

	/* (non-Javadoc)
	 * @see util.searchable.ISearchableByFilter#searchByFilter(util.searchable.ISearchFilter, java.lang.Object)
	 */
	@Override
	public Collection<ITreeNode<NODETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		// TODO Auto-generated method stub
		if (filter == null) return null;
		if (!(compareObject instanceof ITreeNode<?>)) return null;
//		create new collection
		Collection<ITreeNode<NODETYPE>> retVal = new Vector<ITreeNode<NODETYPE>>();
//		add this if it compares successful against the compareObject
		if (filter.searchFilterFunction(this, compareObject)) retVal.add(this);
		if (this.children == null) return retVal;
//		repeat for each child, add the collection from each child to the collection
		this.children.stream().forEach(el -> {
			retVal.addAll(((GenericTreeNode<NODETYPE>) el).searchByFilter(filter, compareObject));
		});
//		some childs may return null, so filter them out
		retVal.parallelStream().filter(el -> el != null);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return this.children.size() == 0;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#getChildren()
	 */
	@Override
	public Collection<ITreeNode<NODETYPE>> getChildren() {
		// TODO Auto-generated method stub
		return this.children;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#nodeValue()
	 */
	@Override
	public NODETYPE nodeValue() {
		// TODO Auto-generated method stub
		return this.nodeValue;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#getLabel()
	 */
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#findNodeByValue(java.lang.Object)
	 */
	@Override
	public ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue) {
		// TODO Auto-generated method stub
		if (this.nodeValue == null || searchValue == null) return null;
//		if this node already equals the given value return it
		if (this.nodeValue.equals(searchValue)) return this;
//		map each children to one node that equals the given value by recursively invoking the method on each child
//		then filter out "null", each child returning null, has no child itself nor is it itself, to match the given value
		List<ITreeNode<NODETYPE>> l = this.children
				.stream()
				.map(el -> el.findNodeByValue(searchValue))
				.filter(el -> el != null)
				.collect(Collectors.toList());
//		if the list contains at least on from null differed node, return the first one
//		could have used a stack for just using the pop()-method
		return l.size() > 0 ? l.get(0) : null;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#findNodeByNode(tree.node.ITreeNode)
	 */
	@Override
	public ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode) {
		// TODO Auto-generated method stub
//		exact same behavior as above, but using this.equals(node) instead of comparing the value
		if (this.equals(searchNode)) return this;
		List<ITreeNode<NODETYPE>> l = this.children
				.stream()
				.map(el -> el.findNodeByNode(searchNode))
				.filter(el -> el != null)
				.collect(Collectors.toList());
		return l.size() > 0 ? l.get(0) : null;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#checkNodeByValue(java.lang.Object)
	 */
	@Override
	public boolean checkNodeByValue(NODETYPE value) {
		// TODO Auto-generated method stub
		if (value == null) return false;
		return this.nodeValue.equals(value);
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#generateConsoleView(java.lang.String, java.lang.String)
	 */
	@Override
	public String generateConsoleView(String spacer, String preamble) {
		// TODO Auto-generated method stub
		if (this.isLeaf()) {
			return spacer + preamble + "-" + this.toString() + "\n";
		}
		String retval = spacer + preamble + "+" + this.toString() + "\n";
		retval += this.children
				.stream()
				.map(el -> el.generateConsoleView(spacer + "  ", preamble))
				.collect(Collectors.joining(""));
		return retval;
	}

	/* (non-Javadoc)
	 * @see tree.node.ITreeNode#deepCopy()
	 */
	@Override
	public ITreeNode<NODETYPE> deepCopy() {
		// TODO Auto-generated method stub
		ITreeNode<NODETYPE> retNode = new GenericTreeNode<NODETYPE>(this.nodeValue, this.label);
		Collection<ITreeNode<NODETYPE>> l = retNode.getChildren();
		this.children.stream().forEach(el -> l.add(el.deepCopy()));
		return retNode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericTreeNode [nodeValue=" + this.nodeValue + ", label=" + this.label + "]";
	}

}
