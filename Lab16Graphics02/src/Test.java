import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Test {
	public static Graphic createFromFile(String filename) {
		File inputFile = new File(filename);
		Graphic g = new Graphic(0, false);
	    try {
		       BufferedReader br = new BufferedReader(new FileReader(inputFile));
		       String inputsText = br.readLine();
		    	   try {
		    		   int numVertices = Integer.parseInt(inputsText);
		    		   inputsText = br.readLine();
				       Boolean isDirected = false;
				       if (!inputsText.equals("D") && !inputsText.equals("U")){
				    	   System.out.println("Invalid Format: second line must be either 'U' or 'D'");
				       } else {
				    	   if (inputsText.equals("D"))
				    		   isDirected = true;
				    	   Graphic tmp = new Graphic(numVertices, isDirected);
					       g = tmp;
					       while ((inputsText = br.readLine()) != null) {
					    	   String[] words=inputsText.split(" ");
					    	   if (words.length != 2){
					    		   System.out.println("Invalid Format: other lines must be two integers seperated by space");
					    		   System.out.println();
					    		   break;
					    	   } else {
					    		   int a = Integer.parseInt(words[0]);
					    		   int b = Integer.parseInt(words[1]);
					    		   Edge e = new Edge(a, b);
					    		   g.insert(e);
					    	   }
					       }
					       br.close();
				       }
		    	   } catch (NumberFormatException e){
		    		   System.out.println("NumberFormatException: first line must be an integer");
		    	   }
		       } catch (IOException e) {
		    	   System.out.println("IOException");
		       }
	    System.out.println("***"+filename+"***");
	    return g;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
			Graphic g1 = createFromFile("Graph1.txt");
			g1.show();
			g1.unweighted(1);
			g1.printC();
			g1.unweighted(5);
			g1.printC();
			System.out.println();
			
			Graphic g2 = createFromFile("Graph2.txt");
			g2.show();
			g2.unweighted(4);
			g2.printC();
			g2.unweighted(8);
			g2.printC();
			System.out.println();
			
			Graphic g3 = createFromFile("Graph3.txt");
			g3.show();
			g3.unweighted(2);
			g3.printC();
			g3.unweighted(5);
			g3.printC();
			System.out.println();
			
			Graphic g4 = createFromFile("Graph4.txt");
			g4.show();
			g4.unweighted(8);
			g4.printC();
			g4.unweighted(6);
			g4.printC();
			System.out.println();
			
			Graphic g5 = createFromFile("Graph5.txt");
			g5.show();
			g5.unweighted(8);
			g5.printC();
			g5.unweighted(6);
			g5.printC();
			System.out.println();
			
			textWriter();
			Graphic g6 = createFromFile("Graph6.txt");
			System.out.println("Undirected graph with 100 vertices (randomly generated edges and starting points): ");
			Random ran = new Random();
			int a = ran.nextInt(99)+1;
			int b = ran.nextInt(99)+1;
			g6.unweighted(a);
			g6.printC();
			g6.unweighted(b);
			g6.printC();
			System.out.println();
		
	}
	
	public static void textWriter() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(
                "Graph6.txt"));
		writer.write("100");
		writer.newLine();
		writer.write("U");
		writer.newLine();
		Random ran = new Random();
		for (int i = 0; i < 150; i++){
			int a = ran.nextInt(99)+1;
			int b = ran.nextInt(99)+1;
			writer.write(a + " " + b);
			writer.newLine();
		}
		writer.close();
	}

}
