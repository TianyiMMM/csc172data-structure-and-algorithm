import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab15Graphics
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
		for (int i = 0; i < numVerticies; i++){
			adj[i][i]=false;
			Vertex v = new Vertex(i, Integer.MAX_VALUE, false);
			vert.add(v);
		}
	} 
	public boolean isDirected() { 
			// your code here 
		return directed;
	} 
	public void setDirected(boolean directed){
		this.directed = directed;
	}
	public int vertices() { 
		// return the number of vertices 
		return vertexCount;
	} 
	public void setVertices(int n){
		vertexCount = n;
	}
	public int edges() { 
		// return number of edges 
		return edgeCount;
	} 
	public Vertex getVertex(int val){
		for (Vertex tmp : vert){
			if (tmp.val == val){
				return tmp;
			}
		}
		return null;
	}
	public void insert(Edge e) { 
		// your code here
		//if (connected(e.v, e.w)==false){
		int v = e.v-1;
		int w = e.w-1;
			if (isDirected()){
				adj[v][w] = true;
			} else {
				adj[v][w] = true;
				adj[w][v] = true;
			}
			edgeCount++;
		//}
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
		// write the code for the constructors 
		// save the value of the vertex passed in 
		// (that will be where the iterator starts) 
		// start the “i” counter at negative one 
		}
		public int next() { 
			// perhaps the trickiest method 
			// use a for loop to advance the value of “i” 
			// “for (++i; i < vertices(); i++)” 
			// and search the appropriate row return the index 
			// of the next true value found 
			// “if (connected(v,i) == true) return i;” 
			// if the loop completes without finding anything return -1
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
	
	public void show () { 
		for (int s = 0; s < vertices(); s++) { 
			System.out.print((s+1) + ": "); 
			AdjList A = getAdjList(s); 
			for (int t = A.begin(); !A.end(); t = A.next()){ // use of iterator
				System.out.print((t+1) + " "); 
				//System.out.println();
			}
			System.out.println();
		}
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
	
	
	public static Graphic createFromFile(String filename) {
		File inputFile = new File(filename);
		Graphic g = new Graphic(0, false);
	    try {
		       BufferedReader br = new BufferedReader(new FileReader(inputFile));
		       String inputsText = br.readLine();
		       int numVertices = Integer.parseInt(inputsText);
		       inputsText = br.readLine();
		       Boolean isDirected = false;
		       if (inputsText == "D"){
		    	   isDirected = true;
		       }
		       g.setVertices(numVertices);
		       g.setDirected(isDirected);
		       while ((inputsText = br.readLine()) != null) {
		    	   String[] words=br.readLine().split(" ");
		    	   int a = Integer.parseInt(words[0]);
		    	   int b = Integer.parseInt(words[1]);
		    	   Edge e = new Edge(a, b);
		    	   g.insert(e);
		       }
		       br.close();
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       }
	    return g;
	}
	
	public void unweighted (int s) { 
		/*
		for (int i = 0; i < vertexCount; i++) {
			v.dist = Integer.MAX_VALUE;
			v.known = false; 
		}
		*/
		Vertex tmpS = getVertex(s);
		tmpS.dist = 0;
		for (int currdist = 0; currdist < vertexCount; currdist++) { 
			for (int i = 0; i < vertexCount; i++){
				Vertex v = getVertex(i);
				if (!v.known && v.dist == currdist) { 
					v.known = true;
					AdjList A = getAdjList(v.val); 
					for (int t = A.begin(); !A.end(); t = A.next()){
						Vertex w = getVertex(t+1);
						if(w.dist == Integer.MAX_VALUE) { 
							w.dist = currdist + 1; 
							w.path = v;
						} 
					}
				}
			}
		}
		//for ()
	}
}


