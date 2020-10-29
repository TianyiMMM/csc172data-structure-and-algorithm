/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab6Stack
 */

public class Test {
	
	public static void main(String[] args) {
		Stack<Integer> l = new StackList<Integer>();
		l.push(1); // push an item into the stack
		System.out.print("Stack 1: ");
		l.printStack(); // print current stack
		System.out.println();
		l.push(2); // push items into the stack
		l.push(3);
		l.push(4);
		l.push(5);
		System.out.print("Stack 1: ");
		l.printStack(); // print current stack
		System.out.println();
		
		System.out.println("Peek Stack 1: "+l.peek()); // peek the first item
		l.pop(); // pop the first item
		System.out.print("Stack 1 (poped): "); 
		l.printStack();// print current stack
		System.out.println();
		
		System.out.println("Peek Stack 1: "+l.peek()); // peek the first item
		l.pop(); // pop the first item
		System.out.print("Stack 1 (poped): ");
		l.printStack(); // print current stack
		System.out.println();
		
		Stack<Integer> ll = new StackList<Integer>();
		System.out.print("Stack 2: "); // create an empty stack
		ll.printStack(); // print the empty stack
		System.out.println();
		System.out.println("Stack 1 isEmpty: "+l.isEmpty());
		System.out.println("Stack 2 isEmpty: "+ll.isEmpty());
	}
		
}
