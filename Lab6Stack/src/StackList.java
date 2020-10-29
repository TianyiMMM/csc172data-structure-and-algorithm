/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab6Stack
 */

import SimpleLinkedList.LinkedList;
import SimpleLinkedList.SimpleLinkedList;

public class StackList<AnyType> implements Stack<AnyType>{

	SimpleLinkedList<AnyType> list = new LinkedList<AnyType>();
	public StackList(){
		SimpleLinkedList<AnyType> list = new LinkedList<AnyType>();
		this.list=list;
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(AnyType x) {
		list.insert(x);
	}

	@Override
	public AnyType pop() {
		if (list.isEmpty()==true)
			return null;
		else
			return list.delete();
	}

	@Override
	public AnyType peek() {
		if (list.isEmpty()==true)
			return null;
		else
			return list.lookup();
	}
	
	@Override
	public void printStack() {
		list.printList();
	}

}
