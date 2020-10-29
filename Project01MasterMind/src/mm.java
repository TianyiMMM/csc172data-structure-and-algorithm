/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Project01MasterMind
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class mm {
	public String[] tokencolors = new String[4];
	public ArrayList<ArrayList<String>> possibilityLists = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> possibilityDeleteBoth = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> possibilityDeletePst = new ArrayList<ArrayList<String>>();
	ArrayList<String> possibility = new ArrayList<String>();
	ArrayList<String> guess = new ArrayList<String>();
	int guessNum = 0;
	public int positions = 0;
	
	public mm(String[] tokencolors, int colorsNum, int positions) {
		this.positions=positions;
		mmRec(tokencolors, colorsNum, positions);
		nextMove();
	}// end of constructor
	
	// uses recursion to generate a list of all possible color sets
	public void mmRec(String[] tokencolors, int colorsNum, int positions){
		
		if (positions==1){ // base case
			for (int i = 0; i < colorsNum; i++){
				ArrayList<String> possibilityCopy = new ArrayList<String>();
				for (String n : possibility) {
					possibilityCopy.add(n);
				}
				possibilityCopy.add(tokencolors[i]);
				possibilityLists.add(possibilityCopy);
			}
		} 
		else {
		for (int i = 0; i < colorsNum; i++){ // induction case
			possibility.add(tokencolors[i]);
			mmRec(tokencolors, colorsNum, positions-1); // calculate the possible outcomes in the last position
			possibility.remove(possibility.size()-1); // replace the possible outcome with the next one
		}
		}
		
	}

	// deletes all possible color sets that do not follow the user response
	public void response(int positionsAndColorRight, int colorsRightPositionWrong) {
		System.out.println();
		int countBothRight = 0;
		int countColorRight = 0;
		if (positionsAndColorRight==positions){
			System.out.print("The color set in your mind: ");
			for (String str : guess){
				System.out.print(str + " ");
			}
			System.out.println();
		} else {
			// check items that have exactly the same numbers of items of which both positions and colors 
			// are as same as the previous guess
		for (ArrayList<String> l1 : possibilityLists) {
			   for (int i = 0; i < positions; i++) {
				   if (l1.get(i).equals(guess.get(i))){
					   countBothRight++;
				   }
			   }
			   if (countBothRight!=positionsAndColorRight){
				   possibilityDeleteBoth.add(l1);
			   }
			   countBothRight=0;
			} 
		// delete those items
		for (ArrayList<String> l1 : possibilityDeleteBoth){
			possibilityLists.remove(l1);
		}
		// check items that have exactly the same numbers of items of which only color 
		// are as same as the previous guess
		for (ArrayList<String> l1 : possibilityLists) {
			for (int i = 0; i < positions; i++) {
				if (!l1.get(i).equals(guess.get(i))){
				for (int u = 0; u < positions; u++) {
					if (l1.get(i).equals(guess.get(u))&&i!=u){
						boolean bothRight = false;
						for (int v = 0; v < positions; v++) {
							if (guess.get(u).equals(l1.get(v))&&u==v){
								bothRight = true;
							}
						}
						if (bothRight==false){
							countColorRight++;
							u=positions;
						}
					}
				}
				}
			}
			   if (countColorRight!=colorsRightPositionWrong){
				   possibilityDeletePst.add(l1);
			   }
			   countColorRight=0;
			} 
		// delete those items
		for (ArrayList<String> l1 : possibilityDeletePst){
			possibilityLists.remove(l1);
		}
		guess = new ArrayList<String>();
		nextMove();
		}
	} 
	public void newGame() { // Reset the game public 
		tokencolors = new String[4];
		possibilityLists = new ArrayList<ArrayList<String>>();
		possibilityDeleteBoth = new ArrayList<ArrayList<String>>();
		possibilityDeletePst = new ArrayList<ArrayList<String>>();
		possibility = new ArrayList<String>();
		guess = new ArrayList<String>();
		guessNum = 0;
		positions=0;
		System.out.println();
		System.out.println("New Game! ");
	}
	
	// generate a random guess from the lists of possible color sets
	public void nextMove() {
		Random rdm = new Random();
		//guess = new ArrayList<String>();
		int size = possibilityLists.size();
		if (size == 1){
			System.out.print("Guess: ");
			for (ArrayList<String> po : possibilityLists){
				for (String str : po){
					System.out.print(str + " ");
					guess.add(str);
				}
				System.out.println();
				}
		} else {
			guessNum = rdm.nextInt(size-1);
			System.out.print("Guess: ");
			for (String str : possibilityLists.get(guessNum)){
				guess.add(str);
				System.out.print(str + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Boolean play = true;
		System.out.println("Welcom to MasterMind! ");
		System.out.println();
		
		while (play==true){ // current round begins
		String[] tokencolors = new String[6];
		int positions = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("Specify how many color you want [Maximum 6]: ");
		int colorsNum = input.nextInt();
		System.out.print("Specify the colors you want: ");
		for (int i = 0; i < colorsNum; i++){
			tokencolors[i] = input.next();
		}
		System.out.print("Specify how many positions you want: ");
		positions = input.nextInt();
		mm game = new mm(tokencolors, colorsNum, positions);
		
		int positionsAndColorRight = 1;
		int colorsRightPositionWrong = 1;
		
		// prompts user for response to the guess
		while (positionsAndColorRight!=positions || colorsRightPositionWrong!=0){
			System.out.print("Please enter the numbers of tokens of which both color and position right: ");
			positionsAndColorRight = input.nextInt();
			System.out.print("Please enter the numbers of tokens of which color right but position wrong: ");
			colorsRightPositionWrong = input.nextInt();
			game.response(positionsAndColorRight, colorsRightPositionWrong);
		}
		System.out.println();
		System.out.print("Do you want to play again? [yes/no]");
		String newgame = input.next();
		if (newgame.equals("yes")){
			game.newGame();
		} else {
			play=false;
		}
		System.out.println();
		} // current round ends
	}
}
