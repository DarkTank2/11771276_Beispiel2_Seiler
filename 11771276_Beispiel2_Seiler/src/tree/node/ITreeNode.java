/**
 * Filename: ITreeNode.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 16.05.2019
 */
package tree.node;

import java.util.Collection;

import rbvs.copy.IDeepCopy;
import util.searchable.ISearchableByFilter;

public interface ITreeNode<NODETYPE> extends IDeepCopy, ISearchableByFilter<ITreeNode<NODETYPE>> {
	boolean isLeaf();
	Collection<ITreeNode<NODETYPE>> getChildren();
	NODETYPE nodeValue();
	String getLabel();
	ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue);
	ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode);
	boolean checkNodeByValue(NODETYPE value);
	String generateConsoleView(String spacer, String preamble);
	ITreeNode<NODETYPE> deepCopy();
}
