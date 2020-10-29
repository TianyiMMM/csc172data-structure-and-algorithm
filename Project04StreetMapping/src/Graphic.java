
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Project04StreetMapping
 */

public class Graphic {
	private int vertexCount, edgeCount; 
	boolean directed; // false for undirected graphs, true for directed 
	private HashMap<String, Vertex> vert;
	private HashMap<Integer, Vertex> vertInt;
	private HashMap<Double, Vertex> vertX;
	private HashMap<String, Vertex> vertUnk;
	private HashMap<String, Vertex> vertUnkVal;
	private HashMap<String, Edge> edge;
	private LinkedList<Vertex> path = new LinkedList<Vertex>();
	private HashMap<String, LinkedList<Integer>> adjt;
	private double maxX, minX, maxY, minY;
	private String name;

	public Graphic(String name, int numVerticies, boolean isDirected) { 
		this.name = name;
		vertexCount = numVerticies;
		directed = isDirected;
		edgeCount = 0;

		adjt = new HashMap<String, LinkedList<Integer>>(numVerticies);
		edge = new HashMap<String, Edge>(numVerticies);
		vert = new HashMap<String, Vertex>(numVerticies);
		vertX = new HashMap<Double, Vertex>(numVerticies);
		vertUnk = new HashMap<String, Vertex>(numVerticies);
		vertUnkVal = new HashMap<String, Vertex>(numVerticies);
		vertInt = new HashMap<Integer, Vertex>(numVerticies);

	} 
	public String name(){
		return name;
	}
	public boolean isDirected() { 
			// your code here 
		return directed;
	} 
	public int vertices() {
		// return the number of vertices 
		return vertexCount;
	} 
	public int edges() { 
		// return number of edges 
		return edgeCount;
	} 
	public void addEdge(Edge e){
		Vertex v = e.vertex1;
		Vertex u = e.vertex2;
		LinkedList<Integer> adj1 = adjt.get(v.name);
		LinkedList<Integer> adj2 = adjt.get(u.name);
		if (adj1.get(0)==0){
			adj1.remove(0);
		}
		if (adj2.get(0)==0){
			adj2.remove(0);
		}
		if (!adj1.contains(u.val-1))
			adj1.add(u.val-1);
		if (!adj2.contains(v.val-1))
			adj2.add(v.val-1);
		adjt.replace(v.name, adj1);
		adjt.replace(u.name, adj2);
		edge.put(e.name, e);
	}
	public HashMap<String, Vertex> verticesList(){
		return vert;
	}
	public HashMap<String, Edge> edgeList(){
		return edge;
	}
	public void setMaxMin(double maxX, double maxY, double minX, double minY){
		this.maxX=maxX;
		this.maxY=maxY;
		this.minX=minX;
		this.minY=minY;
	}
	public double getMaxX(){
		return maxX;
	}
	public double getMaxY(){
		return maxY;
	}
	public double getMinX(){
		return minX;
	}
	public double getMinY(){
		return minY;
	}

	public Vertex getVertex(int val){
		Vertex tmp2 = null;
		System.out.println("getVertex - val: "+val);
		for (int i = 0; i < vertexCount; i++){
		for (Vertex tmp : vert.values()){
			if (tmp.val == val){
				System.out.println("getVertex - tmp.val: "+tmp.val);
				tmp2 = tmp;
				System.out.println("getVertex - tmp.val: "+tmp.val);
				System.out.println("getVertex - tmp2.val: "+tmp2.val);
			}
		}
		}
		return tmp2;
	}
	public HashMap<Double, Vertex> getVertexX(){
		return vertX;
	}
	public void putVertex(Vertex v){
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(0);
		vertUnk.put(v.name, v);
		vert.put(v.name, v);
		vertInt.put(v.val, v);
		adjt.put(v.name, l);
		vertX.put(v.x, v);
	}
	public Vertex getVertex(String name){
		return vert.get(name);
	}
	
	public boolean connected(String node1, int node2) { 
		return adjt.get(node1).contains(node2);
	} 
	
	public LinkedList<Integer> getAdjList(String name){
		return adjt.get(name);
	}
	
	private double dist = 0;
	private Vertex end = null;
	
	public LinkedList<Vertex> dijkstra(String s, String e){
		//try {
		Vertex strt = vert.get(s);
		strt.dist=0;
		vertUnkVal.put(strt.name, strt);
		while(vertUnk.size()!=0){
			Vertex v = min(vertUnkVal);
			v.known=true;
			//vertUnk.remove(v.name);
			vertUnkVal.remove(v.name);
			//System.out.println("vertUnkVal.size(): "+vertUnkVal.size());
			//System.out.println("vertUnk.size(): "+vertUnk.size());
			//vertKn.put(v.name, v);
			LinkedList<Integer> A = getAdjList(v.name); 
			for (int t : A){
				Vertex w = vertInt.get(t+1);
				if(!w.known) {
					double cvw = Math.pow(Math.pow(v.getX()-w.getX(), 2)+Math.pow(v.getY()-w.getY(), 2), 1.0/2.0);
					if (v.dist+cvw<w.dist){
						w.dist=v.dist+cvw;
						Vertex p = vert.get(v.name);
						w.path=p;
						if (vertUnkVal.containsKey(w.name))
							vertUnkVal.replace(w.name, w);
						else
							vertUnkVal.put(w.name, w);
						if (w.name.equals(e)){
							dist = w.dist;
							end = w;
							vertUnk.clear();
							break;
						}
					}
				} 
			}
		}
		printPath(end);
		double r = 6371; // radius of earth: 6371km
		System.out.println("Distance travled: "+r*dist+"km");
		//} catch (Exception n){
			//System.out.println("Sorry, this two intersections can't be connected with each other");
		//}
		return path;
	}
	
	
	public ArrayList<Edge> kruskal(String strt, int numVertices){
		DisjSets ds = new DisjSets(numVertices);
		HashMap<String, Edge> tmp = edge;
		ArrayList<Edge> mst = new ArrayList<>( );

		while(mst.size()!=numVertices-1){

			Edge e = minEdge(tmp);
			tmp.remove(e.name);
			int u = ds.find(e.vertex1.val-1);
			int v = ds.find(e.vertex2.val-1);
			
			if (u!=v){
				mst.add(e);
				ds.union(u, v);
			}
		}
		
		for (Edge e : mst){
			Vertex v = e.vertex1;
			Vertex u = e.vertex2;
			System.out.println("Road "+e.name+": " + v.name + " to " + u.name);
		}
		
		return mst;
	}
	
	public Edge minEdge(HashMap<String, Edge> edge){
		Vertex v1 = new Vertex(1, Integer.MAX_VALUE, false, "", 0, 0);
		Vertex v2 = new Vertex(1, Integer.MAX_VALUE, false, "", 0, 0);
		Edge min = new Edge("", v1, v2, this, (double)Integer.MAX_VALUE);
		for (Edge tmp : edge.values()){
			if (tmp.dist<min.dist){
				min.dist=tmp.dist;
				min.name=tmp.name;
				min.vertex1=tmp.vertex1;
				min.vertex2=tmp.vertex2;
				min.v=tmp.v;
				min.w=tmp.w;
			}
		}
		return min;
	}
	
	public Vertex min(HashMap<String, Vertex> vertUnk){
		Vertex min = new Vertex(0, Integer.MAX_VALUE, false, "", 0, 0);
		for (Vertex tmp : vertUnk.values()){
			if (tmp.dist<min.dist){
				min.dist=tmp.dist;
				min.val=tmp.val;
				min.known=tmp.known;
				min.name=tmp.name;
				min.x=tmp.x;
				min.y=tmp.y;
			}
		}
		return min;
	}
	
	public void show () { 
		for (int s = 0; s < vertices(); s++) { 
			System.out.print((s+1) + ": ");
			LinkedList<Integer> A = getAdjList(vertInt.get(s+1).name); 
			if (A!=null){
			for (int t : A){ // use of iterator
				System.out.print((t+1) + " "); 
			}
			System.out.println();
			}
		}
		System.out.println();
	}

	private int count = 0;
	public void printPath(Vertex w){
		Vertex tmp = w;
		if (tmp!=null){
			printPath(tmp.path);
			path.add(tmp);
			if (tmp.val != this.end.val){
				count++;
				System.out.println(count+". "+tmp.name);
			}else{
				count++;
				System.out.println(count+". "+tmp.name);
			}
		}
	}

}


