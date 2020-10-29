import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener{
	private Graphic gph = new Graphic("", 0, false);

	private int check = 0;
	private int scale = 0;
	private String strt = "";
	private String end = "";
	protected Image imgPlay;
	protected Image imgBG;

	Canvas(Graphic g, String s, String e, int check){
		this.gph = g;
		this.strt = s;
		this.end = e;
		this.check = check;
		addMouseListener(this);
		try {

			imgPlay = ImageIO.read(new File("play2.png"));

			imgBG = ImageIO.read(new File("background.png"));

		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	Canvas(Graphic g, String s, int check){
		this.gph = g;
		this.strt = s;
		this.check = check;
		addMouseListener(this);
		try {

			imgPlay = ImageIO.read(new File("play2.png"));

			imgBG = ImageIO.read(new File("background.png"));

		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	Canvas(Graphic g, int check){
		this(g, "", "", check);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		HashMap<String, Edge> edge = gph.edgeList();

		if (gph.name().equals("ur.txt")){
			scale = 30000;
		} else if (gph.name().equals("monroe.txt")){
			scale = 500;
		} else if (gph.name().equals("nys.txt")){
			scale = 45;
		}
		int h = getHeight();
		int w = getWidth();
		g.drawImage(imgBG, 0, 0, w, h, this);
		
		double maxLat = gph.getMaxY();
		double latRad = maxLat*Math.PI/180.0;
		double m = Math.log(Math.tan(Math.PI/4.0+latRad/2.0));
		double minY = (h/2.0)-(h*m/(2*Math.PI));
		
		double minLon = gph.getMinX();
		double minLat = gph.getMinY();
		double minX = (minLon+180)*(w/360.0);
		latRad = minLat*Math.PI/180.0;
		m = Math.log(Math.tan(Math.PI/4.0+latRad/2.0));

		for (Edge tmp : edge.values()){
			double lat1 = tmp.vertex1.y;
			double lon1 = tmp.vertex1.x;
			double lat2 = tmp.vertex2.y;
			double lon2 = tmp.vertex2.x;
			
			double x1 = lon1*w;
			double x2 = lon2*w;
			
			double y1 = (h/2.0)-(h*lat1);
			double y2 = (h/2.0)-(h*lat2);
			y1=(y1-minY)*scale+20;
			y2=(y2-minY)*scale+20;
			x1=(x1-minX)*scale+20;
			x2=(x2-minX)*scale+20;
			g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}

			imgW = (int)(w/10.0);
			imgH = (int)(h/20.0);
			playX = (int)(w/2.0)-imgW/2;
			playY = (int)(h-20)-imgH;
			nameX = 20;
			nameY = playY;
			coorX = nameX;
			coorY = playY-15;
		if (check!=0){
			g.drawImage(imgPlay, playX, playY, imgW, imgH, this);
		}
	}
	private int imgW, imgH, playX, playY;
	
	public static Graphic createFromFile(String filename) {
		File inputFile = new File(filename);
		Graphic g = new Graphic(filename, 0, false);
	    try {
		       BufferedReader br = new BufferedReader(new FileReader(inputFile));
		       String inputsText = br.readLine();
		       int num = 1;
		       while ((inputsText = br.readLine()) != null) { // get the total number of vertices (intersections)
		    	   char x = inputsText.charAt(0);
		    	   if (x == 'i'){
		    		   num++;
		    	   }
		       }
		       Graphic tmp = new Graphic(filename, num, false);
		       g = tmp;
		       num = 0;
		       br = new BufferedReader(new FileReader(inputFile));
		       double maxY = 0;
		       double maxX = 0;
		       double minY = 1000000;
		       double minX = 1000000;
		       while ((inputsText = br.readLine()) != null) {
		    	   String[] words=inputsText.split("\\s+");
		    	   if (words[0].equals("i")){ // set up all vertices (intersections)
		    		   String name = words[1];
		    		   double y = Double.parseDouble(words[2]);
		    		   double x = Double.parseDouble(words[3]);
		    		   if (y>maxY)
		    			   maxY=y;
		    		   if (y<minY)
		    			   minY=y;
		    		   if (x>maxX)
		    			   maxX=x;
		    		   if (x<minX)
		    			   minX=x;
		    		   int max = Integer.MAX_VALUE;
		    		   Vertex v = new Vertex(num+1, max, false, name, y, x);
		    		   g.putVertex(v);
		    		   num++;
		    	   } else if (words[0].equals("r")){ // set up all edges (roads)
		    		   String name = words[1];
		    		   Vertex vertex1 = g.getVertex(words[2]);
		    		   Vertex vertex2 = g.getVertex(words[3]);
		    		   // calculating the distance between two intersections
		    		   double dist = Math.pow(Math.pow(vertex1.getX()-vertex2.getX(), 2)+Math.pow(vertex1.getY()-vertex2.getY(), 2), 1.0/2.0);
		    		   Edge e = new Edge(name, vertex1, vertex2, g, dist);
		    		   g.addEdge(e);
		    	   } else {
		    		   System.out.println("Invalid expression.");
		    	   }
		       }
		       g.setMaxMin(maxX, maxY, minX, minY);
		       } catch (IOException e) {
		    	   System.out.println("IOException");
		       }
	    return g;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() >= playX && e.getX() <= playX+imgW && e.getY() >= playY && e.getY() <= playY+imgH){
		
		//HashMap<String, Edge> edge = gph.edgeList();

		if (gph.name().equals("ur.txt")){
			scale = 30000;
		} else if (gph.name().equals("monroe.txt")){
			scale = 500;
		} else if (gph.name().equals("nys.txt")){
			scale = 45;
		}
		int h = getHeight();
		int w = getWidth();
		//System.out.println("paintComponent");
		//int count = 0;
		
		//double maxLon = gph.getMaxX();
		double maxLat = gph.getMaxY();
		double latRad = maxLat*Math.PI/180.0;
		double m = Math.log(Math.tan(Math.PI/4.0+latRad/2.0));
		double minY = (h/2.0)-(h*m/(2*Math.PI));
		
		double minLon = gph.getMinX();
		double minLat = gph.getMinY();
		double minX = (minLon+180)*(w/360.0);
		latRad = minLat*Math.PI/180.0;
		m = Math.log(Math.tan(Math.PI/4.0+latRad/2.0));
		
		if (check==1){
			Graphics g = getGraphics();
			LinkedList<Vertex> path = gph.dijkstra(strt, end);
			Vertex v1 = path.get(0);
			Vertex v2 = path.get(path.size()-1);
			
			for (int i = 0; i < path.size()-1; i++){
				Vertex tmp = path.get(i);
				Vertex tmpPrev = path.get(i+1);
				if (!tmpPrev.name.equals(v1.name) || !tmp.name.equals(v2.name)){
					double lat1 = tmp.y;
					double lon1 = tmp.x;
					double lat2 = tmpPrev.y;
					double lon2 = tmpPrev.x;
					
					double x1 = lon1*w;
					double x2 = lon2*w;
					
					double y1 = (h/2.0)-(h*lat1);
					double y2 = (h/2.0)-(h*lat2);
					y1=(y1-minY)*scale+20;
					y2=(y2-minY)*scale+20;
					x1=(x1-minX)*scale+20;
					x2=(x2-minX)*scale+20;
					g.setColor(Color.RED);
					g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
				}
			}
		}
		if (check==2){
			Graphics g = getGraphics();
			ArrayList<Edge> path = gph.kruskal(strt, gph.vertices());

			for (int i = 0; i < path.size(); i++){
				Edge t = path.get(i);
				Vertex tmp = t.vertex1;
				Vertex tmpPrev = t.vertex2;
				double lat1 = tmp.y;
				double lon1 = tmp.x;
				double lat2 = tmpPrev.y;
				double lon2 = tmpPrev.x;
				
				double x1 = lon1*w;
				double x2 = lon2*w;
				
				double y1 = (h/2.0)-(h*lat1);
				double y2 = (h/2.0)-(h*lat2);
				y1=(y1-minY)*scale+20;
				y2=(y2-minY)*scale+20;
				x1=(x1-minX)*scale+20;
				x2=(x2-minX)*scale+20;
				g.setColor(Color.RED);
				g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
				}
			}
		} else {
			System.out.println("mousePressed");
			// TODO Auto-generated method stub
			Graphics g = getGraphics();
			double x = e.getX();
			double y = e.getY();
			String coor = Double.toString(x)+", "+Double.toString(y);
			g.drawString(coor, (int)coorX, (int)coorY);
			HashMap<Double, Vertex> vert = gph.getVertexX();
			if (vert.containsKey(x)){
				Vertex v = vert.get(x);
				g.drawString(v.name, (int)nameX, (int)nameY);
			}
			//repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private double coorX = 0;
	private double coorY = 0;
	private double nameX = 0;
	private double nameY = 0;
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		if (args.length<2){
			System.out.println("Invalid input");
		} else {
			
		String filename = args[0];
		
		Graphic g = createFromFile(filename);
		int check = 1;

		String s = "";
		String e = "";

		JFrame frame=new JFrame();
		Canvas canvas = null;
		if (args.length==5){
			check=1;
			s = args[3];
			e = args[4].substring(0, args[4].length()-1);
			Canvas tmp = new Canvas(g, s, e, check);
			canvas=tmp;
		} else if (args.length==3){
			check=2;
			Canvas tmp =new Canvas(g, "SUEB", check);
			canvas=tmp;
		} else {
			check = 0;
			Canvas tmp = new Canvas(g, check);
			canvas=tmp;
		}
		frame.add(canvas);
		frame.setSize(900, 900);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		}
	}


}
