/**
 * Filename: ITree.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree;

import rbvs.copy.IDeepCopy;
import tree.node.ITreeNode;
import util.searchable.ISearchableByFilter;

public interface ITree<TREETYPE> extends ISearchableByFilter<ITreeNode<TREETYPE>>, IDeepCopy {
	void setRoot(ITreeNode<TREETYPE> root);
	ITreeNode<TREETYPE> getRoot();
	ITreeNode<TREETYPE> findNode(TREETYPE searchValue);
	ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode);
	String generateConsoleView(String spacer);
	ITree<TREETYPE> deepCopy();
}
