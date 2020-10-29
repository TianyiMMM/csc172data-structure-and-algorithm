/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab4SimpleLinkedList
 */
public class Test {

	public static void main(String[] args) {
		SimpleLinkedList<Integer> l = new LinkedList<Integer>();
		l.insert(1);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		l.insert(2); 
		l.insert(3);
		l.insert(4);
		l.insert(4);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		l.delete(2);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		System.out.println("Contains 2: "+l.contains(2));
		System.out.println("Contains 3: "+l.contains(3));
		System.out.println("Lookup 3: "+l.lookup(3));
		SimpleLinkedList<Integer> ll = new LinkedList<Integer>();
		System.out.print("LinkedList l2: ");
		ll.printList();
		System.out.println();
		System.out.println("l isEmpty: "+l.isEmpty());
		System.out.println("ll isEmpty: "+ll.isEmpty());
	}

}
