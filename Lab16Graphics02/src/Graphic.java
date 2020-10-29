import java.util.ArrayList;

/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab16Graphics02
 */

public class Graphic {
	private int vertexCount, edgeCount; 
	boolean directed; // false for undirected graphs, true for directed 
	private boolean adj[][];
	private ArrayList<Vertex> vert = new ArrayList<Vertex>();

	public Graphic(int numVerticies, boolean isDirected) { 
		vertexCount = numVerticies;
		directed = isDirected;
		// your code here 
		edgeCount = 0;
		adj = new boolean [numVerticies][numVerticies];
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < numVerticies; i++){
			adj[i][i]=false;
			Vertex v = new Vertex(i+1, max, false);
			vert.add(v);
		}
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
	public Vertex getVertex(int val){
		Vertex tmp2 = null;
		for (Vertex tmp : vert){
			if (tmp.val == val){
				tmp2 = tmp;
			}
		}
		//System.out.println("getVertex");
		//printVertex(tmp2);
		return tmp2;
	}
	public void printVertex(){
		for (Vertex tmp : vert){
			System.out.print(tmp.val + ", " + tmp.dist + ", " + tmp.known + ", "+ tmp.path);
			System.out.println();
		}
		System.out.println();
	}
	public void printVertex(Vertex v){
		System.out.println("printing");
		Vertex tmp = v;
		System.out.print(tmp.val + ", " + tmp.dist + ", " + tmp.known + ", "+ tmp.path);
		System.out.println();
	}
	public void insert(Edge e) { 
		// your code here
		int v = e.v-1;
		int w = e.w-1;
			if (isDirected()==true){
				adj[v][w] = true;
			} else {
				adj[v][w] = true;
				adj[w][v] = true;
			}
			edgeCount++;
	} 
	public void delete(Edge e) { 
		// your code here 
		int v = e.v-1;
		int w = e.w-1;
		if (isDirected()){
			adj[v][w] = false;
		} else {
			adj[v][w] = false;
			adj[v][w] = false;
		}
		edgeCount--;
	} 
	public boolean connected(int node1, int node2) { 
		return adj[node1][node2];
	} 
	public AdjList getAdjList(int vertex) { 
		// your code here
		return new AdjArray(vertex);
	}
	
	
	interface AdjList { 
		int begin();
		int next(); 
		boolean end(); 
	}
	
	private class AdjArray implements AdjList { 
		private int v; // what vertex we are interested in 
		private int i; // so we can keep track of where we are
	
		public AdjArray(int v) { 
			this.v = v;
			i = -1;
		}
		public int next() { 
			for (++i; i < vertices(); i++){
				if (connected(v, i)){
					return i;
				}
			}
			return -1;
		}

		public int begin() { 
			// reset “i” back to negative one 
			// return the value of a call to “next” 
			i = -1;
			return next();
		} 
		public boolean end() {
			if (i < vertices())
				return false;
			else
				return true;
			// if “i” is less than the number of vertices return false 
		}

	}
	private int cc = 0;
	public void show () { 
		for (int s = 0; s < vertices(); s++) { 
			System.out.print((s+1) + ": "); 
			AdjList A = getAdjList(s); 
			for (int t = A.begin(); !A.end(); t = A.next()){ // use of iterator
				System.out.print((t+1) + " "); 
				//cc++;
			}
			System.out.println();
		}
		//System.out.println("edges: "+cc);
		System.out.println("edges: "+edges());
		System.out.println();
	}
	
	public class Vertex{
		int val;
		int dist;
		boolean known;
		Vertex path;
		public Vertex(int val, int dist, boolean known){
			this.val = val;
			this.dist = dist;
			this.known = known;
		}
		
	}
	
	private Vertex w = null;
	private int c = 0;
	public void unweighted (int s) { 
		Vertex tmpS = getVertex(s);
		tmpS.dist = 0;
		for (int currdist = 0; currdist < vertexCount; currdist++) { 
			for (int i = 0; i < vertexCount; i++){
				Vertex v = getVertex(i+1);
				c++;
				if (!v.known && v.dist == currdist) { 
					v.known = true;
					AdjList A = getAdjList(v.val-1); 
					for (int t = A.begin(); !A.end(); t = A.next()){
						Vertex w = getVertex(t+1);
						if(w.dist == Integer.MAX_VALUE) {
							this.w = w;
							w.dist = currdist + 1; 
							w.path = v;
						} 
					}
				}
			}
		}
		printPath(w);
		this.w = null;
		int max = Integer.MAX_VALUE;
		count=0;
		for (Vertex tmp : vert){
			tmp.dist = max;
			tmp.known = false;
			tmp.path = null;
		}
	}
	public void printC(){
		System.out.println("Runtime: "+c);
		c=0;
	}
	private int count = 0;
	public void printPath(Vertex w){
		Vertex tmp = w;
		if (tmp!=null){
			count++;
			printPath(tmp.path);
			if (tmp.val != this.w.val)
				System.out.print(tmp.val + " to ");
			else
				System.out.println(tmp.val);
		} else if (count==0){
			System.out.println("No adjacent vertex: try again");
		}
	}
}


