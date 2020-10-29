
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graphic g = new Graphic(7, true);
		Edge e1 = new Edge(1, 2);
		Edge e2 = new Edge(1, 4);
		Edge e3 = new Edge(1, 3);
		Edge e4 = new Edge(2, 4);
		Edge e5 = new Edge(2, 5);
		Edge e6 = new Edge(3, 6);
		Edge e7 = new Edge(4, 6);
		Edge e8 = new Edge(4, 7);
		Edge e9 = new Edge(5, 4);
		Edge e10 = new Edge(5, 7);
		Edge e11 = new Edge(7, 6);
		g.insert(e1);
		g.insert(e2);
		g.insert(e3);
		g.insert(e4);
		g.insert(e5);
		g.insert(e6);
		g.insert(e7);
		g.insert(e8);
		g.insert(e9);
		g.insert(e10);
		g.insert(e11);
		g.show();
		
		System.out.println();
		
		Graphic gg = new Graphic(5, false);
		Edge ee1 = new Edge(1, 2);
		Edge ee2 = new Edge(1, 4);
		Edge ee3 = new Edge(1, 5);
		Edge ee4 = new Edge(2, 4);
		Edge ee5 = new Edge(2, 3);
		Edge ee6 = new Edge(5, 1);
		Edge ee7 = new Edge(5, 3);
		gg.insert(ee1);
		gg.insert(ee2);
		gg.insert(ee3);
		gg.insert(ee4);
		gg.insert(ee5);
		gg.insert(ee6);
		gg.insert(ee7);
		gg.show();
	}

}
