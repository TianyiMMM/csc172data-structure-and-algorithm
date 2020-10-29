/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab6Stack
 */
public interface Stack<AnyType> { 
	
	public boolean isEmpty(); 
	public void push(AnyType x); 
	public AnyType pop(); 
	public AnyType peek(); 
	public void printStack();
	
}
