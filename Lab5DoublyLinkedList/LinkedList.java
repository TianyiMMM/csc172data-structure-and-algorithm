
/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab5DoublyLinkedList
 */
public class LinkedList<AnyType> implements DoublyLinkedList<AnyType> {
	
	public LinkedList() {
		first = new Node<AnyType>();      
		last = new Node<AnyType>();      
		first.prev = null;
		first.next = last;
		last.prev = first;
		last.next = null;
	}

	protected class Node<AnyType>{
		public AnyType data; 
		public Node<AnyType> next;
		public Node<AnyType> prev;
		public Node(){
			data = null;
			next = null;
			prev = null;
		}
		public Node(AnyType d) {
			data = d;
			next = null;
			prev = null;
		}
	}
	
	private Node<AnyType> first = null;
	private Node<AnyType> last = null;
	
	@Override
	public void insert(AnyType x) {
		if (contains(x)==false){
		Node<AnyType> newLink = new Node<AnyType>(x);        
        if(first == null){
            first = newLink;
            last = first;
        } else {
            first.prev=newLink;
            newLink.next=first;
            first = newLink;
        }
		}
	}

	@Override
	public void delete(AnyType x) {
		Node<AnyType> current = first;
		while (current != last) {
			if (x.equals(current.data)) {
				(current.next).prev = current.prev;
				(current.prev).next = current.next;
				return;
			}
			current = current.next;
		}
	}

	@Override
	public boolean contains(AnyType x) {
		Node<AnyType> check = first;
		Boolean contains = false;
		if (check.data == x){
			contains = true;
		} else {
			while (check.data!=x || check.data!=null){
				check = check.next;
				if (check.data==x)
					contains = true;
			}
		}
		return contains;
	}

	@Override
	public AnyType lookup(AnyType x) {
		Node<AnyType> check = first;
		if (contains(x)==true){
			while (check.data!=x || check.data!=null){
				check = check.next;
			}
			return check.data;
		}else{
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return first==null;
	}
	
	// Expected Runtime: O(n)
	@Override
	public void printList() {
		Node<AnyType> check = first;
        while (check.next != null){
        	System.out.print((check.data).toString());
			check = check.next;
        }
	}

	@Override
	public void printListRev() {
		Node<AnyType> check = last;
        while (check.next != null){
        	System.out.print((check.data).toString());
			check = check.next;
        }
	}

}
