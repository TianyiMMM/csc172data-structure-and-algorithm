import DoubleLinkedList.LinkedList;
import DoubleLinkedList.DoublyLinkedList;
public class QueueList<AnyType> implements Queue<AnyType>{
	
	DoublyLinkedList<AnyType> list = new LinkedList<AnyType>();
	public QueueList(){
		DoublyLinkedList<AnyType> list = new LinkedList<AnyType>();
		this.list=list;
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(AnyType x) {
		list.insert(x);
	}

	@Override
	public AnyType dequeue() {
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
	public void printQueue() {
		list.printList();
	}

}
