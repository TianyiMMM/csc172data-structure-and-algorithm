/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab4SimpleLinkedList
 */
public class LinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	
	protected class MyNode<AnyType>{
		public AnyType data; 
		public MyNode<AnyType> next;
		public MyNode(AnyType d) {
			data = d;
			next = null;
		}
	}

	protected MyNode<AnyType> first = null;

	@Override
	public void insert(AnyType x) {
		if (isEmpty()==true){
			MyNode<AnyType> newLink = new MyNode<AnyType>(x);
			first = newLink;
		} else if (contains(x)==false){
			MyNode<AnyType> newLink = new MyNode<AnyType>(x);
			newLink.next = first;
			first = newLink;
		}
	}
	@Override
	public void delete(AnyType x) {
		MyNode<AnyType> check = first;
		MyNode<AnyType> checkprev = first;
		if (contains(x)==true){
			while (check.data!=x && check.data!=null){
				checkprev = check;
				check = check.next;
			}
			checkprev.next = check.next;
		}
	}
	@Override
	public boolean contains(AnyType x) {
		MyNode<AnyType> check = first;
		Boolean contains = false;
		if (isEmpty()==false){
			if (check.data == x){
				contains = true;
			} else {
				while (check.data!=x&&check.next!=null){
					check = check.next;
					//if (check==null)
					//	break;
					if (check.data==x){
						contains = true;
					}
				}
			}
		}
		return contains;
	}
	@Override
	public AnyType lookup(AnyType x) {
		MyNode<AnyType> check = first;
		if (contains(x)==true){
			while (check.data!=x && check!=null){
				check = check.next;
			}
			return check.data;
		}else{
			return null;
		}
	}
	@Override
	public boolean isEmpty() {
		return first==null;
	}
	@Override
	// Expected Runtime: O(n)
	public void printList() {
		MyNode<AnyType> check = first;
		if (check==null)
			System.out.print("null");
		while (check != null) {
			System.out.print((check.data).toString());
			check = check.next;
		}
	}
}
