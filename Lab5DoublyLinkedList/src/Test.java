/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab5DoublyLinkedList
 */
public class Test {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> l = new LinkedList<Integer>();
		l.insert(1);
		l.insert(2); 
		l.insert(3);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		l.insert(2); 
		l.insert(3);
		l.insert(4);
		l.insert(4);
		l.insert(5);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		System.out.print("LinkedList l1 Reverse: ");
		l.printListRev();
		System.out.println();
		l.delete(2);
		System.out.print("LinkedList l1: ");
		l.printList();
		System.out.println();
		System.out.println("Contains 2: "+l.contains(2));
		System.out.println("Contains 3: "+l.contains(3));
		System.out.println("Lookup 3: "+l.lookup(3));
		DoublyLinkedList<Integer> ll = new LinkedList<Integer>();
		System.out.println("l isEmpty: "+l.isEmpty());
		System.out.println("ll isEmpty: "+ll.isEmpty());
	}

}
