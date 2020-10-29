/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Project3PointLocation
 */

public class BSTline {
	public class TNode<Line> { 
		public Line data; 
		public TNode<Line> left; 
		public TNode<Line> right; 
		public TNode<Line> p;
		
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
	
	public void insert(double x1, double y1, double x2, double y2) {
		Line x = new Line(x1, y1, x2, y2);
		TNode<Line> newNode = new TNode<Line>();
		newNode.data = x;
		TNode<Line> c = this.root;
		TNode<Line> y = c;
		while (c != null){
			if (intersect(x, c.data)==false){ // if the new line doesn't intersect line c
				y = c;
				if (compare(newNode.data, c.data)==-1){
					c = c.left;
				} else {
					c = c.right;
				}
			} else {
				insert(-1, c.left, c, x1, y1, x2, y2);
				insert(1, c.right, c, x1, y1, x2, y2);
				c = null;
			}
			newNode.p = y;
		}
		if (root == null){
			root = newNode;
		} else if (compare(newNode.data, y.data)==-1){
			y.left = newNode;
		} else {
			y.right = newNode;
		}
	}
	
	public void insert(int clock, TNode<Line> start, TNode<Line> startprev, double x1, double y1, double x2, double y2) {

		Line x = new Line(x1, y1, x2, y2);
		TNode<Line> newNode = new TNode<Line>();
		newNode.data = x;
		TNode<Line> c = start;
		TNode<Line> y = startprev;
		while (c != null){
			if (intersect(x, c.data)==false){ // if the new line doesn't intersect line c
				y = c;
				if (compare(newNode.data, c.data)==-1){
					c = c.left;
				} else {
					c = c.right;
				}
			} else {
				insert(-1, c.left, c, x1, y1, x2, y2);
				insert(1, c.right, c, x1, y1, x2, y2);
			}
		}
		newNode.p = y;
		if (compare(newNode.data, y.data)==0){
			if (clock==-1)
				y.left = newNode;
			else if (clock==1)
				y.right = newNode;
		} else if (compare(newNode.data, y.data)==-1){
			y.left = newNode;
		} else {
			y.right = newNode;
		}
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
		if (ccw(l1.p1, l2.p1, l2.p2) == 0 || ccw(l1.p2, l2.p1, l2.p2) == 0){
			rtr = 2; 
		} else if (ccw(l1.p1, l2.p1, l2.p2) == ccw(l1.p2, l2.p1, l2.p2)){ // if the line doesn't intersect
			rtr = ccw(l1.p1, l2.p1, l2.p2); // return whether the line is on one side of l2 or the other
			
		}
		return rtr;
	}
	
	// check if two line intersect
	public boolean intersect(Line l1, Line l2){
		return ccw(l1.p1, l2.p1, l2.p2) + ccw(l1.p2, l2.p1, l2.p2)==0;
	}
	
	// test whether a point is on one side of a line or the other
	// -1 == Counter Clockwise
	// 1 == Clockwise
	// 0 == p0 is on the line
	public int ccw(Point p0, Point p1, Point p2) { 
		double dx1 = p1.x - p0.x; 
		double dy1 = p1.y - p0.y; 
		double dx2 = p2.x - p0.x; 
		double dy2 = p2.y - p0.y; 

		if (dx1*dy2 > dy1*dx2){
			return -1; 
		}else if (dx1*dy2 < dy1*dx2) {
			return 1; 
		}else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) {
			return 1; 
		}else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) {
			return -1; 
		}else {
			return 0;
		}
	}
	
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
	}
	
	public void printLine(Line l){
		System.out.println("("+l.p1.x+", "+l.p1.y+") ("+l.p2.x+", "+l.p2.y+")");
	}
	
}
