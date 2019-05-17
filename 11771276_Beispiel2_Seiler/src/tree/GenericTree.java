/**
 * Filename: GenericTree.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree;

import java.util.Collection;

import tree.node.ITreeNode;
import util.searchable.ISearchFilter;

public class GenericTree<TREETYPE> implements ITree<TREETYPE> {

	private ITreeNode<TREETYPE> root;
	
	/**
	 * Constructor for class GenericTree.java
	 * @author Alexander Seiler, 11771276
	 */
	public GenericTree() {
		// TODO Auto-generated constructor stub
		this.root = null;
	}

	/**
	 * Constructor for class GenericTree.java
	 * @author Alexander Seiler, 11771276
	 * @param root
	 */
	public GenericTree(ITreeNode<TREETYPE> root) {
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see util.searchable.ISearchableByFilter#searchByFilter(util.searchable.ISearchFilter, java.lang.Object)
	 */
	@Override
	public Collection<ITreeNode<TREETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		// TODO Auto-generated method stub
		return this.root.searchByFilter(filter, compareObject);
	}

	/* (non-Javadoc)
	 * @see tree.ITree#setRoot(tree.node.ITreeNode)
	 */
	@Override
	public void setRoot(ITreeNode<TREETYPE> root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see tree.ITree#getRoot()
	 */
	@Override
	public ITreeNode<TREETYPE> getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	/* (non-Javadoc)
	 * @see tree.ITree#findNode(java.lang.Object)
	 */
	@Override
	public ITreeNode<TREETYPE> findNode(TREETYPE searchValue) {
		// TODO Auto-generated method stub
//		invoke the search for the root-element
		return this.root.findNodeByValue(searchValue);
	}

	/* (non-Javadoc)
	 * @see tree.ITree#findNode(tree.node.ITreeNode)
	 */
	@Override
	public ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode) {
		// TODO Auto-generated method stub
//		invoke the search for the root-element
		return this.root.findNodeByNode(searchNode);
	}

	/* (non-Javadoc)
	 * @see tree.ITree#generateConsoleView(java.lang.String)
	 */
	@Override
	public String generateConsoleView(String spacer) {
		// TODO Auto-generated method stub
		return "Tree-representation:\n" + this.root.generateConsoleView(spacer, "|_");
	}

	/* (non-Javadoc)
	 * @see tree.ITree#deepCopy()
	 */
	@Override
	public ITree<TREETYPE> deepCopy() {
		// TODO Auto-generated method stub
		return new GenericTree<TREETYPE>(this.root.deepCopy());
	}

}
