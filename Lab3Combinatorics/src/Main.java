/* CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab1Generic
 */

public class Main {

	public static void main(String[] args) {
		// Q1 test
		System.out.println("Count the number of anagrams of the following words (a) error (b) street (c) allele (d) Mississippi.");
		System.out.println("(a) "+identicalO(5, 3, 1, 1, 1));
		System.out.println("(b) "+identicalO(6, 2, 2, 1, 1));
		System.out.println("(a) "+identicalO(6, 3, 2, 1, 1));
		System.out.println("(d) "+identicalO(11, 4, 4, 2, 1));
		System.out.println();
		
		// Q2 test
		System.out.println("How many ways can we distribute (a) six apples to four children (b) four apples to six children.");
		System.out.println("(a) "+bins(6,4));
		System.out.println("(b) "+bins(4,6));
		System.out.println();
		
		// Q3 test
		System.out.println("How many ways can we distribute (a) six apples and three pears to five children (b) two apples, five pears and six bananas to three children.");
		System.out.println("(a) "+bins2(9, 5, 6, 3, 1));
		System.out.println("(b) "+bins2(13, 3, 6, 5, 2));
	}
	
	// Q1: ORDERING WITH IDENTICAL ITEMS
	public static double identicalO(double n, double i1, double i2, double i3, double i4){
		if (n==0)
			return 1;
		else if (i4!=1)
			return n/i4*identicalO(n-1, i1, i2, i3, i4-1);
		else if (i4==1 && i3!=1)
			return n/i3*identicalO(n-1, i1, i2, i3-1, i4);
		else if (i4==1 && i3==1 && i2!=1)
			return n/i2*identicalO(n-1, i1, i2-1, i3, i4);
		else if (i4==1 && i3==1 && i2==1 && i1!=1)
			return n/i1*identicalO(n-1, i1-1, i2, i3, i4);
		else
			return n*identicalO(n-1, i1, i2, i3, i4);
	}

	// Q2: DISTRIBUTING INDISTINGUISHABLE OBJECTS INTO BINS
	public static double bins(double n, double m){
		double a = n+m-1;
		if (m<=1) 
			return 1;
		else
			return a * bins(n, m-1);
	}
	
	// Q3: DISTRIBUTING DISTINGUISHABLE OBJECTS INTO BINS
	public static double bins2(double n, double m, double i1, double i2, double i3){
		double a = n+m-1;
		if (n==0)
			return 1;
		else if (i3!=1)
			return a/i3*bins2(n-1, m, i1, i2, i3);
		else if (i3==1 && i2!=1)
			return a/i2*bins2(n-1, m, i1, i2-1, i3);
		else if (i3==1 && i2==1 && i1!=1)
			return a/i1*bins2(n-1, m, i1-1, i2, i3);
		else 
			return a*bins2(n-1, m, i1, i2, i3);
	}
}
