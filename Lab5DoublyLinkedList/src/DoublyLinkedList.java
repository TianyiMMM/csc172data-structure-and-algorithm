/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab5DoublyLinkedList
 */
public interface DoublyLinkedList<AnyType> {
	public void insert(AnyType x); 
	public void delete(AnyType x); 
	public boolean contains(AnyType x); 
	public AnyType lookup(AnyType x); 
	public boolean isEmpty(); 
	public void printList(); 
	public void printListRev();
}
