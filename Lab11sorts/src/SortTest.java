/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab11sorts
 */
import java.util.Arrays;
import java.util.Scanner;

public class SortTest { 
	
	private static int count;
	private static int countIns;
	private static int countShell;
	
	public static void main(String args[]) { 
		long startTime, endTime, elapsedTime; 
		Scanner in = new Scanner(System.in);
		System.out.print("input the size of your array: ");
		int size = in.nextInt();
		Integer [] a = new Integer[size]; 
		Integer [] b = new Integer[size];
		Integer [] c = new Integer[size];
		Integer [] d = new Integer[size];

		for (int i = 0; i < size; i++) {
			a[i] = b[i] = c[i] = d[i] = (int)(Math.random() * 100); 
		}

		System.out.println(Arrays.toString(a)); 
		startTime = System.currentTimeMillis(); 
		Arrays.sort(a); 
		endTime = System.currentTimeMillis(); 
		elapsedTime = endTime - startTime; 
		System.out.println(Arrays.toString(a)); 
		System.out.println("library sort() method sort the array in: "+ elapsedTime + " millesec"); 
		System.out.println();
		
		System.out.println(Arrays.toString(b)); 
		countShell = 0; 
		startTime = System.currentTimeMillis(); 
		shellSort(b); 
		endTime = System.currentTimeMillis(); 
		elapsedTime = endTime - startTime; 
		System.out.println(Arrays.toString(b)); 
		System.out.println("Shell sort took "+ countShell + " moves to sort " + size + " items"); 
		System.out.println("\t in : "+ elapsedTime + " millesec"); // Reset count and array count = 0; for (int i = 0; i < size; i++) a[i] = b[i];
		System.out.println();

		System.out.println(Arrays.toString(c)); 
		countIns = 0; 
		startTime = System.currentTimeMillis(); 
		insertionSort(c); 
		endTime = System.currentTimeMillis(); 
		elapsedTime = endTime - startTime; 
		System.out.println(Arrays.toString(c)); 
		System.out.println("Insertion sort took "+ countIns + " moves to sort " + size + " items"); 
		System.out.println("\t in : "+ elapsedTime + " millesec"); // Reset count and array count = 0; for (int i = 0; i < size; i++) a[i] = b[i];
		System.out.println();
		
		System.out.println(Arrays.toString(d)); 
		count = 0; 
		startTime = System.currentTimeMillis(); 
		bubblesort(d); 
		endTime = System.currentTimeMillis(); 
		elapsedTime = endTime - startTime; 
		System.out.println(Arrays.toString(d)); 
		System.out.println("Bubble sort took "+ count + " moves to sort " + size + " items"); 
		System.out.println("\t in : "+ elapsedTime + " millesec"); // Reset count and array count = 0; for (int i = 0; i < size; i++) a[i] = b[i];
		System.out.println();
		
	}
	
	public static <AnyType extends Comparable<? super AnyType>> void bubblesort(AnyType[] a) { 
		for (int i = 0; i < a.length; i++) { 
			for (int j = 0; j < a.length - 1; j++) { 
				if (a[j].compareTo(a[j + 1]) > 0) { 
					AnyType tmp = a[j]; 
					count++; 
					a[j] = a[j + 1]; 
					count++; 
					a[j + 1] = tmp; 
					count++;
					}
				}
			}
		}

public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) { 
	for (int i = 1; i < a.length; i++) { 
		for (int j = i; j > 0; j--) { 
			if (a[j].compareTo(a[j - 1]) < 0) { 
				AnyType tmp = a[j]; 
				countIns++; 
				a[j] = a[j - 1]; 
				countIns++; 
				a[j - 1] = tmp; 
				countIns++;
				}
			}
		}
	}

public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) { 
	int i0 = (int) (Math.log(a.length+1)/Math.log(2));
	for (int i = i0; i > 0; i--) { 
		int incr = (int)(Math.pow(2, i)-1);
		for (int j = 0; j < incr; j++) { 
			shellSort2(a, j, incr);
			}
		shellSort2(a, 0, 1);
		}
	}

public static <AnyType extends Comparable<? super AnyType>> void shellSort2(AnyType[] a, int start, int incr) { 
	for (int i = start+incr; i < a.length; i+=incr) { 
		for (int j = i; j >= incr; j--) { 
			if (a[j].compareTo(a[j - incr]) < 0) { 
				AnyType tmp = a[j]; 
				countShell++; 
				a[j] = a[j - incr]; 
				countShell++; 
				a[j - incr] = tmp; 
				countShell++;
				}
			}
		}
	}
}