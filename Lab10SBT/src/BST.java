/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab10SBT
 */
public interface BST<T extends Comparable<T>> { 
	
	public void insert(T x); 
	public void delete(T x); 
	public boolean lookup(T x); 
	public void printPreOrder(); 
	public void printInOrder(); 
	public void printPostOrder();
	
}