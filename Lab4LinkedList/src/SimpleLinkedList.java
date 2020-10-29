/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab4SimpleLinkedList
 */
public interface SimpleLinkedList<AnyType> { 
	public void insert(AnyType x); 
	public void delete(AnyType x); 
	public boolean contains(AnyType x); 
	public AnyType lookup(AnyType x); 
	public boolean isEmpty(); 
	public void printList();

}