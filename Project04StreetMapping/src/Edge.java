
public class Edge{
	public int v, w; // an edge from v to w 
	public String name;
	public Vertex vertex1, vertex2;
	public double dist;
	public Edge(String name, Vertex vertex1, Vertex vertex2, Graphic g, double dist) { // your code here 
		v = vertex1.val;
		w = vertex2.val;
		this.name = name;
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.dist = dist;
	} 
}
