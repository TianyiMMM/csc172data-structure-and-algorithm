
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel implements MouseListener{
	
	public void paintComponent(Graphics g){
		g.drawLine(0, 264, 69, 0);
		g.drawLine(300, 177, 0, 144);
		g.drawLine(300, 240, 90, 0);
		g.drawLine(0, 180, 30, 300);
		g.drawLine(300, 195, 30, 0);
	}
	
	public Canvas(){
		addMouseListener(this);
	}
	
	private static BSTline tree = new BSTline();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTline tree2 = new BSTline();
		tree = tree2;
		tree.insert(0.00, 0.12, 0.23, 1);
		tree.insert(1.0, 0.41, 0.0, 0.52);
		tree.insert(1.0, 0.2, 0.3, 1.0);
		tree.insert(0.0, 0.4, 0.1, 0.0);
		//tree.insert(1.0, 0.35, 0.1, 1.0);
		
		
		JFrame frame=new JFrame();
		Canvas canvas=new Canvas();
		frame.add(canvas);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int count=0;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		count ++;
		Graphics g = getGraphics();
		g.fillOval(e.getX()-2, e.getY()-2, 4, 4);
		if (count==1){
			x1 = e.getX()/300.0;
			y1 = (1-e.getY()/300.0);
		} else if (count==2){
			x2 = e.getX()/300.0;
			y2 = (1-e.getY()/300.0);
			tree.checkRegion(x1, y1, x2, y2);
			count = 0;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
