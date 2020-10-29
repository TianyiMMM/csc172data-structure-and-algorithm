
public class Vertex {
	public int val;
	public double lat, lon, y, x, dist;
	public String name;
	public boolean known;
	public Vertex path;
	public Vertex(int val, double dist, boolean known, String name, double lat, double lon){
		this.val = val;
		this.dist = dist;
		this.known = known;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		x = (lon+180.0)/(360.0);
		double latRad = lat*Math.PI/180.0;
		double m = Math.log(Math.tan(Math.PI/4.0+latRad/2.0));
		y = m/(2*Math.PI);
	}
	public double getY(){
		return y;
	}
	public double getX(){
		return x;
	}
	public Vertex path(){
		return path;
	}
	public String name(){
		return name;
	}
}
