
/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Project3PointLocation
 */

public class BSTline2 {
	public class TNode<Line> { 
		public Line data; 
		public TNode<Line> left; 
		public TNode<Line> right; 
		public TNode<Line> p;
		
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
	}
	
	// Point
	public class Point{
		public double x;
		public double y;
		
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	// Line
	public class Line{
		public Point p1;
		public Point p2;
		
		public Line(Point p1, Point p2){
			if (p1.x > p2.x){
				this.p1 = p2;
				this.p2 = p1;
			} else {
				this.p1 = p1;
				this.p2 = p2;
			}
		}
		
		public Line(double x1, double y1, double x2, double y2){
			Point p1 = new Point (x1, y1);
			Point p2 = new Point (x2, y2);
			if (p1.x > p2.x){
				this.p1 = p2;
				this.p2 = p1;
			} else {
				this.p1 = p1;
				this.p2 = p2;
			}
		}
	} // assume p1.x < p2.x
	
	private TNode<Line> root = null;
	private TNode<Line> parent = null;
	
	public void insert(double x1, double y1, double x2, double y2) {
		//System.out.println("inserting");
		//if (this.lookup(x)==false){
		Line x = new Line(x1, y1, x2, y2);
		TNode<Line> newNode = new TNode<Line>();
		newNode.data = x;
		TNode<Line> c = this.root;
		TNode<Line> y = c;
		while (c != null){
			System.out.println("insertion while loop");
			if (intersect(x, c.data)==false){ // if the new line doesn't intersect line c
				System.out.println("insertion: no intersection");
				//System.out.print("insertion: no intersection: c.data" );
				//printLine(c.data);
				//printLine(c.right.data);
				y = c;
				System.out.print("insertion: compare(newNode.data, c.data): "+compare(newNode.data, c.data) );
				if (compare(newNode.data, c.data)==-1){
					System.out.println("insertion: no intersection -- counterclockwise");
					//if (c.left != null){
					c = c.left;
					//}
				} else /*if (compare(newNode.data, c.data)==1)*/{
					System.out.println("insertion: no intersection -- clockwise");
					//if (c.right != null){
					c = c.right;
					//System.out.println(c.right);
					//}
				//} else if (compare(newNode.data, c.data)==2){
					//c = null;
				}
			} else {
				System.out.println("insertion: intersection");
				//parent = c;
				insert(-1, c.left, c, x1, y1, x2, y2);
				System.out.println("insertion: intersection -- left insertion complete");
				insert(1, c.right, c, x1, y1, x2, y2);
				System.out.println("insertion: intersection -- right insertion complete");
				System.out.println(c.left);
				System.out.println(c.right);
				c = null;
			}
			newNode.p = y;
		}
		//newNode.p = y;
		if (root == null){
			root = newNode;
			//System.out.println("insertion: root == null");
		} else if (compare(newNode.data, y.data)==-1){
			//System.out.println("insertion: root == counterclockwise");
			y.left = newNode;
		} else /*if (compare(newNode.data, y.data)==1)*/ {
			//System.out.println("insertion: root == clockwise");
			y.right = newNode;
		}
		System.out.println("insertion: complete");
		//}
	}
	
	public void insert(int clock, TNode<Line> start, TNode<Line> startprev, double x1, double y1, double x2, double y2) {
		//System.out.println("inserting");
		//if (this.lookup(x)==false){
		Line x = new Line(x1, y1, x2, y2);
		TNode<Line> newNode = new TNode<Line>();
		newNode.data = x;
		TNode<Line> c = start;
		TNode<Line> y = startprev;
		while (c != null){
			//System.out.println("insertion while loop");
			if (intersect(x, c.data)==false){ // if the new line doesn't intersect line c
				System.out.println("insertion2: no intersection");
				//System.out.print("insertion: no intersection: c.data" );
				//printLine(c.data);
				System.out.print("insertion2: compare(newNode.data, c.data): "+compare(newNode.data, c.data) );
				y = c;
				if (compare(newNode.data, c.data)==-1){
					System.out.println("insertion2: no intersection -- counterclockwise");
					c = c.left;
				} else /*if (compare(newNode.data, c.data)==1)*/{
					System.out.println("insertion2: no intersection -- clockwise");
					c = c.right;
				//} else if (compare(newNode.data, c.data)==2){
				//	c = null;
				}
			} else {
				System.out.println("insertion2: intersection");
				parent = c;
				insert(-1, c.left, c, x1, y1, x2, y2);
				insert(1, c.right, c, x1, y1, x2, y2);
			}
			//newNode.p = y;
		}
		newNode.p = y;
		//System.out.println(y.data);
		System.out.println("insertion22: start == compare: "+compare(newNode.data, y.data));
		System.out.print("insertion2: start == newNode.data: ");
		printLine(newNode.data);
		System.out.println("insertion2: start == y.data: ");
		printLine(y.data);
		if (compare(newNode.data, y.data)==0){
			System.out.println("insertion2: start == null");
			if (clock==-1)
				y.left = newNode;
			else if (clock==1)
				y.right = newNode;
		} else if (compare(newNode.data, y.data)==-1){
			System.out.println("insertion2: start == counterclockwise");
			y.left = newNode;
		} else if (compare(newNode.data, y.data)==1){
			System.out.println("insertion2: start == clockwise");
			y.right = newNode;
		}
		System.out.println("");
	}
	
	public boolean compare(Point p1, Point p2){
		if (p1.x==p2.x && p1.y==p2.y){
			return true;
		} else {
			return false;
		}
	}
	
	// l1 == the line that need to be classified (whether is the left or right child of l2)
	// l2 == the line that is compared to
	public int compare(Line l1, Line l2){
		int rtr = 0;
		//System.out.println("Compare: "+ccw(l1.p1, l2.p1, l2.p2));
		System.out.println("Compare: "+ccw(l1.p2, l2.p1, l2.p2));
		if (ccw(l1.p1, l2.p1, l2.p2) == 0 || ccw(l1.p2, l2.p1, l2.p2) == 0){
			System.out.println("compare(========) "+2);
			rtr = 2; 
		} else if (ccw(l1.p1, l2.p1, l2.p2) == ccw(l1.p2, l2.p1, l2.p2)){ // if the line doesn't intersect
			System.out.println("doesn't intersect "+ccw(l1.p1, l2.p1, l2.p2));
			System.out.println("doesn't intersect "+ccw(l1.p2, l2.p1, l2.p2));
			rtr = ccw(l1.p1, l2.p1, l2.p2); // return whether the line is on one side of l2 or the other
			
		}
		return rtr;
	}
	
	// check if two line intersect
	public boolean intersect(Line l1, Line l2){
		//System.out.println("Check intersect: "+ccw(l1.p1, l2.p1, l2.p2));
		//System.out.println("Check intersect: "+ccw(l1.p2, l2.p1, l2.p2));
		
		return ccw(l1.p1, l2.p1, l2.p2) + ccw(l1.p2, l2.p1, l2.p2)==0;
	}
	/*
	public void splitLine(Line l1, Line l2){
		// calculate the equation for l1 and l2
		double a1 = (l1.p2.y-l1.p1.y)/(l1.p2.x-l1.p1.x);
		double b1 = l1.p1.y - a1*l1.p1.x; 
		double a2 = (l2.p2.y-l2.p1.y)/(l2.p2.x-l2.p1.x);
		double b2 = l2.p1.y - a2*l2.p1.x;
		// find the coordinates of intersection point x0
		double x0 = -(b1-b2)/(a1-a2);
		double y0 = a2*x0 + b2;
		
		Point intrs = new Point(x0, y0);
		Line a = new Line(l1.p1, intrs);
		Line b = new Line(intrs, l1.p2);
		System.out.println("Check intersect point x: "+x0);
		System.out.println("Check intersect point y: "+y0);
		System.out.println("Check intersect point (splitLine): "+ccw(intrs, l2.p1, l2.p2));
		System.out.println("Check intersect a (splitLine): "+intersect(a, l2));
		System.out.println("Check intersect b (splitLine): "+intersect(b, l2));
		
		// insert the newly split line
		insert(l1.p1.x, l1.p1.y, intrs.x, intrs.y);
		insert(intrs.x, intrs.y, l1.p2.x, l1.p2.y);
	}
	*/
	
	// test whether a point is on one side of a line or the other
	// -1 == Counter Clockwise
	// 1 == Clockwise
	// 0 == p0 is on the line
	public int ccw(Point p0, Point p1, Point p2) { 
		double dx1 = p1.x - p0.x; 
		double dy1 = p1.y - p0.y; 
		double dx2 = p2.x - p0.x; 
		double dy2 = p2.y - p0.y; 
		/*
		System.out.println("x0: "+p0.x);
		System.out.println("y0: "+p0.y);
		System.out.println("p1.x: "+p1.x);
		System.out.println("p1.y: "+p1.y);
		System.out.println("p2.x: "+p2.x);
		System.out.println("p2.y: "+p2.y);
		
		System.out.println("dx1: "+dx1);
		System.out.println("dy1: "+dy1);
		System.out.println("dx2: "+dx2);
		System.out.println("dy2: "+dy2);
		/*System.out.println("y: "+y);
		System.out.println("p0.y: "+p0.y);
		*/
		System.out.println("dx1: "+dx1);
		System.out.println("dy1: "+dy1);
		System.out.println("dx1*dy2"+dx1*dy2);
		System.out.println("dx2: "+dx2);
		System.out.println("dy2: "+dy2);
		System.out.println("dy1*dy2"+dy1*dy2);
		if (dx1*dy2 > dy1*dx2){
			System.out.println("1==");
			return -1; 
		}else if (dx1*dy2 < dy1*dx2) {
			System.out.println("2==");
			return 1; 
		}else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) {
			System.out.println("3==");
			return 1; 
		}else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) {
			System.out.println("4==");
			return -1; 
		}else {
			return 0;
		}
	}
	/*
	public TNode<Line> getParent(Point p){
		TNode<Line> c = this.root;
		TNode<Line> cprev = c;
		while (c != null){
			cprev = c;
			printLine(cprev.data);
			System.out.println("getParent");
			if (ccw(p, c.data.p1, c.data.p2)<0){
				c = c.left;
			} else if (ccw(p, c.data.p1, c.data.p2)>0){
				c = c.right;
			}
		}
		return cprev;
	}
	public TNode<Line> getParent(Line p){
		TNode<Line> c = this.root;
		TNode<Line> cprev = c;
		while (c != null){
			cprev = c;
			printLine(cprev.data);
			System.out.println("g etParent");
			if (ccw(p, c.data.p1, c.data.p2)<0){
				c = c.left;
			} else if (ccw(p, c.data.p1, c.data.p2)>0){
				c = c.right;
			}
		}
		return cprev;
	}
	*/
	
	public void checkRegion(double x1, double y1, double x2, double y2){
		Point pp1 = new Point(x1, y1);
		Point pp2 = new Point(x2, y2);
		TNode<Line> c = this.root;
		Boolean check = false;
		while (c!=null){
			if (ccw(pp1, c.data.p1, c.data.p2)+ccw(pp2, c.data.p1, c.data.p2)==0){
				check = true;
				System.out.println("("+pp1.x+", "+pp1.y+"), ("+pp2.x+", "+pp2.y+"): at least one of the lines goes between the two points. ");
				System.out.print("One line: ");
				printLine(c.data);
				c = null;
			} else if (ccw(pp1, c.data.p1, c.data.p2)==-1){
				c = c.left;
			} else if (ccw(pp1, c.data.p1, c.data.p2)==1){
				c = c.right;
			}
		}
		if (check == false){
			System.out.println("("+pp1.x+", "+pp1.y+"), ("+pp2.x+", "+pp2.y+"): none of the lines goes between the two points. ");
		}
		/*
		TNode<Line> a = getParent(p1);
		TNode<Line> b = getParent(p2);
		
		if (compare(a.data, b.data)==0){
			if (ccw(p1, a.data.p1, a.data.p2)==ccw(p2, a.data.p1, a.data.p2)){
				System.out.println("("+p1.x+", "+p1.y+") ("+p2.x+", "+p2.y+"): none of the lines goes between the two points. ");
			}
		} else {
			System.out.println("("+p1.x+", "+p1.y+") ("+p2.x+", "+p2.y+"): at least one of the lines goes between the two points. ");
			System.out.print("One line: ");
			printLine(a.data);
		}
		*/
	}
	
	public void printLine(Line l){
		System.out.println("("+l.p1.x+", "+l.p1.y+") ("+l.p2.x+", "+l.p2.y+")");
	}
	
	public void printPreOrder() {
		if (root != null){
			root.printPreOrder();
		} else {
			System.out.print("null");
		}
	}
	
}
