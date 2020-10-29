/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab10SBT
 */
public class BSTree<T extends Comparable<T>> implements BST<T> {
	
	public class TNode<T extends Comparable<T>> { 
		public T data; 
		public TNode<T> left; 
		public TNode<T> right; 
		public TNode<T> p;
		
		public void printPreOrder() {
			if (this != null){
				System.out.print(this.data.toString()+" ");
				if (this.left != null){
				this.left.printPreOrder();
				}
				if (this.right != null){
				this.right.printPreOrder();
				}
			}
		}

		public void printInOrder() {
			if (this != null){
				if (this.left != null){
				this.left.printInOrder();
				}
				System.out.print(this.data.toString()+" ");
				if (this.right != null){
				this.right.printInOrder();
				}
			}
		}
		String str1 = "";
		public String strDataPreOrder() {
			if (this != null){
				str1 += this.data.toString()+",";
				if (this.left != null){
					str1 += this.left.strDataPreOrder();
				}
				if (this.right != null){
					str1 += this.right.strDataPreOrder();
				}
			}
			return str1;
		}
		String str2 = "";
		public String strStructurePreOrder() {
			if (this != null){
				str2 = str2 + "1";
				if (this.left != null){
					str2 += this.left.strStructurePreOrder();
				} else {
					str2 = str2 + "0";
				}
				if (this.right != null){
					str2 += this.right.strStructurePreOrder();
				} else {
					str2 = str2 + "0";
				}
			}
			return str2;
		}
		
		public void printPostOrder() {
			if (this != null){
				if (this.left != null){
				this.left.printPostOrder();
				}
				if (this.right != null){
				this.right.printPostOrder();
				}
				System.out.print(this.data.toString()+" ");
			}
		}
	}
	
	// BSTree class starts here
	
	private TNode<T> root = null;
	
	private String strct = "";
	private String[] data = new String[10];
	private int curr = 0;
	private int count = 0;
	private int length = 0;
	
	public BSTree(){
		
	}
	// new constructor
	public BSTree(String data, String structure){
		strct = structure;
		length = strct.length();
		int l = data.length(); // this.data array initialization begins
		String temp = "";
		int count = 0;
		for (int i = 0; i <= l; i++){
			if (i==l){
				this.data[count]=temp;
				count++;
			} else if (data.charAt(i)!=','){
				temp += data.charAt(i);
			} else {
				this.data[count]=temp;
				count++;
				temp = "";
			}
		}
		String[] strTemp = new String[count];
		this.count = count;
		count=0;
		for (String str : this.data){
			if (str!=null){
				strTemp[count] = str;
				count++;
			}
		}
		this.data = strTemp; // this.data array initialization finished
		root = (BSTree<T>.TNode<T>) treeBuilder(strct); // initiate the root for this tree
	}
	
	// treeBuilder
	private int c = 0;
	public TNode<String> treeBuilder(String strct){
		c++;
		if (c <= length){ // avoid StringOutOfBound error
		if (strct.charAt(0)==0){
			int l = strct.length();
			strct = strct.substring(1, l);
			return null;
		} else {
			int l = strct.length();
			strct = strct.substring(1, l);
			TNode<String> newNode = new TNode<String>();
			if (curr<count){
			newNode.data = this.data[curr];
			//System.out.println("data: "+newNode.data);
			}
			curr++;
			newNode.left = treeBuilder(strct);
			newNode.right = treeBuilder(strct);
			return newNode;
		}
		} else {
			return null;
		}
	}
	
	@Override
	public void insert(T x) {
		if (this.lookup(x)==false){
		TNode<T> newNode = new TNode<T>();
		newNode.data = x;
		TNode<T> c = this.root;
		TNode<T> y = new TNode<T>();
		while (c != null){
			y = c;
			if (newNode.data.compareTo(c.data)<0){
				c = c.left;
			} else {
				c = c.right;
			}
		newNode.p = y;
		}
		if (root == null){
			root = newNode;
		} else if (newNode.data.compareTo(y.data)<0){
			y.left = newNode;
		} else {
			y.right = newNode;
		}
		}
	}

	public void transplant(TNode<T> replaced, TNode<T> replacing){
		if (replaced.p==null){
			root = replacing;
		} else if (replaced == replaced.p.left){
			replaced.p.left = replacing;
		} else {
			replaced.p.right = replacing;
		}
		if (replacing != null){
			replacing.p = replaced.p;
		}
	}
	@Override
	public void delete(T x) {
		if (this.lookup(x)==true){
		TNode<T> c = this.root;
		while (c.data != x){
			if (x.compareTo(c.data)<0){
				c = c.left;
			} else {
				c = c.right;
			}
		}

		if (c.left == null && c.right == null){ // cases when both child nodes are empty
			this.transplant(c, c.right);
		} else if (c.left != null && c.right != null){ // cases when both child nodes are not empty
			TNode<T> r = c.right;
			while (r.left != null){
				r = r.left;
			}
			TNode<T> y = new TNode<T>();
			y = r;
			if (y.p != c){
				this.transplant(y, y.right);
				y.right = c.right;
				y.right.p = y;
			}
			this.transplant(c, y);
			y.left = c.left;
			y.left.p = y;
		} else if (c.right != null){ // cases when only a child node is empty
			this.transplant(c, c.right);
		} else if (c.left != null){
			this.transplant(c, c.left);
		}
		}
	}

	@Override
	public boolean lookup(T x) {
		TNode<T> c = this.root;
		Boolean check = false;
		while (c != null){
			if (c.data == x){
				check = true;
				break;
			}
			if (x.compareTo(c.data)<0){
				c = c.left;
			} else {
				c = c.right;
			}
		}
		return check;
	}
	
	public TNode<T> get(T x){
		TNode<T> c = this.root;
		while (c != null){
			if (c.data == x){
				break;
			}
			if (x.compareTo(c.data)<0){
				c = c.left;
			} else {
				c = c.right;
			}
		}
		return c;
	}

	@Override
	public void printPreOrder() {
		if (root != null){
			root.printPreOrder();
		} else {
			System.out.print("null");
		}
	}

	@Override
	public void printInOrder() {
		if (root != null){
			root.printInOrder();
		} else {
			System.out.print("null");
		}
	}

	@Override
	public void printPostOrder() {
		if (root != null){
			root.printPostOrder();
		} else {
			System.out.print("null");
		}
	}
	
	public String strDataPreOrder() {
		if (root != null){
			String str = root.strDataPreOrder();
			int l = str.length();
			str = str.substring(0, l-1);
			return str;
		} else {
			System.out.print("null");
			return null;
		}
	}
	public String strStructurePreOrder() {
		if (root != null){
			return root.strStructurePreOrder();
		} else {
			System.out.print("null");
			return null;
		}
	}
}
