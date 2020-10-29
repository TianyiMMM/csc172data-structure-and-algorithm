public class Test {
	
	public static void main(String[] args) {
		Queue<Integer> l = new QueueList<Integer>();
		l.enqueue(1); // push an item into the Queue
		System.out.print("Queue 1: ");
		l.printQueue(); // print current Queue
		System.out.println();
		l.enqueue(2); // push items into the Queue
		l.enqueue(3);
		l.enqueue(4);
		l.enqueue(5);
		System.out.print("Queue 1: ");
		l.printQueue(); // print current Queue
		System.out.println();
		
		System.out.println("Peek Queue 1: "+l.peek()); // peek the first item
		l.dequeue(); // dequeue the first item
		System.out.print("Queue 1 (dequeueed): "); 
		l.printQueue();// print current Queue
		System.out.println();
		
		System.out.println("Peek Queue 1: "+l.peek()); // peek the first item
		l.dequeue(); // dequeue the first item
		System.out.print("Queue 1 (dequeueed): ");
		l.printQueue(); // print current Queue
		System.out.println();
		
		Queue<Integer> ll = new QueueList<Integer>();
		System.out.print("Queue 2: "); // create an empty Queue
		ll.printQueue(); // print the empty Queue
		System.out.println();
		System.out.println("Queue 1 isEmpty: "+l.isEmpty());
		System.out.println("Queue 2 isEmpty: "+ll.isEmpty());
	}
		
}