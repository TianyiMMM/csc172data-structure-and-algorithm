import java.util.Scanner;

public class Test{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTline2 tree = new BSTline2();

		Scanner in  = new Scanner(System.in);
		
		System.out.print("Input the number of the lines: ");
		int num = in.nextInt();
		
		for (int i = 1; i <= num; i++){
			System.out.print("Input the four coordinates of line " + i + " : ");
			double x1 = in.nextDouble();
			double y1 = in.nextDouble();
			double x2 = in.nextDouble();
			double y2 = in.nextDouble();
			tree.insert(x1, y1, x2, y2);
		}

		System.out.print("Input the number of pairs of points you want to evaluate: ");
		num = in.nextInt();
		for (int i = 1; i <= num; i++){
			System.out.print("Input the four coordinates of the pairs of points: ");
			double x1 = in.nextDouble();
			double y1 = in.nextDouble();
			double x2 = in.nextDouble();
			double y2 = in.nextDouble();
			tree.checkRegion(x1, y1, x2, y2);
		}
		
	}

}
