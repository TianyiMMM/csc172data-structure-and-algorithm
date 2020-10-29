/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab10SBT
 */
public class Test {
	public static void main(String[] args) {
		BSTree<Integer> l = new BSTree<Integer>();
		l.insert(15); // test insert() method
		l.insert(6); 
		l.insert(8);
		l.insert(7);
		l.insert(3);
		l.insert(18);
		System.out.print("BSTree l1 (PreOrder): ");
		l.printPreOrder(); // test printPreOrder()
		System.out.print("BSTree l1 (InOrder): ");
		l.printInOrder(); // test printInOrder()
		System.out.println();
		System.out.print("BSTree l1 (PostOrder): ");
		l.printPostOrder(); // test printPostOrder()
		System.out.println();
		String strData = l.strDataPreOrder();
		String strStrct = l.strStructurePreOrder();
		System.out.println(strData);
		System.out.println(strStrct);
		BSTree<Integer> lCopy = new BSTree<Integer>(strData, strStrct);
		System.out.print("BSTree l1 (PreOrder): ");
		lCopy.printPreOrder(); // test printPreOrder()
		System.out.println();
		System.out.print("BSTree l1 (InOrder): ");
		lCopy.printInOrder(); // test printInOrder()
		System.out.println();
		System.out.print("BSTree l1 (PostOrder): ");
		lCopy.printPostOrder(); // test printPostOrder()
		System.out.println();
	}
}
