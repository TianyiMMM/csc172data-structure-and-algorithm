/* CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab1Generic
 */
public class Main {

	public static void main(String[] args) {
		// Q1 test
		System.out.println("1. How many ways can we paint three houses in any of four colors?");
		System.out.println(counting(4, 3));
		System.out.println("2. Suppose a computer password consists of eight to ten letters and/or digits. How many different passwords are there? Remember that an upper-case letter is different from a lower- case one.");
		System.out.println(counting(62, 8)+counting(62, 9)+counting(62, 10));
		System.out.println();
		
		// Q2 test
		System.out.println("1. If we have 9 players for a baseball team, how many possible batting orders are there?");
		System.out.println(permutation(9));
		System.out.println();
		
		// Q3 test
		System.out.println("1. In a class of 200 students, we wish to elect a President, Vice President, Secretary and Treasurer. In haw many ways can these four officers be selected?");
		System.out.println(orderedS(200, 4));
		System.out.println("2. How many ways are there to form a sequence of m letters out of the 26 letters, if no letter is allowed to appera more than once for (a) m=3, (b) m=5.");
		System.out.println("(a) "+orderedS(26, 3));
		System.out.println("(b) "+orderedS(26, 5));
		System.out.println();
		
		// Q4 test
		System.out.println("In poker, each player is dealt five cards from a 52 card deck. How many different possible hands are there?");
		System.out.println(unorderedS(52, 5));
		System.out.println();
	}
	
	// Q1: Counting Assignment
	public static double counting(double k, double n){
		if (n==0) return 1;
		if (n==1) return k;
		if ((n % 2) == 0)
		      return counting(k*k, n/2);
		else
		      return counting(k*k, (n-1)/2) * k;

	}
	
	// Q2: Counting Permutation
	public static double permutation(double n){
		if (n<=1) 
			return 1;
		else
			return n * permutation(n-1);
	}
	
	// Q3: Ordered Selections
	public static double orderedS(double n, double m){
		if (m<=0) 
			return 1;
		else
			return n * orderedS(n-1, m-1);
	}
	
	// Q4: Unordered Selections
	public static double unorderedS(double n, double m){
		if (m<=0) 
			return 1;
		else
			return n/m * orderedS(n-1, m-1);
	}
}
